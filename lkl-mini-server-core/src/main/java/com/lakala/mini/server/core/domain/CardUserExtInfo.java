/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户扩展信息
 * @author QW
 * 2012-10-19下午02:26:35
 */
@Entity
@Table(name="TAB_CARD_USER_EXTINFO")
@SequenceGenerator(name="SEQ_GEN", allocationSize=25,sequenceName="SEQ_CARD_USER_EXTINFO",initialValue=10000000)
public class CardUserExtInfo extends IdEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4291546213788111051L;
	
	@Column(name="CARD_USER_ID")
	private Long cardUserId;
	/**
	 * 手机号码
	 */
	@Column(name="MOBILE_PHONE_NUM")
	private String mobilePhoneNum;
	/**
	 * 电信运营商
	 */
	@Column(name="TELECOM_OPERATORS")
	private String telecomOperators;
	/**
	 * 手机型号
	 */
	@Column(name="MOBILE_PHONE_MODEL")
	private String mobilePhoneModel;
	/**
	 * 手机产品类型
	 */
	@Column(name="MOBILE_PHONE_PRODUCT")
	private String mobilePhoneProduct;
	/**
	 * 手机厂商名称
	 */
	@Column(name="MOBILE_PHONE_MANUFACTURER")
	private String mobilePhoneManuFacturer;
	
	@Column(name="RECORD_DATE")
	private Date recordDate;

	public Long getCardUserId() {
		return cardUserId;
	}

	public void setCardUserId(Long cardUserId) {
		this.cardUserId = cardUserId;
	}

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

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public String toString() {
		return "CardUserExtInfo [cardUserId=" + cardUserId
				+ ", mobilePhoneNum=" + mobilePhoneNum + ", telecomOperators="
				+ telecomOperators + ", mobilePhoneModel=" + mobilePhoneModel
				+ ", mobilePhoneProduct=" + mobilePhoneProduct
				+ ", mobilePhoneManuFacturer=" + mobilePhoneManuFacturer
				+ ", recordDate=" + recordDate + "]";
	}

}
