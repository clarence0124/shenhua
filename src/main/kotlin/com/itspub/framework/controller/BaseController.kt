package com.itspub.framework.controller

import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by Administrator on 2017/1/20.
 */
open abstract class BaseController {

    protected val moduleContext: String = javaClass.getAnnotation(RequestMapping::class.java).value[0]

    protected fun internalResource(jspPath: String): String {
        return "/WEB-INF/$moduleContext/$jspPath.jsp"
    }
}