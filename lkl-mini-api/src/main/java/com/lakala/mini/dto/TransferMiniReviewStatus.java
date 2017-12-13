/*
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ray
 * @since 1.0.0
 */
@XmlType(name="TransferMiniReviewStatus")
@XmlEnum
public enum TransferMiniReviewStatus {
	@XmlEnumValue(value = "NONE")
	NONE("NONE"), 
	@XmlEnumValue(value = "APPLY")
	APPLY("APPLY"), 
	@XmlEnumValue(value = "PASS")
	PASS("PASS"), 
	@XmlEnumValue(value = "REJECT")
	REJECT("REJECT");
	private final String value;
	private TransferMiniReviewStatus(String value) {
		this.value = value;
	}
	public static TransferMiniReviewStatus fromValue(String value) {
		if (value != null && value.trim().length() > 0) {
			value = value.toUpperCase();
			for (TransferMiniReviewStatus c : TransferMiniReviewStatus.values()) {
				if (c.value.equals(value)) {
					return c;
				}
			}
		}
		throw new IllegalArgumentException("Value is not in TransferMiniReviewStatus ");
	}
}
