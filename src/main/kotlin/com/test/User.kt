package com.test

/**
 * Created by Administrator on 2016/12/8.
 */
data class User(var id: String?, var name: String?) : Human {

    constructor(): this(null, null)

    var status: HumanStatus = HumanStatus.HEALTH

    constructor(id: String) : this(id, null)

    init {

    }
}