/*
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;

/**
 * @author ray	
 * @since 1.4.0 
 */
@XmlType(name="IdCardPicUploadType")
@XmlEnum
@WSDLDocumentation(value="证件图片上传类型")
public enum IdCardPicUploadType {
	/**
	 * 门户
	 */
	@XmlEnumValue(value = "PORTAL")
	PORTAL("PORTAL"),/**
	 *邮件 
	 */
	@XmlEnumValue(value = "MAIL")
	MAIL("MAIL"),/**
	 *传真 
	 */
	@XmlEnumValue(value = "FAX")
	FAX("FAX");
	private final String value;
	private IdCardPicUploadType(String value) {
		this.value = value;
	}
	public static IdCardPicUploadType fromValue(String value) {
		if (value != null && value.trim().length() > 0) {
			value = value.toUpperCase();
			for (IdCardPicUploadType c : IdCardPicUploadType.values()) {
				if (c.value.equals(value)) {
					return c;
				}
			}
		}
		throw new IllegalArgumentException("Value is not in IdCardPicUploadType ");
	}
	
	public  String getValue(){
		return value;
	}
}
