package com.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by Administrator on 2016/12/17.
 */
@Controller
@RequestMapping(value = "/test")
class TestController @Autowired constructor(@Resource private var testService: TestService) {



    @ResponseBody
    @RequestMapping(value="test1")
    fun test1(user: User): String {
        try {
            var test1 = testService.test1()
            test1!!.forEach { println(it.status) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return user.status.toString()
    }
}