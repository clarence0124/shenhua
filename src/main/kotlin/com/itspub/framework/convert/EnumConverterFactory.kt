package com.itspub.framework.convert

import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import java.lang.Enum

class EnumConverterFactory : ConverterFactory<String, Enum<*>> {

    override fun <T : Enum<*>> getConverter(targetType: Class<T>): Converter<String, T> {
        return EnumConverter(targetType)
    }
}