package com.test

import com.itspub.framework.interceptor.annotation.SpringInterceptor
import com.itspub.util.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2015/4/1.
 */
@SpringInterceptor(priority = 5, sort = 2)
class LogInterceptor2 : HandlerInterceptor {

    val logger = LoggerFactory.getLogger(LogInterceptor2::class.java)!!

    var ACCESS_TOKENS = ThreadLocal<String>()

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is ResourceHttpRequestHandler || handler is DefaultServletHttpRequestHandler) {
            return true
        } else if (handler is HandlerMethod) {

            val accessToken = StringUtils.randomLetterAndNumber(6)
            ACCESS_TOKENS.set(accessToken)

            val controllerClass = handler.beanType
            val controllerMethod = handler.method
            val args = arrayOf<Any>(accessToken, controllerClass.canonicalName, controllerMethod.name)
            logger.info(String.format("Spring:%s \r\n controller=%s\r\n method=%s\r\n", *args))
        }
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        if (null != modelAndView && null != ACCESS_TOKENS.get()) {
            logger.info(String.format("Spring:%s view:%s \r\n", ACCESS_TOKENS.get(), modelAndView.viewName))
        }
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        print("after2")
    }
}
