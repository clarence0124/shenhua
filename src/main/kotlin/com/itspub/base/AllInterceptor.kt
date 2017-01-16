package com.itspub.base

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2015/4/1.
 */
class AllInterceptor: HandlerInterceptor, ApplicationContextAware {

    private val sortInterceptors: ArrayList<Any> = ArrayList()

    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        /**
         * 查找配置的SpringInterceptor的Bean
         */
        var beansOfType = applicationContext?.getBeansWithAnnotation(SpringInterceptor::class.java)

        /**
         * 使用排序树获取排序后的SpringInterceptor对应的Bean
         */
        val treeMap = TreeMap<Int, Any>()
        beansOfType!!.map {
            var annotation = it.value.javaClass.getAnnotation(SpringInterceptor::class.java)
            treeMap.put(annotation.sort, it.value)
        }
        sortInterceptors.addAll(treeMap.values)
    }

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        sortInterceptors.forEach {
            if (it is HandlerInterceptor && !it.preHandle(request, response, handler)) {
                return false
            }
        }

        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        sortInterceptors.reversed().forEach {
            if (it is HandlerInterceptor) {
                it.postHandle(request, response, handler, modelAndView)
            }
        }
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        sortInterceptors.reversed().forEach {
            if (it is HandlerInterceptor) {
                it.afterCompletion(request, response, handler, ex)
            }
        }
    }
}
