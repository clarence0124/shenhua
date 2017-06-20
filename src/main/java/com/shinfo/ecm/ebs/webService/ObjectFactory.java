
package com.shinfo.ecm.ebs.webService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shinfo.ecm.ebs.webService package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetWbsTemplateNodesByIndustryTypeAndDisciplineType_QNAME = new QName("http://webService.ebs.ecm.shinfo.com/", "getWbsTemplateNodesByIndustryTypeAndDisciplineType");
    private final static QName _GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse_QNAME = new QName("http://webService.ebs.ecm.shinfo.com/", "getWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shinfo.ecm.ebs.webService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWbsTemplateNodesByIndustryTypeAndDisciplineType }
     * 
     */
    public GetWbsTemplateNodesByIndustryTypeAndDisciplineType createGetWbsTemplateNodesByIndustryTypeAndDisciplineType() {
        return new GetWbsTemplateNodesByIndustryTypeAndDisciplineType();
    }

    /**
     * Create an instance of {@link GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse }
     * 
     */
    public GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse createGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse() {
        return new GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWbsTemplateNodesByIndustryTypeAndDisciplineType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.ebs.ecm.shinfo.com/", name = "getWbsTemplateNodesByIndustryTypeAndDisciplineType")
    public JAXBElement<GetWbsTemplateNodesByIndustryTypeAndDisciplineType> createGetWbsTemplateNodesByIndustryTypeAndDisciplineType(GetWbsTemplateNodesByIndustryTypeAndDisciplineType value) {
        return new JAXBElement<GetWbsTemplateNodesByIndustryTypeAndDisciplineType>(_GetWbsTemplateNodesByIndustryTypeAndDisciplineType_QNAME, GetWbsTemplateNodesByIndustryTypeAndDisciplineType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.ebs.ecm.shinfo.com/", name = "getWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse")
    public JAXBElement<GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse> createGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse(GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse value) {
        return new JAXBElement<GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse>(_GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse_QNAME, GetWbsTemplateNodesByIndustryTypeAndDisciplineTypeResponse.class, null, value);
    }

}
