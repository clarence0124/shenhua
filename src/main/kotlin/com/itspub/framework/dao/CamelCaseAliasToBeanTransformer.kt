package com.itspub.framework.dao

import com.itspub.framework.convert.EnumConverterFactory
import org.hibernate.property.ChainedPropertyAccessor
import org.hibernate.property.PropertyAccessor
import org.hibernate.property.PropertyAccessorFactory
import org.hibernate.property.Setter
import org.hibernate.transform.ResultTransformer
import java.util.regex.Pattern

/**
 * Created by Administrator on 2017/1/16.
 */
internal open class CamelCaseAliasToBeanTransformer(val type: Class<*>): ResultTransformer {

    internal var propertyAccessor: PropertyAccessor = ChainedPropertyAccessor(arrayOf(PropertyAccessorFactory.getPropertyAccessor(type, null), PropertyAccessorFactory.getPropertyAccessor("field")))
    internal var setters: Array<Setter?> = arrayOf()

    private val enumConverter = EnumConverterFactory()

    override fun transformList(list: List<*>): List<*> {
        return list
    }

    override fun transformTuple(tuple: Array<Any>, alias: Array<String>): Any {
        if (null == setters || 0 == setters.size) {
            setters = arrayOfNulls<Setter?>(alias.size)
            for (i in alias.indices) {
                var setter = propertyAccessor!!.getSetter(type, underScoreCaseToCamelCase(alias[i]))
                setters!![i] = setter
            }
        }
        try {
            val obj = type.newInstance()

            for (i in alias.indices) {
                var value = tuple[i]
                /*if (null != val) {
                    if (val instanceof java.sql.Timestamp) {
                        val = LocalDateTimeConverter.INSTANCE.convertToEntityAttribute((java.sql.Timestamp) val);
                    } else if (val instanceof java.sql.Date) {
                        val = LocalDateConverter.INSTANCE.convertToEntityAttribute((java.sql.Date) val);
                    }
                }*/

                val setter = setters!![i]!!

                // 参数类型
                val pClass = setter.method.parameterTypes[0]

                // 添加枚举转换处理
                value?.let {
                    if (pClass.superclass == Enum::class.java) {
                        value = handleEnumPropertyValue(value, pClass as Class<out java.lang.Enum<*>>)
                    }
                }

                setter.set(obj, value, null)
            }
            return obj
        } catch (e: InstantiationException) {
            throw IllegalStateException(type.toString() + "实例化出错")
        } catch (e: IllegalAccessException) {
            throw IllegalStateException(type.toString() + "实例化出错")
        }
    }

    private fun handleEnumPropertyValue(value: Any, pClass: Class<out java.lang.Enum<*>>): Any {
        return enumConverter.getConverter(pClass).convert(value.toString())
    }

    private fun underScoreCaseToCamelCase(underScoreCase: String?): String {
        var underScoreCase: String = underScoreCase ?: throw IllegalArgumentException("要转换的字符串不能为null")
        if (-1 == underScoreCase.indexOf("_")) {
            return underScoreCase
        }
        underScoreCase = underScoreCase.toLowerCase()
        val p = Pattern.compile("_([a-z])")
        val m = p.matcher(underScoreCase)
        val sb = StringBuffer()
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase())
        }
        m.appendTail(sb)
        return sb.toString()
    }
}