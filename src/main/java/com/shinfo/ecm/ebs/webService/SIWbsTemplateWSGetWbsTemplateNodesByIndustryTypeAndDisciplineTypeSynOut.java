package com.shinfo.ecm.ebs.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-06-19T15:56:55.914+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebService(targetNamespace = "http://www.shenhuagroup.com.cn/ZAOJIA", name = "SI_wbsTemplateWS_getWbsTemplateNodesByIndustryTypeAndDisciplineType_Syn_Out")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIWbsTemplateWSGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeSynOut {

    @WebResult(name = "getWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse", targetNamespace = "http://webService.ebs.ecm.shinfo.com/", partName = "parameters")
    @WebMethod(operationName = "SI_wbsTemplateWS_getWbsTemplateNodesByIndustryTypeAndDisciplineType_Syn_Out", action = "http://sap.com/xi/WebService/soap1.1")
    public GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse siWbsTemplateWSGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeSynOut(
        @WebParam(partName = "parameters", name = "getWbsTemplateNodesByIndustryTypeAndDisciplineType", targetNamespace = "http://webService.ebs.ecm.shinfo.com/")
        GetWbsTemplateNodesByIndustryTypeAndDisciplineType parameters
    );
}
