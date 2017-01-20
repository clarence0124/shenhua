package com.itspub.framework.interceptor.annotation

import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2017/1/11.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class SpringInterceptor(val priority: Int, val sort: Int)
