
package com.shinfo.ecm.ebs.webService;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-06-19T15:36:53.784+08:00
 * Generated source version: 2.7.18
 * 
 */
public final class SIWbsTemplateWSGetWbsTemplateListSynOut_HTTPPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.shenhuagroup.com.cn/ZAOJIA", "SI_wbsTemplateWS_getWbsTemplateList_Syn_OutService");

    private SIWbsTemplateWSGetWbsTemplateListSynOut_HTTPPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = SIWbsTemplateWSGetWbsTemplateListSynOutService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SIWbsTemplateWSGetWbsTemplateListSynOutService ss = new SIWbsTemplateWSGetWbsTemplateListSynOutService(wsdlURL, SERVICE_NAME);
        SIWbsTemplateWSGetWbsTemplateListSynOut port = ss.getHTTPPort();  
        
        {
        System.out.println("Invoking getWbsTemplateList...");
        java.lang.String _getWbsTemplateList__return = port.getWbsTemplateList();
        System.out.println("getWbsTemplateList.result=" + _getWbsTemplateList__return);


        }

        System.exit(0);
    }

}
