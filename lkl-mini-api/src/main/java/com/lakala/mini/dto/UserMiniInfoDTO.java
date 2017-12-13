package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UserMiniInfo")
public class UserMiniInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	/**
	 * 用户卡号
	 */
	private String userCardNum;
	/**
	 * 用户卡唯一标识
	 */
	private long cardInfoId;
	/**
	 * 用户卡类型
	 */
    private int type;
    /**
     * 用户卡状态
     */
    private int cardStatus;
	/**
	 * 绑定电话
	 */
	private String phoneNum;

	/**
	 * 状态
	 * 
	 * @see com.lakala.mini.common.UserAuditing
	 */
	private int status;
	/**
	 * 绑定时间 yyyy-MM-dd hh:mm:ss
	 */
	private String bindDate;

	private String psamNo;

	/**
	 * 最终审核日 yyyy-MM-dd hh:mm:ss
	 */
	private String auditingDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserCardNum() {
		return userCardNum;
	}

	public void setUserCardNum(String userCardNum) {
		this.userCardNum = userCardNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBindDate() {
		return bindDate;
	}

	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public String getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(String auditingDate) {
		this.auditingDate = auditingDate;
	}

	public long getCardInfoId() {
		return cardInfoId;
	}

	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}

}
