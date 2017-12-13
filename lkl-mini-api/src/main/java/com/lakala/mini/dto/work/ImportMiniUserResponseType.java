
package com.lakala.mini.dto.work;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importSUUserResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="importSUUserResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean" form="unqualified"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *         &lt;element name="storeNo" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *         &lt;element name="psamNo" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "importMiniUserResponseType", propOrder = {

})
public class ImportMiniUserResponseType {

    @XmlElement(namespace = "")
    protected boolean success;
    @XmlElement(namespace = "", required = true)
    protected String userId;
    @XmlElement(namespace = "", required = true)
    protected String storeNo;
    @XmlElement(namespace = "", required = true)
    protected String psamNo;

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the storeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreNo() {
        return storeNo;
    }

    /**
     * Sets the value of the storeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreNo(String value) {
        this.storeNo = value;
    }

    /**
     * Gets the value of the psamNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsamNo() {
        return psamNo;
    }

    /**
     * Sets the value of the psamNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsamNo(String value) {
        this.psamNo = value;
    }

}
