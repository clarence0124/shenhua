package com.itspub.base

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
            pt.enumConstants.forEach {
                if (it.toString().equals(strVal)) {
                    return it
                }
            }
            throw IllegalStateException("not found string " + strVal)
        } else {
            val ordinal = Integer.valueOf(strVal)!!.toInt()
            pt.enumConstants.forEach {
                if (it.ordinal() == ordinal) {
                    return it
                }
            }
            throw IllegalStateException("not found ordinal " + ordinal)
        }
    }

    override fun convert(source: String): T {
        return convertEnumPropertyValue(source, enumType) as T
    }
}