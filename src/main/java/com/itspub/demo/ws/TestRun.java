package com.itspub.demo.ws;

import com.itspub.demo.ws.DemoWebService;
import com.itspub.demo.ws.IDemoWebService;
import com.itspub.demo.ws.IDemoWebService_DemoWebServicePort_Client;

import javax.xml.namespace.QName;

/**
 * Created by Administrator on 2016/12/7.
 */
public class TestRun {

    public static void main(String[] args) {
        QName SERVICE_NAME = new QName("http://ws.demo.itspub.com/", "DemoWebService");
        DemoWebService ss = new DemoWebService(DemoWebService.WSDL_LOCATION, SERVICE_NAME);
        IDemoWebService demoWebServicePort = ss.getDemoWebServicePort();
        System.out.println(demoWebServicePort.sayHelloWorld());
    }

    public static String getTest(String str) {
        return str;
    }
}
