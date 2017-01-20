package com.itspub.framework.sso

import org.jasig.cas.client.authentication.AuthenticationFilter
import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl
import org.jasig.cas.client.authentication.GatewayResolver
import org.jasig.cas.client.util.AbstractCasFilter
import org.jasig.cas.client.util.CommonUtils
import org.jasig.cas.client.validation.Assertion
import java.io.IOException
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2017/1/18.
 */
// @WebFilter(filterName = "a_ssoFilter1", urlPatterns = arrayOf("/*"))
class CASAuthenticationFilter : AbstractCasFilter() {

    /**
     * The URL to the CAS Server login.
     */
    private var casServerLoginUrl: String? = null

    /**
     * Whether to send the renew request or not.
     */
    private var renew = false

    /**
     * Whether to send the gateway request or not.
     */
    private var gateway = false

    private var gatewayStorage: GatewayResolver = DefaultGatewayResolverImpl()

    @Throws(ServletException::class)
    override fun initInternal(filterConfig: FilterConfig?) {
        if (!isIgnoreInitConfiguration) {
            super.initInternal(filterConfig)
            setCasServerLoginUrl(getPropertyFromInitParams(filterConfig!!, "casServerLoginUrl", null))
            log.trace("Loaded CasServerLoginUrl parameter: " + this.casServerLoginUrl!!)
            setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")))
            log.trace("Loaded renew parameter: " + this.renew)
            setGateway(parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")))
            log.trace("Loaded gateway parameter: " + this.gateway)

            val gatewayStorageClass = getPropertyFromInitParams(filterConfig, "gatewayStorageClass", null)

            if (gatewayStorageClass != null) {
                try {
                    this.gatewayStorage = Class.forName(gatewayStorageClass).newInstance() as GatewayResolver
                } catch (e: Exception) {
                    log.error(e, e)
                    throw ServletException(e)
                }

            }
        }
    }

    override fun init() {
        super.init()
        CommonUtils.assertNotNull(this.casServerLoginUrl!!, "casServerLoginUrl cannot be null.")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse
        val session = request.getSession(false)

        if (notNeedHandle(request, response)) {
            filterChain.doFilter(request, response)
            return
        }

        val assertion = if (session != null) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) as Assertion else null

        if (assertion != null) {
            filterChain.doFilter(request, response)
            return
        }

        val serviceUrl = constructServiceUrl(request, response)
        val ticket = CommonUtils.safeGetParameter(request, artifactParameterName)
        val wasGatewayed = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl)

        if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
            filterChain.doFilter(request, response)
            return
        }

        val modifiedServiceUrl: String

        log.debug("no ticket and no assertion found")
        if (this.gateway) {
            log.debug("setting gateway attribute in session")
            modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl)
        } else {
            modifiedServiceUrl = serviceUrl
        }

        if (log.isDebugEnabled) {
            log.debug("Constructed service url: " + modifiedServiceUrl)
        }

        val urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl!!, serviceParameterName, modifiedServiceUrl, this.renew, this.gateway)

        if (log.isDebugEnabled) {
            log.debug("redirecting to \"" + urlToRedirectTo + "\"")
        }

        response.sendRedirect(urlToRedirectTo)
    }

    private fun notNeedHandle(request: HttpServletRequest, response: HttpServletResponse): Boolean {
        return request.requestURI.startsWith("/static") || request.requestURI.startsWith("/login")
    }

    fun setRenew(renew: Boolean) {
        this.renew = renew
    }

    fun setGateway(gateway: Boolean) {
        this.gateway = gateway
    }

    fun setCasServerLoginUrl(casServerLoginUrl: String) {
        this.casServerLoginUrl = casServerLoginUrl
    }

    fun setGatewayStorage(gatewayStorage: GatewayResolver) {
        this.gatewayStorage = gatewayStorage
    }
}