package com.itspub.cg

import com.itspub.core.dao.AuthorityDao
import java.nio.file.Paths

/**
 * Created by Administrator on 2017/1/23.
 */
object CodeGenTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val srcPath = Paths.get("E:\\workspace\\idea_2014\\gradle-kotlin\\src\\main\\kotlin")

        var type = AuthorityDao::class.java

        println(ClassDef(srcPath, type).gen())
        // println(CgUtils.getParameterNames(type.getDeclaredMethods("listAll", Double::class.java, String::class.java), srcPath)?.get(0))
    }
}