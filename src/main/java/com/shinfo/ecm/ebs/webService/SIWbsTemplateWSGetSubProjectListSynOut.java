package com.shinfo.ecm.ebs.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-06-19T15:27:28.480+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebService(targetNamespace = "http://www.shenhuagroup.com.cn/ZAOJIA", name = "SI_wbsTemplateWS_GetSubProjectList_Syn_Out")
@XmlSeeAlso({ObjectFactory.class})
public interface SIWbsTemplateWSGetSubProjectListSynOut {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSubProjectList", targetNamespace = "http://webService.ebs.ecm.shinfo.com/", className = "com.shinfo.ecm.ebs.webService.GetSubProjectList")
    @WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
    @ResponseWrapper(localName = "getSubProjectListResponse", targetNamespace = "http://webService.ebs.ecm.shinfo.com/", className = "com.shinfo.ecm.ebs.webService.GetSubProjectListResponse")
    public java.lang.String getSubProjectList(
        @WebParam(name = "projName", targetNamespace = "")
        java.lang.String projName
    );
}
