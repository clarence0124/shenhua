package com.test

import com.itspub.framework.interceptor.annotation.SpringInterceptor
import org.slf4j.LoggerFactory
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2015/4/1.
 */
@SpringInterceptor(priority = 1, sort = 1)
class AccessLogInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(AccessLogInterceptor::class.java)

    /*private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();*/
    internal var bts = ThreadLocal<Long>()

    fun getTimeMills(): Long {
        return System.currentTimeMillis()
    }

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        /*if (handler instanceof HandlerMethod) {
            HandlerMethod method = HandlerMethod.class.cast(handler);
            String loginUserId = (String) request.getSession().getAttribute(G.C.LOGIN_USER_ID);
            if (!method.getMethod().getName().equals("login") && !StringUtils.isMeaningFul(loginUserId)) {
                response.sendRedirect("/login.jsp");
                return false;
            }
        }*/

        // 记录开始时间
        bts.set(getTimeMills())
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        if (handler is HandlerMethod) {
            val method = HandlerMethod::class.java.cast(handler)
            var viewInfo = ""
            if (null != modelAndView && modelAndView.hasView()) {
                viewInfo = "view: " + modelAndView.viewName
            }
            logger.debug(String.format("controller: %s.%s \r\n%s", method.bean.javaClass.canonicalName, method.method.name, viewInfo))
        }
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        logger.debug("cost: " + (getTimeMills() - bts.get()) + "m\r\n")
    }
}
