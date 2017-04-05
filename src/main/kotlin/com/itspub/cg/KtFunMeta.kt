package com.itspub.cg

/**
 * Created by Administrator on 2017/2/21.
 */
class KtFunMeta(val interfaceMeta: KtInterfaceMeta, private val declaredAnnotations : List<String>, val descLine: String) {

    val annotationsDesc = declaredAnnotations.map {
        KtAnnotationMeta(it, interfaceMeta.declaredImports, interfaceMeta.filePackage)
    }

    val name = descLine.substring(descLine.indexOf(KtKeyWord.FUN) + KtKeyWord.FUN.length, descLine.indexOf("(")).trim()

    val parameters = descLine
                .substring(descLine.indexOf("(") + 1, descLine.indexOf(")"))
                .split(",")
                .filter { it.trim() != "" }
                .map { it.split(":") }
                .associate { Pair(it[0].trim(), it[1].trim()) }

    val returnType = descLine.substring(descLine.indexOf(")") + 1).replace(":", "").trim()

    val returnTypeGeneric = if (returnType.matches(""".+?(<.+>)$""".toRegex())) returnType.substring(returnType.indexOf("<") + 1, returnType.indexOf(">")) else null

    fun gen(body: CharSequence = ""): String {
        val mc = StringBuilder()
        // annotationsDesc.forEach { mc.appendln( "  @${it.descLine}") }
        mc.appendln("  ${KtKeyWord.OVERRIDE} $descLine {" )
            mc.appendln(body)
        mc.appendln("  }")
        return mc.toString()
    }
}