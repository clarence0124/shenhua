package com.itspub.raindrop

/**
 * Created by Administrator on 2017/2/22.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class NamedQuery(val value: String = "")