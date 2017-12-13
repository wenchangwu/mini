/*
 * 
 */
package com.lakala.mini.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.wrap.BaseRequestType;

/**
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name="TransferMiniApply")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransferMiniApplyDTO extends BaseRequestType{
	
	private static final long serialVersionUID = 1L;
	@NotNull
	@Valid
	private TransferMiniOrderDTO order;
	/**
	 * @return the order
	 */
	public TransferMiniOrderDTO getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(TransferMiniOrderDTO order) {
		this.order = order;
	}
	
	
	
	
}
