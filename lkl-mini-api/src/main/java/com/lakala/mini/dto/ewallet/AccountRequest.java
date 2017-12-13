/**
 * 
 */
package com.lakala.mini.dto.ewallet;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.dto.BaseRequestDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-5-14
 */
@XmlRootElement(name = "AccountRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * 拉卡拉用户序号
	 */
	@NotNull
	private Long userId;
	@NotNull
	private String  areaCode;
	
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

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @param userId
	 */
	public AccountRequest(Long userId) {
		this();
		this.userId = userId;
	}

	/**
	 * 
	 */
	public AccountRequest() {
		super();
	}

	/**
	 * @return the mobilePhoneNum
	 */
	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}

	/**
	 * @param mobilePhoneNum the mobilePhoneNum to set
	 */
	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}

	/**
	 * @return the telecomOperators
	 */
	public String getTelecomOperators() {
		return telecomOperators;
	}

	/**
	 * @param telecomOperators the telecomOperators to set
	 */
	public void setTelecomOperators(String telecomOperators) {
		this.telecomOperators = telecomOperators;
	}

	/**
	 * @return the mobilePhoneModel
	 */
	public String getMobilePhoneModel() {
		return mobilePhoneModel;
	}

	/**
	 * @param mobilePhoneModel the mobilePhoneModel to set
	 */
	public void setMobilePhoneModel(String mobilePhoneModel) {
		this.mobilePhoneModel = mobilePhoneModel;
	}

	/**
	 * @return the mobilePhoneProduct
	 */
	public String getMobilePhoneProduct() {
		return mobilePhoneProduct;
	}

	/**
	 * @param mobilePhoneProduct the mobilePhoneProduct to set
	 */
	public void setMobilePhoneProduct(String mobilePhoneProduct) {
		this.mobilePhoneProduct = mobilePhoneProduct;
	}

	/**
	 * @return the mobilePhoneManuFacturer
	 */
	public String getMobilePhoneManuFacturer() {
		return mobilePhoneManuFacturer;
	}

	/**
	 * @param mobilePhoneManuFacturer the mobilePhoneManuFacturer to set
	 */
	public void setMobilePhoneManuFacturer(String mobilePhoneManuFacturer) {
		this.mobilePhoneManuFacturer = mobilePhoneManuFacturer;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	

}
