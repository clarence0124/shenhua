package com.shinfo.ecm.ebs.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-06-19T15:49:26.903+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebService(targetNamespace = "http://www.shenhuagroup.com.cn/ZAOJIA", name = "SI_wbsTemplateWS_importWSExtimateDetails_Syn_Out")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIWbsTemplateWSImportWSExtimateDetailsSynOut {

    @WebResult(name = "importWSExtimateDetailsResponse", targetNamespace = "http://webService.ebs.ecm.shinfo.com/", partName = "parameters")
    @WebMethod(operationName = "SI_wbsTemplateWS_importWSExtimateDetails_Syn_Out", action = "http://sap.com/xi/WebService/soap1.1")
    public ImportWSExtimateDetailsResponse siWbsTemplateWSImportWSExtimateDetailsSynOut(
        @WebParam(partName = "parameters", name = "importWSExtimateDetails", targetNamespace = "http://webService.ebs.ecm.shinfo.com/")
        ImportWSExtimateDetails parameters
    );
}