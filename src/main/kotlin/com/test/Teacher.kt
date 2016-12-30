package com.test

/**
 * Created by Administrator on 2016/12/27.
 */
class Teacher(name: String) {

    var name: String = name!!

    override fun toString(): String {
        return name
    }
}