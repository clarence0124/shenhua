package com.itspub.framework.convert.annotation

import com.itspub.framework.convert.EnumType

/**
 * Created by Administrator on 2016/12/29.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Enumerated(val value: EnumType = EnumType.ORDINAL)