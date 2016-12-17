package com.itspub.demo.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2016-12-14T19:58:01.868+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebServiceClient(name = "DemoWebService", 
                  wsdlLocation = "file:/E:/workspace/idea_2014/gradle-kotlin/src/main/java/com/itspub/demo/ws/helloWorld.wsdl",
                  targetNamespace = "http://ws.demo.itspub.com/") 
public class DemoWebService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.demo.itspub.com/", "DemoWebService");
    public final static QName DemoWebServicePort = new QName("http://ws.demo.itspub.com/", "DemoWebServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/workspace/idea_2014/gradle-kotlin/src/main/java/com/itspub/demo/ws/helloWorld.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DemoWebService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/E:/workspace/idea_2014/gradle-kotlin/src/main/java/com/itspub/demo/ws/helloWorld.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DemoWebService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DemoWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DemoWebService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoWebService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoWebService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoWebService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IDemoWebService
     */
    @WebEndpoint(name = "DemoWebServicePort")
    public IDemoWebService getDemoWebServicePort() {
        return super.getPort(DemoWebServicePort, IDemoWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IDemoWebService
     */
    @WebEndpoint(name = "DemoWebServicePort")
    public IDemoWebService getDemoWebServicePort(WebServiceFeature... features) {
        return super.getPort(DemoWebServicePort, IDemoWebService.class, features);
    }

}
