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
            val queryName = if (0 < ps.size) ps["value"] else "\"${funMeta.name}\""

            val queryParams = "arrayOf<Any>(${funMeta.parameters.keys.joinToString()})"

            return when {
                funMeta.returnType.contains("List") -> {
                    val genericType = funMeta.returnTypeGeneric
                    if (null != genericType && !baseType.contains(CgUtils.getSimpleName(genericType))) {
                        return indent(2) + "return sqlDao.listByAliasToBean($genericType::class.java, NamedQueryConfig.get($queryName), $queryParams)"
                    } else {
                        return indent(2) + "return sqlDao.listAutoCast(NamedQueryConfig.get($queryName), $queryParams)"
                    }
                } else -> {
                    val returnType = funMeta.returnType
                    if (!baseType.contains(CgUtils.getSimpleName(returnType))) {
                        return indent(2) + "return sqlDao.getByAliasToBean($returnType::class.java, NamedQueryConfig.get($queryName), $queryParams)"
                    } else {
                        return indent(2) + "return sqlDao.getAutoCast(NamedQueryConfig.get($queryName), $queryParams)"
                    }
                }
            }
        }
        throw UnsupportedOperationException()
    }
}