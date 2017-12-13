/*
 * 
 */
package com.lakala.mini.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.wrap.BaseRequestType;

/**
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name = "TransferMiniReview")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "迷你移机申请审核", placement = Placement.BINDING)
public class TransferMiniReviewDTO extends BaseRequestType {

	private static final long serialVersionUID = 1L;
	@NotNull
	private String orderId;
	@NotNull
	private TransferMiniReviewStatus status;
	@NotNull
	private Long reviewerId;
	private String reviewName;
	private Date reviewDate;
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
	/**
	 * @return the status
	 */
	public TransferMiniReviewStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(TransferMiniReviewStatus status) {
		this.status = status;
	}
	/**
	 * @return the reviewerId
	 */
	public Long getReviewerId() {
		return reviewerId;
	}
	/**
	 * @param reviewerId the reviewerId to set
	 */
	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}
	/**
	 * @return the reviewName
	 */
	public String getReviewName() {
		return reviewName;
	}
	/**
	 * @param reviewName the reviewName to set
	 */
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	/**
	 * @return the reviewDate
	 */
	public Date getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
}
