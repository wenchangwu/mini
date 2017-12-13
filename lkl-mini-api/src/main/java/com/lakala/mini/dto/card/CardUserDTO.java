/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * @author QW
 * @CardUserDTO.java
 * @2011-4-12 下午04:33:44
 */
@XmlType(name="CardUser")
public class CardUserDTO implements Serializable {

	private static final long serialVersionUID = -4718350430401168051L;
	private Long id;

	private String psamNo;

	private long cardInfoId;

	private Date telModifyDate;

	private Date selfOpenDate;

	private Date auditingDate;

	private String telNo;

	private String telAreaNo;

	private int auditingState;

	private int telMovingCount;

	private String operatType;

	private String userName;

	private String identityCardPic;

	private String userPic;

	private String identityCard;

	private String userMobile;

	private String email;

	private String bindingTelNo;

	private String provoice;

	private String city;

	private String area;

	private String address;

	private String post;

	private Date changeDate;

	private Date bindingDate;

	private String nodeNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public long getCardInfoId() {
		return cardInfoId;
	}

	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	public Date getTelModifyDate() {
		return telModifyDate;
	}

	public void setTelModifyDate(Date telModifyDate) {
		this.telModifyDate = telModifyDate;
	}

	public Date getSelfOpenDate() {
		return selfOpenDate;
	}

	public void setSelfOpenDate(Date selfOpenDate) {
		this.selfOpenDate = selfOpenDate;
	}

	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTelAreaNo() {
		return telAreaNo;
	}

	public void setTelAreaNo(String telAreaNo) {
		this.telAreaNo = telAreaNo;
	}

	public int getAuditingState() {
		return auditingState;
	}

	public void setAuditingState(int auditingState) {
		this.auditingState = auditingState;
	}

	public int getTelMovingCount() {
		return telMovingCount;
	}

	public void setTelMovingCount(int telMovingCount) {
		this.telMovingCount = telMovingCount;
	}

	public String getOperatType() {
		return operatType;
	}

	public void setOperatType(String operatType) {
		this.operatType = operatType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityCardPic() {
		return identityCardPic;
	}

	public void setIdentityCardPic(String identityCardPic) {
		this.identityCardPic = identityCardPic;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBindingTelNo() {
		return bindingTelNo;
	}

	public void setBindingTelNo(String bindingTelNo) {
		this.bindingTelNo = bindingTelNo;
	}

	public String getProvoice() {
		return provoice;
	}

	public void setProvoice(String provoice) {
		this.provoice = provoice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Date getBindingDate() {
		return bindingDate;
	}

	public void setBindingDate(Date bindingDate) {
		this.bindingDate = bindingDate;
	}

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	
	
}
