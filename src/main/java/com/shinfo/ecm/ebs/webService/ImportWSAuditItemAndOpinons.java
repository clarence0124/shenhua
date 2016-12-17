
package com.shinfo.ecm.ebs.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>importWSAuditItemAndOpinons complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="importWSAuditItemAndOpinons">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auditItemList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "importWSAuditItemAndOpinons", propOrder = {
    "auditItemList",
    "subjId",
    "timeStamp"
})
public class ImportWSAuditItemAndOpinons {

    protected String auditItemList;
    protected String subjId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;

    /**
     * ��ȡauditItemList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditItemList() {
        return auditItemList;
    }

    /**
     * ����auditItemList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditItemList(String value) {
        this.auditItemList = value;
    }

    /**
     * ��ȡsubjId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjId() {
        return subjId;
    }

    /**
     * ����subjId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjId(String value) {
        this.subjId = value;
    }

    /**
     * ��ȡtimeStamp���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * ����timeStamp���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

}
