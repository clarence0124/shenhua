package com.itspub.cg

/**
 * Created by Administrator on 2017/2/21.
 */
class KtAnnotationMeta(descLine: String, declaredImports: MutableList<String>, currentPackage: Package) {

    val name: String = descLine.replace("""\(.*\)""".toRegex(), "")

    val type: Class<*> = if (0 < name.indexOf(".")) {
        Class.forName(name)
    } else {
        val className = declaredImports.associate { Pair(CgUtils.getSimpleName(it), it) }[name]
        if (null != className) Class.forName(className) else Class.forName(currentPackage.name + "." + className)
    }

    val parameters: Map<String, String> = when {
            descLine.indexOf("(") == -1 -> {
                mapOf()
            }
            else -> {
                descLine
                        .substring(descLine.indexOf("(") + 1, descLine.indexOf(")"))
                        .split(",")
                        .filter { it.trim() != "" }
                        .map { it.split("=") }
                        .associate { Pair(it[0].trim(), it[1].trim()) }
            }
        }
}