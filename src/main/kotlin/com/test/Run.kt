package com.test

import com.test.User

/**
 * Created by Administrator on 2016/12/7.
 */
object Run {
    @JvmStatic
    fun main(args: Array<String>) {
        var user = User("clarence")
        user.name = "陈儒聪"
        println(user.toString())
    }
}