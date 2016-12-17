
package com.shinfo.ecm.ebs.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getSubProjectList complex type?? Java ??
 * 
 * <p>??????????????????????��?????????
 * 
 * <pre>
 * &lt;complexType name="getSubProjectList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="projName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSubProjectList", propOrder = {
    "projName"
})
public class GetSubProjectList {

    protected String projName;

    /**
     * ???projName????????
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjName() {
        return projName;
    }

    /**
     * ????projName????????
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjName(String value) {
        this.projName = value;
    }

}
