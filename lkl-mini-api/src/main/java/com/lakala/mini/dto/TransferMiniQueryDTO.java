/*
 * 
 */
package com.lakala.mini.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name = "TransferMiniQuery")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "迷你移机申请单查询", placement = Placement.BINDING)
public class TransferMiniQueryDTO extends BaseQueryDTO {

	private Long uid;
	private List<String> orderId;
	private Date startApplyDate;
	private Date endApplyDate;
	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * @return the orderId
	 */
	public List<String> getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(List<String> orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the startApplyDate
	 */
	public Date getStartApplyDate() {
		return startApplyDate;
	}
	/**
	 * @param startApplyDate the startApplyDate to set
	 */
	public void setStartApplyDate(Date startApplyDate) {
		this.startApplyDate = startApplyDate;
	}
	/**
	 * @return the endApplyDate
	 */
	public Date getEndApplyDate() {
		return endApplyDate;
	}
	/**
	 * @param endApplyDate the endApplyDate to set
	 */
	public void setEndApplyDate(Date endApplyDate) {
		this.endApplyDate = endApplyDate;
	}
	
	
	
	
}
