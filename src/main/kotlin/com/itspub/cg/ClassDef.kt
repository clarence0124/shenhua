package com.itspub.cg

import com.itspub.framework.service.BaseService
import com.itspub.raindrop.NamedQueryConfig
import org.springframework.stereotype.Repository
import java.io.FileOutputStream
import java.nio.file.Path

/**
 * Created by Administrator on 2017/1/23.
 */
class ClassDef<T> constructor(val srcPath: Path, var interfaceClass: Class<T>) {

    private val meta = KtInterfaceMeta(srcPath, interfaceClass.`package`, interfaceClass.simpleName)
    private val code = StringBuilder()

    private fun getImplName(): String {
        return "${interfaceClass.simpleName}Impl"
    }

    fun gen(): String? {

        if (0 != code.length) {
            return code.toString()
        }

        val awareClasses = hashSetOf<Class<*>>()
        awareClasses.add(Repository::class.java)
        awareClasses.add(BaseService::class.java)
        awareClasses.add(NamedQueryConfig::class.java)

        val importLines = awareClasses.map { "${it.canonicalName}" }.plus(meta.declaredImports).sorted()

        val methodsCode = StringBuilder()
        meta.ktFunMetaList.forEach {
            methodsCode.append(it.gen(KtFunCodeGen(it).get()))
        }

        /**
         * package
         */
        code.appendln(interfaceClass.`package`.toString())
        code.appendln()

        /**
         * dependence
         */
        importLines.forEach { code.appendln("import $it") }
        code.appendln()

        /**
         * class
         */
        code.appendln("@Repository")
        code.appendln("class ${getImplName()} constructor(): BaseService(), ${interfaceClass.simpleName} {")
        code.appendln(methodsCode)
        code.appendln("}")

        return code.toString()
    }

    fun writeToDir(destDir: Path) {
        val os = FileOutputStream(CgUtils.getSourcePath(destDir, interfaceClass.`package`, getImplName() + ".kt").toFile())
        os.use {
            val writer = os.bufferedWriter()
            writer.write(gen())
            writer.flush()
        }
    }
}