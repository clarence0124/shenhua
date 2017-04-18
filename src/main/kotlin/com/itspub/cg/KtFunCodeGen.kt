package com.itspub.cg

import com.itspub.cg.CgUtils.indent
import com.itspub.raindrop.NamedQuery

/**
 * Created by Administrator on 2017/2/22.
 */
class KtFunCodeGen(private val funMeta: KtFunMeta) {

    val baseType = hashSetOf("Int", "Boolean", "BigDecimal", "Long", "String", "Double", "Integer")

    fun get(): String {
        val nqas = funMeta.annotationsDesc.filter { it.type == NamedQuery::class.java }
        if (0 < nqas.size) {
            val ps = nqas[0].parameters
            val namespace = ps["namespace"]
            val value = ps["value"]
            var queryName = if (null != value && !"".equals(value)) value else "\"${funMeta.name}\""
            if (null != namespace && !"".equals(namespace)) {
                queryName = "$namespace + \".\" + $queryName"
            } else {
                val filter = funMeta.interfaceMeta.ktAnnotationList.filter { it.type == NamedQuery::class.java }
                if (0 < filter.size) {
                    val defaultNamespace = filter[0].parameters["namespace"]
                    if (null != defaultNamespace && !"".equals(defaultNamespace)) {
                        queryName = "$defaultNamespace + \".\" + $queryName"
                    }
                }
            }

            val queryParams = "arrayOf<Any>(${funMeta.parameters.keys.joinToString()})"

            return indent(2) +  when {
                funMeta.returnType.contains("List") -> {
                    val genericType = funMeta.returnTypeGeneric
                    if (null != genericType && !baseType.contains(CgUtils.getSimpleName(genericType))) {
                        "return sqlDao.listByAliasToBean($genericType::class.java, NamedQueryConfig.get($queryName), $queryParams)"
                    } else {
                        "return sqlDao.listAutoCast(NamedQueryConfig.get($queryName), $queryParams)"
                    }
                } else -> {
                    val returnType = funMeta.returnType
                    if (!baseType.contains(CgUtils.getSimpleName(returnType))) {
                        "return sqlDao.getByAliasToBean($returnType::class.java, NamedQueryConfig.get($queryName), $queryParams)"
                    } else {
                        "return sqlDao.getAutoCast(NamedQueryConfig.get($queryName), $queryParams)"
                    }
                }
            }
        } else {
            throw UnsupportedOperationException()
        }
    }
}