package com.itspub.framework

import com.itspub.framework.interceptor.AllInterceptor
import com.itspub.framework.convert.EnumConverterFactory
import com.itspub.framework.convert.JsonMessageConverter
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.format.support.FormattingConversionService
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.util.*

/**
 * Created by Administrator on 2017/1/16.
 */
@EnableWebMvc
@ComponentScan(basePackages = arrayOf("com"))
@Configuration
open class MyWebMvcConfigurationSupport : WebMvcConfigurationSupport(), ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        this.applicationContext = applicationContext
    }

    @Bean
    override fun requestMappingHandlerAdapter(): RequestMappingHandlerAdapter {
        var defaultAdapter = super.requestMappingHandlerAdapter()
       /* val converters = ArrayList<HttpMessageConverter<*>>()
        converters.add(DataMessageConvert())

        val argumentResolvers = ArrayList<HandlerMethodArgumentResolver>()
        argumentResolvers.add(MyResolver(converters))
        defaultAdapter.customArgumentResolvers = argumentResolvers
        */

        val returnValueHandlers = ArrayList<HandlerMethodReturnValueHandler>()
        returnValueHandlers.add(JsonMessageConverter())
        defaultAdapter.customReturnValueHandlers = returnValueHandlers
        return defaultAdapter
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>?) {
        converters!!.add(0, JsonMessageConverter())
        super.extendMessageConverters(converters)
    }

    @Bean
    override fun requestMappingHandlerMapping(): RequestMappingHandlerMapping {
        var defaultMapping = super.requestMappingHandlerMapping()
        defaultMapping.setInterceptors(arrayOf(applicationContext!!.getBean(AllInterceptor::class.java)))
        return defaultMapping
    }

    @Bean(name = arrayOf("conversionService"))
    override fun mvcConversionService(): FormattingConversionService {
        var mvcConversionService = super.mvcConversionService()
        mvcConversionService.addConverterFactory(EnumConverterFactory())
        return mvcConversionService
    }
}