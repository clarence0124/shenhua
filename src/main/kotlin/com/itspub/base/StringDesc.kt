package com.itspub.base

/**
 * Created by Administrator on 2016/12/8.
 */
interface StringDesc : Desc<String> {
    override fun getDesc(): String {
        return this.toString()
    }
}