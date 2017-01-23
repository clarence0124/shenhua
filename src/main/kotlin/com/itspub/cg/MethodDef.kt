package com.itspub.cg

import com.itspub.util.ReflectUtils.getGenericParameterizedType
import java.lang.reflect.Method

/**
 * Created by Administrator on 2017/1/23.
 */
class MethodDef(val method: Method) {
    private val sb: StringBuilder = StringBuilder()

    fun gen(): String {
        val methodName = method.name
        val returnType = method.returnType

        val gpts = getGenericParameterizedType(method.genericReturnType)
        val rgt = if (0 < gpts.size) {
            "<" + gpts.subList(1, gpts.size).fold(gpts[0].simpleName, {x, y ->
                x + "," + y.simpleName
            }) + ">"
        } else {
            ""
        }

        sb.appendln("override internal fun ${methodName}(): ${returnType}${rgt} {")

        sb.appendln("}")
        return sb.toString()
    }
}