package com.itspub.cg

/**
 * Created by Administrator on 2017/1/23.
 */
class ClassDef constructor(var impl: Class<*>) {

    val code : StringBuilder = StringBuilder()

    fun gen(): String? {
        code.appendln(impl.`package`.toString())

        val className = impl.simpleName + "Impl"
        code.appendln("@Response")
        code.appendln("class ${className} constructor(): BaseService(), ${impl.simpleName} {")
            impl.declaredMethods.forEach { code.append(MethodDef(it).gen()) }


        code.appendln("}")
        return code.toString()
    }
}