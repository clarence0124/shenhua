package com.itspub.base

import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

class EnumConverterFactory : ConverterFactory<String, java.lang.Enum<*>> {

    override fun <T : java.lang.Enum<*>> getConverter(targetType: Class<T>): Converter<String, T> {
        return EnumConverter(targetType)
    }
}