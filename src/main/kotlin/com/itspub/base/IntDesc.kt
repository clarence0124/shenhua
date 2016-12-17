package com.itspub.base

/**
 * Created by Administrator on 2016/12/8.
 */
interface IntDesc : Desc<Int> {
    override fun getDesc(): Int = when (this) {
        is kotlin.Enum<*> -> this.ordinal
        else -> -1
    }
}