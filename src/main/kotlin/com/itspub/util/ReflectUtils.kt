
package com.itspub.util

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

/**
 * Created by Administrator on 2017/1/23.
 */
object ReflectUtils {

    fun getGenericParameterizedType(gt: Type): List<Class<*>> {
        if (gt is ParameterizedType) {
            return gt.actualTypeArguments.map { it as Class<*> }
        }
        return ArrayList()
    }
}