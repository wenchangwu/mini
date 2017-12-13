/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * @author 全伟(QW)
 * @date 2012-6-26
 * @time 下午03:06:16
 */
@XmlType(name="UDServiceRequestExtDTO")
public class UDServiceRequestExtDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2901259956098604859L;
	/**
	 * 手机号码
	 */
	private String mobilePhoneNum;
	/**
	 * 电信运营商
	 */
	private String telecomOperators;
	/**
	 * 手机型号
	 */
	private String mobilePhoneModel;
	/**
	 * 手机产品类型
	 */
	private String mobilePhoneProduct;
	/**
	 * 手机厂商名称
	 */
	private String mobilePhoneManuFacturer;
	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}
	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}
	public String getTelecomOperators() {
		return telecomOperators;
	}
	public void setTelecomOperators(String telecomOperators) {
		this.telecomOperators = telecomOperators;
	}
	public String getMobilePhoneModel() {
		return mobilePhoneModel;
	}
	public void setMobilePhoneModel(String mobilePhoneModel) {
		this.mobilePhoneModel = mobilePhoneModel;
	}
	public String getMobilePhoneProduct() {
		return mobilePhoneProduct;
	}
	public void setMobilePhoneProduct(String mobilePhoneProduct) {
		this.mobilePhoneProduct = mobilePhoneProduct;
	}
	public String getMobilePhoneManuFacturer() {
		return mobilePhoneManuFacturer;
	}
	public void setMobilePhoneManuFacturer(String mobilePhoneManuFacturer) {
		this.mobilePhoneManuFacturer = mobilePhoneManuFacturer;
	}
	@Override
	public String toString() {
		return "UDServiceRequestExtDTO [mobilePhoneNum=" + mobilePhoneNum
				+ ", telecomOperators=" + telecomOperators
				+ ", mobilePhoneModel=" + mobilePhoneModel
				+ ", mobilePhoneProduct=" + mobilePhoneProduct
				+ ", mobilePhoneManuFacturer=" + mobilePhoneManuFacturer + "]";
	}
	
	
	
}
