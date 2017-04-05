package com.itspub.util

import java.util.*

/**
 * Created by Administrator on 2016/12/28.
 */
object StringUtils {

    private fun getLetterAndNumberArray(): Array<Char?> {
        var arrayOfNulls = kotlin.arrayOfNulls<Char>(36)
        for (i in IntRange(0, 9)) {
            arrayOfNulls[i] = i.toString()[0]
        }
        for (j in IntRange(10, arrayOfNulls.size - 1)) {
            arrayOfNulls[j] = (j + 87).toChar()
        }
        return arrayOfNulls
    }

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

    fun randomLetterAndNumber(len: Int): String {
        val array = getLetterAndNumberArray()
        val r = Random()
        return IntRange(0, len).map {
            array[r.nextInt(array.size)]
        }.joinToString(separator = "")
    }
}