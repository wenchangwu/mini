package com.lakala.mini.dto;

import java.io.Serializable;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-7 下午02:07:54
 * 类说明,传递人工开通，人工移机终端参数,与大作业交互
 */

public class ManuServiceRequest  implements Serializable{

	private static final long serialVersionUID = -4339213791675005473L;
	String deviceNo;//大作业设备号
	String cardNo;//用户卡号
	String userName;//用户姓名
	String mobile;//用户手机号
	String identityCard;//身份证号
	String email;//EMAIL
	String province;//省
	String city;//市
	String area;//区
	String addr;//装机地址
	String post;//邮政编码
	String telAreaNo;//终端所属区号
	String telNo;//终端号码
	String psam;//PSAM号
	String copFlag;//联名卡商户标识
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getTelAreaNo() {
		return telAreaNo;
	}
	public void setTelAreaNo(String telAreaNo) {
		this.telAreaNo = telAreaNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getPsam() {
		return psam;
	}
	public void setPsam(String psam) {
		this.psam = psam;
	}
	public String getCopFlag() {
		return copFlag;
	}
	public void setCopFlag(String copFlag) {
		this.copFlag = copFlag;
	}
}
