package com.shinfo.ecm.ebs.webService;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-06-19T15:36:53.801+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebServiceClient(name = "SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService", 
                  wsdlLocation = "file:/D:/template/SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService.wsdl",
                  targetNamespace = "http://www.shenhuagroup.com.cn/ZAOJIA") 
public class SIWbsTemplateWSGetWbsTemplateListSynOutService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.shenhuagroup.com.cn/ZAOJIA", "SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService");
    public final static QName HTTPSPort = new QName("http://www.shenhuagroup.com.cn/ZAOJIA", "HTTPS_Port");
    public final static QName HTTPPort = new QName("http://www.shenhuagroup.com.cn/ZAOJIA", "HTTP_Port");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/template/SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SIWbsTemplateWSGetWbsTemplateListSynOutService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/template/SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SIWbsTemplateWSGetWbsTemplateListSynOutService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SIWbsTemplateWSGetWbsTemplateListSynOutService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SIWbsTemplateWSGetWbsTemplateListSynOutService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIWbsTemplateWSGetWbsTemplateListSynOutService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIWbsTemplateWSGetWbsTemplateListSynOutService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIWbsTemplateWSGetWbsTemplateListSynOutService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SIWbsTemplateWSGetWbsTemplateListSynOut
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIWbsTemplateWSGetWbsTemplateListSynOut getHTTPSPort() {
        return super.getPort(HTTPSPort, SIWbsTemplateWSGetWbsTemplateListSynOut.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SIWbsTemplateWSGetWbsTemplateListSynOut
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIWbsTemplateWSGetWbsTemplateListSynOut getHTTPSPort(WebServiceFeature... features) {
        return super.getPort(HTTPSPort, SIWbsTemplateWSGetWbsTemplateListSynOut.class, features);
    }
    /**
     *
     * @return
     *     returns SIWbsTemplateWSGetWbsTemplateListSynOut
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIWbsTemplateWSGetWbsTemplateListSynOut getHTTPPort() {
        return super.getPort(HTTPPort, SIWbsTemplateWSGetWbsTemplateListSynOut.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SIWbsTemplateWSGetWbsTemplateListSynOut
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIWbsTemplateWSGetWbsTemplateListSynOut getHTTPPort(WebServiceFeature... features) {
        return super.getPort(HTTPPort, SIWbsTemplateWSGetWbsTemplateListSynOut.class, features);
    }

}
