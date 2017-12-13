/*
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

/**
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name="ApplyTransferMiniResult")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "迷你移机申请接受结果", placement = Placement.BINDING)
public class TransferMiniApplyResultDTO implements Serializable {

	private static final long serialVersionUID = 4978630436929540463L;
	
	/**
	 * 申请单号
	 */
	private String orderId;
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
