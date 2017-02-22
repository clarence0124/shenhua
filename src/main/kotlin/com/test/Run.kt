package com.test

/**
 * Created by Administrator on 2016/12/7.
 */
object Run {
    @JvmStatic
    fun main(args: Array<String>) {
        /*var user = User("clarence")
        user.name = "陈儒聪"
        println(user.toString())*/

        System.out.println(Teacher("陈儒聪"))

        val list = listOf<String?>(null)
        System.out.println(list?.get(0) ?: "hello")

        var a = null
        a?.let { System.out.println("it=$it") }

        println(arrayOf("123", "555").joinToString())
    }
}