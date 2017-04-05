package com.itspub.framework

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

/**
 * Created by Administrator on 2017/1/17.
 */
@ControllerAdvice
class MyControllerAdvice {

    @ExceptionHandler
    fun exceptionHandler(ex: Exception): ModelAndView {
        val mv = ModelAndView("error")
        mv.addObject("exception", ex)
        ex.printStackTrace()
        return mv
    }
}