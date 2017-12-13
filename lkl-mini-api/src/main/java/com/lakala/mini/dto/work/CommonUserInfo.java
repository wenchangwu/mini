
package com.lakala.mini.dto.work;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="commonUserInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="psamNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardOrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="areaNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="changeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commonUserInfo", propOrder = {

})
public class CommonUserInfo {
	
    @XmlElement(required = true)
    protected String cardNo;
    @XmlElement(required = true)
    protected String psamNo;
    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    protected String cardOrg;
    @XmlElement(required = true)
    protected String areaNo;
    @XmlElement(required = true)
    protected String phoneNo;
    @XmlElement(required = true)
    protected String mobile;
    @XmlElement(required = true)
    protected Date changeDate;
    @XmlElement(required = true)
    protected int userType;
    @XmlElement(required = true)
    protected int status;  //0: 关闭， 1：开启


    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
     * Gets the value of the cardOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardOrg() {
        return cardOrg;
    }

    /**
     * Sets the value of the cardOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardOrg(String value) {
        this.cardOrg = value;
    }

    /**
     * Gets the value of the areaNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * Sets the value of the areaNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaNo(String value) {
        this.areaNo = value;
    }

    /**
     * Gets the value of the phoneNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the value of the phoneNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNo(String value) {
        this.phoneNo = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


}
