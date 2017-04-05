package com.itspub.raindrop

/**
 * Created by Administrator on 2017/2/22.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class NamedQuery(val namespace: String = "", val value: String = "")