package com.itspub.framework.convert

import com.itspub.framework.convert.annotation.Enumerated

/**
 * Created by Administrator on 2017/1/16.
 */
class EnumConverter<T : java.lang.Enum<*>>(private val enumType: Class<T>) : org.springframework.core.convert.converter.Converter<String, T> {

    fun convertEnumPropertyValue(`val`: Any, pt: Class<out java.lang.Enum<*>>): Any? {
        val annotation = pt.getAnnotation(Enumerated::class.java)
        val strVal = `val`.toString().trim()
        if ("" == strVal)
            return null
        else if (null != annotation && annotation.value === EnumType.STRING) {
            val filter = pt.enumConstants.filter { it.toString().equals(strVal) }
            return if (0 < filter.size) filter[0] else IllegalStateException("not found string " + strVal)
        } else {
            val ordinal = Integer.valueOf(strVal)!!.toInt()
            val filter = pt.enumConstants.filter { it.ordinal() == ordinal }
            return if (0 < filter.size) filter[0] else IllegalStateException("not found ordinal " + ordinal)
        }
    }

    override fun convert(source: String): T {
        return convertEnumPropertyValue(source, enumType) as T
    }
}