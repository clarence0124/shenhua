package com.itspub.base;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.EnumSet;

/**
 * Created by Administrator on 2016/12/29.
 */
public class EnumConverterFactory implements ConverterFactory<String, Enum> {

    // public static EnumConverterFactory INSTANCE = new EnumConverterFactory();

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new EnumConverter(targetType);
    }

    private Object handleEnumPropertyValue(Object val, Class<? extends Enum> pt) {
        Enumerated annotation = pt.getAnnotation(Enumerated.class);
        if (null != annotation && annotation.value() == EnumType.STRING) {
            String strVal = val.toString().trim();
            if (!strVal.equals("")) {
                return Enum.valueOf(pt, strVal);
            } else {
                return null;
            }
        } else {
            EnumSet<? extends Enum> enums = EnumSet.allOf(pt);
            final int ordinal = Integer.valueOf(val.toString()).intValue();
            for (Enum<? extends Enum> anEnum : enums) {
                if (ordinal == anEnum.ordinal()) {
                    return anEnum;
                }
            }
            throw new IllegalStateException("not found ordinal " + ordinal);
        }
    }

    private class EnumConverter<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public EnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            return (T) handleEnumPropertyValue(source, enumType);
        }
    }
}
