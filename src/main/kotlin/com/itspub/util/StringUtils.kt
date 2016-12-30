package com.itspub.util

/**
 * Created by Administrator on 2016/12/28.
 */
object StringUtils {

    @JvmStatic
    fun notNull(str: String?): String {
        return str ?: ""
    }

    @JvmStatic
    fun notNull(str: String?, candidate: String): String {
        return str ?: StringUtils.notNull(candidate)
    }

    @JvmStatic
    fun notNullAndEmpty(str: String, candidate: String): String {
        return if (notNull(str) == "") candidate else str
    }
}