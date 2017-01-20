package com.itspub.framework.sso

import org.jasig.cas.client.validation.AbstractTicketValidationFilter
import org.jasig.cas.client.validation.Cas10TicketValidator
import org.jasig.cas.client.validation.TicketValidator
import javax.servlet.FilterConfig
import javax.servlet.annotation.WebFilter

/**
 * Created by Administrator on 2017/1/18.
 */
// @WebFilter(filterName = "a_ssoFilter2", urlPatterns = arrayOf("/*"))
class CASValidationFilter : AbstractTicketValidationFilter() {

    override fun getTicketValidator(filterConfig: FilterConfig?): TicketValidator {
        val casServerUrlPrefix = getPropertyFromInitParams(filterConfig!!, "casServerUrlPrefix", null)
        val validator = Cas10TicketValidator(casServerUrlPrefix)
        validator.setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")))
        validator.setHostnameVerifier(getHostnameVerifier(filterConfig))
        validator.setEncoding(getPropertyFromInitParams(filterConfig, "encoding", null))

        return validator
    }
}