package com.test

import com.itspub.framework.convert.JsonResponse
import org.jasig.cas.client.util.AssertionHolder
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
    fun test1(user: User) : JsonResponse {
        println("ssoLoginUserName=" + AssertionHolder.getAssertion()?.principal?.name)
        var user1 = testService.test1()
        return JsonResponse(true, "ks001").addContent("ssoLoginUserName", AssertionHolder.getAssertion()).addContent("users", user1!!.toList())
    }

    @ResponseBody
    @RequestMapping(value="test2", produces = arrayOf("application/json"))
    fun test2(user: User) : JsonResponse {
        return JsonResponse(true, "ks001")
    }

    @RequestMapping(value="test3")
    fun test3(): String {
        return "jsonTest"
    }
}