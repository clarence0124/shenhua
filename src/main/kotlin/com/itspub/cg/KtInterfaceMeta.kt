package com.itspub.cg

import java.io.FileReader
import java.nio.file.Path

/**
 * Created by Administrator on 2017/2/21.
 */
class KtInterfaceMeta(dirPath: Path, val filePackage: Package, val fileName: String) {

    private val funLinePattern = """^\s*${KtKeyWord.FUN}\s*.*?""".toRegex()
    val declaredMethods = mutableListOf<String>()

    private val importLinePattern = """^\s*${KtKeyWord.IMPORT}\s*.*?""".toRegex()
    val declaredImports = mutableListOf<String>()

    private val annotationLinePattern = """^\s*${KtKeyWord.ANNOTATION}\s*.*?""".toRegex()

    val sourcePath = CgUtils.getSourcePath(dirPath, filePackage, fileName + ".kt")

    var ktFunMetaList: List<KtFunMeta> = mutableListOf()

    init {
        var declaredAnnotations: List<String> = listOf()
        FileReader(sourcePath.toFile()).readLines().filter{!it.trim().equals("")}.forEach {
            when {
                it.matches(importLinePattern) -> declaredImports += it.replace(importLinePattern, "").trim()
                it.matches(annotationLinePattern) -> declaredAnnotations += it.replace(annotationLinePattern, "").trim()
                it.matches(funLinePattern) -> {
                    declaredMethods += it.trim()
                    ktFunMetaList += KtFunMeta(this, declaredAnnotations, it.trim())
                    declaredAnnotations = listOf()
                }
            }
        }
    }
}