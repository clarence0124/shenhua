package com.test

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by Administrator on 2016/12/17.
 */
@Controller
@RequestMapping(value = "/test")
class TestController {

    @Resource
    private var testService: TestService? = null

    @ResponseBody
    @RequestMapping(value="test1")
    fun test1(user: User): String {
        return user.status.toString()
    }
}