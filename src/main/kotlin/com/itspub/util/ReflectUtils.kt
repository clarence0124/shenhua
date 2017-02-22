
package com.itspub.util

import java.lang.reflect.ParameterizedType

/**
 * Created by Administrator on 2017/1/23.
 */
object ReflectUtils {

    fun getGenericParameterizedType(gt: java.lang.reflect.Type): java.util.List<Class<*>> {
        val arrayList = arrayListOf<Class<*>>()
        if (gt is ParameterizedType) {
            gt.actualTypeArguments.forEach { arrayList.add(it as Class<*>) }
        }
        return arrayList as java.util.List<Class<*>>
    }

}