package com.itspub.framework.listener

import javax.servlet.ServletContextEvent


/**
 * Created by Administrator on 2017/2/15.
 */
class SpringInitListener() : org.springframework.web.context.ContextLoaderListener() {

    override fun contextInitialized(event: ServletContextEvent) {
        super.contextInitialized(event)
    }
}