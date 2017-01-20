package com.itspub.framework.convert

import org.springframework.core.MethodParameter
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import java.nio.charset.Charset

/**
 * Created by Administrator on 2017/1/17.
 */
class JsonMessageConverter : AbstractHttpMessageConverter<JsonResponse>(MediaType("application", "json", Charset.forName("UTF-8"))), HandlerMethodReturnValueHandler {

    override fun writeInternal(t: JsonResponse?, outputMessage: HttpOutputMessage) {
        return outputMessage.body.write(t.toString().toByteArray())
    }

    override fun supports(clazz: Class<*>): Boolean {
        return JsonResponse :: class.java.isAssignableFrom(clazz)
    }

    override fun readInternal(clazz: Class<out JsonResponse>, inputMessage: HttpInputMessage): JsonResponse {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun supportsReturnType(returnType: MethodParameter): Boolean {
        return AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody::class.java) != null || returnType.getMethodAnnotation(ResponseBody::class.java) != null
    }

    override fun handleReturnValue(returnValue: Any?, returnType: MethodParameter?, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}