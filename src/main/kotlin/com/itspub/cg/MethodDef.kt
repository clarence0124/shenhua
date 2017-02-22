package com.itspub.cg

import java.lang.reflect.Method
import java.nio.file.Path

/**
 * Created by Administrator on 2017/1/23.
 */
class MethodDef(val method: Method, val srcPath: Path) {

    private val sb: StringBuilder = StringBuilder()

    fun gen(): String {
        val methodName = method.name
        val returnType = method.returnType.canonicalName

        val returnTypeGeneric = CgUtils.getTypeGenericName(method.genericReturnType)
        val returnTypeGenericDesc = returnTypeGeneric
                ?.padStart(returnTypeGeneric.length + 1, '<')
                ?.padEnd(returnTypeGeneric.length + 2, '>') ?: ""

        val parameterNames = CgUtils.getParameterNames(method, srcPath)
        parameterNames?.forEach (::println)

        sb.appendln("  override fun $methodName(): $returnType$returnTypeGenericDesc {")
        sb.appendln("    val sql = \"select * from CAuthority\"")
        sb.appendln("    return sqlDao.listByAliasToBean($returnTypeGeneric::class.java, sql, arrayOf())")
        sb.appendln("  }")
        return sb.toString()
    }

}