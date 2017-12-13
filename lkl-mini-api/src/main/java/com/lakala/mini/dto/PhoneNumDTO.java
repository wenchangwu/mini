/*
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PhoneNum")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * @author ray
 * @since 1.0.0
 */
public class PhoneNumDTO implements Serializable {

	private static final long serialVersionUID = 8976895843259069671L;

	/**
	 * 区号
	 */
	@XmlElement
	private String areaCode;
	/**
	 * 主号
	 */
	@XmlElement(nillable=false,required=true)
	private String phoneNum;
	/**
	 * 分机号
	 */
	private String extensionNum;

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setExtensionNum(String extensionNum) {
		this.extensionNum = extensionNum;
	}

	public PhoneNumDTO() {
		super();
	}

	public PhoneNumDTO(String areaCode, String phoneNum, String extensionNum) {
		super();
		this.areaCode = areaCode;
		this.phoneNum = phoneNum;
		this.extensionNum = extensionNum;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getExtensionNum() {
		return extensionNum;
	}


}

