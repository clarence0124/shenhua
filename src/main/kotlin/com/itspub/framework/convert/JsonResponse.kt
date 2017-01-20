package com.itspub.framework.convert

import com.alibaba.fastjson.JSONObject
import java.util.*

/**
 * Created by Administrator on 2017/1/16.
 */
class JsonResponse(val success: Boolean, val statusCode: String) {

    val content = HashMap<String, Any?>()

    fun addContent(key: String, value: Any?): JsonResponse {
        content.put(key, value)
        return this
    }

    override fun toString(): String {
        return JSONObject.toJSONString(content)
    }
}