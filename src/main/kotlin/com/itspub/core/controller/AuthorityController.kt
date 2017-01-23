package com.itspub.core.controller

import com.itspub.core.service.AuthorityService
import com.itspub.framework.controller.BaseController
import com.itspub.framework.convert.JsonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by Administrator on 2017/1/20.
 */

@Controller
@RequestMapping(value = "su/authority")
class AuthorityController @Autowired constructor (
        @Autowired var authorityService: AuthorityService
) : BaseController() {

    @RequestMapping(value = "manage")
    fun manager(): String {
        return internalResource("manage")
    }

    @ResponseBody
    @RequestMapping(value = "listAll")
    fun listAll(): JsonResponse {
        return JsonResponse(true, "123").addContent("list", authorityService.listAll())
    }
}