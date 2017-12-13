/**
 * 
 */
package com.lakala.mini.dto;

import javax.validation.constraints.NotNull;


/**
 * @author ray
 *
 */
public class CardUserSubmitRequest {
	@NotNull
	private long id;
	
	/**
	 * PSAM卡号
	 */
	@NotNull
	private String psamNo;
	/**
	 * 用户服务卡唯一标识ID
	 */
	@NotNull
	private String cardInfoNo;
	
	
	/**
	 * 用户姓名
	 */
	@NotNull
	private String userName;
	/**
	 * 用户身份证图片
	 */
	@NotNull
	private String identityCardPic;
	/**
	 * 用户上传图片地址
	 */
	private String userPic;
	/**
	 * 证件号
	 */
	private String identityCard;
	/**
	 * 手机号
	 */
	private String userMobile;
	
	/**
	 * 注册email
	 */
	private String email;
	/**
	 * 注册绑定电话号码
	 */
	private String bindingTelNo;
	/**
	 * 省
	 */
	private String provoice;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 装机地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String post;
	
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}


	public String getCardInfoNo() {
		return cardInfoNo;
	}

	public void setCardInfoNo(String cardInfoNo) {
		this.cardInfoNo = cardInfoNo;
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
	
}
