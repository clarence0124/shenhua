package com.itspub.base;

import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCaseAliasToBeanTransformer implements ResultTransformer {

    private static final long serialVersionUID = 4564398361349116557L;

    final Class<?> type;
    PropertyAccessor propertyAccessor = null;
    Setter[] setters  = null;
    private CamelCaseAliasToBeanTransformer(Class<?> type) {
        this.type = type;
        propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[]
                {PropertyAccessorFactory.getPropertyAccessor(type, null), PropertyAccessorFactory.getPropertyAccessor("field")});
    }

    public static CamelCaseAliasToBeanTransformer toBean(Class<?> type) {
        return new CamelCaseAliasToBeanTransformer(type);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List transformList(List list) {
        return list;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] alias) {
        if (null == setters) {
            setters = new Setter[alias.length];
            for (int i = 0; i < alias.length; i++) {
                setters[i] =  propertyAccessor.getSetter(type, underScoreCaseToCamelCase(alias[i]));
            }
        }
        try {
            Object obj = type.newInstance();

            for (int i = 0; i < alias.length; i++) {
                Object val = tuple[i];
                /*if (null != val) {
                    if (val instanceof java.sql.Timestamp) {
                        val = LocalDateTimeConverter.INSTANCE.convertToEntityAttribute((java.sql.Timestamp) val);
                    } else if (val instanceof java.sql.Date) {
                        val = LocalDateConverter.INSTANCE.convertToEntityAttribute((java.sql.Date) val);
                    }
                }*/

                Setter setter = setters[i];

                // 参数类型
                Class<?> pClass = setter.getMethod().getParameterTypes()[0];

                // 添加处理
                if (null != val) {
                    if (pClass.getSuperclass() == Enum.class) {
                        // val = handleEnumPropertyValue(val, (Class<? extends Enum>) pClass);
                    }
                }

                setter.set(obj, val, null);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(type + "实例化出错");
        }
    }

    private Object handleEnumPropertyValue(Object val, Class<? extends Enum> pClass) {
        // return EnumConverterFactory.INSTANCE.getConverter(pClass).convert(val.toString());
        return null;
    }

    private String underScoreCaseToCamelCase(String underScoreCase) {
        if (null == underScoreCase) {
            throw new IllegalArgumentException("要转换的字符串不能为null");
        }
        if (-1 == underScoreCase.indexOf("_")) {
            return underScoreCase;
        }
        underScoreCase = underScoreCase.toLowerCase();
        Pattern p = Pattern.compile("_([a-z])");
        Matcher m = p.matcher(underScoreCase);
        StringBuffer sb = new StringBuffer();
        while(m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}