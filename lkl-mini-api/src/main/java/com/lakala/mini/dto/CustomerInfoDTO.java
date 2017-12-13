/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2013-1-9
 */
@XmlType(name="CustomerInfos")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 客户编号
	 */
	private Long customerId;
	
	/**
	 * 客户实名
	 */
	private String customerRealname;
	/**
	 * 客户注册手机号
	 */
	private String customerMobileNum;
	/**
	 * 客户证件号
	 */
	private String customerIdCardId;
	/**
	 * 客户最后登陆时间
	 */
	private Date customerLastLoginDateTime;
	
	/**
	 * 客户实名审核状态
	 */
	private String customerRealNameAuthStatus;
	/**
	 *客户住址
	 */
	private String customerAddr;
	
	/**
	 * 开通手机号
	 */
	private String openMobileNum;
	
	/**
	 * 开通手机厂商
	 */
	private String openMobileManufacturer;
	
	/**
	 * 开通状态
	 */
	private String openState;
	/**
	 * 开通手机型号
	 */
	private String openMobileModel;
	
	/**
	 * 开通手机运营商
	 */
	private String openMobileMobileOpt;
	
	/**
	 * 开通所在城市
	 */
	private String openLocation;
	
	/**
	 * 开通时间
	 */
	private Date openDateTime;
	/**
	 * 销售渠道
	 */
	private String salesChannel;
	/**
	 * 机构id
	 */
	private String ogId;
	/**
	 * 机构名称
	 */
	private String ogName;
	
	/**
	 * 机构代码
	 */
	private String ogCode;
	
	/**
	 * 最后交易日期时间
	 */
	private Date lastDealDateTime;
	
	
	/**
	 * 交易限制卡号数量
	 */
	private Integer dealCardLimit;
	
	
	/**
	 * 交易限制卡号明细
	 */
	private List<String> dealCardNo;
	
	
	
	/**
	 * PSAM
	 */
	private String psam;
	
	/**
	 * 绑定线路号码
	 */
	private String bindingTelNo;
	
	/**
	 * 绑定规则名称
	 */
	private String orgCardRuleName;

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerRealname
	 */
	public String getCustomerRealname() {
		return customerRealname;
	}

	/**
	 * @param customerRealname the customerRealname to set
	 */
	public void setCustomerRealname(String customerRealname) {
		this.customerRealname = customerRealname;
	}

	/**
	 * @return the customerMobileNum
	 */
	public String getCustomerMobileNum() {
		return customerMobileNum;
	}

	/**
	 * @param customerMobileNum the customerMobileNum to set
	 */
	public void setCustomerMobileNum(String customerMobileNum) {
		this.customerMobileNum = customerMobileNum;
	}

	/**
	 * @return the customerIdCardId
	 */
	public String getCustomerIdCardId() {
		return customerIdCardId;
	}

	/**
	 * @param customerIdCardId the customerIdCardId to set
	 */
	public void setCustomerIdCardId(String customerIdCardId) {
		this.customerIdCardId = customerIdCardId;
	}

	/**
	 * @return the customerLastLoginDateTime
	 */
	public Date getCustomerLastLoginDateTime() {
		return customerLastLoginDateTime;
	}

	/**
	 * @param customerLastLoginDateTime the customerLastLoginDateTime to set
	 */
	public void setCustomerLastLoginDateTime(Date customerLastLoginDateTime) {
		this.customerLastLoginDateTime = customerLastLoginDateTime;
	}

	/**
	 * @return the customerRealNameAuthStatus
	 */
	public String getCustomerRealNameAuthStatus() {
		return customerRealNameAuthStatus;
	}

	/**
	 * @param customerRealNameAuthStatus the customerRealNameAuthStatus to set
	 */
	public void setCustomerRealNameAuthStatus(String customerRealNameAuthStatus) {
		this.customerRealNameAuthStatus = customerRealNameAuthStatus;
	}

	/**
	 * @return the customerAddr
	 */
	public String getCustomerAddr() {
		return customerAddr;
	}

	/**
	 * @param customerAddr the customerAddr to set
	 */
	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	/**
	 * @return the openMobileNum
	 */
	public String getOpenMobileNum() {
		return openMobileNum;
	}

	/**
	 * @param openMobileNum the openMobileNum to set
	 */
	public void setOpenMobileNum(String openMobileNum) {
		this.openMobileNum = openMobileNum;
	}

	/**
	 * @return the openMobileManufacturer
	 */
	public String getOpenMobileManufacturer() {
		return openMobileManufacturer;
	}

	/**
	 * @param openMobileManufacturer the openMobileManufacturer to set
	 */
	public void setOpenMobileManufacturer(String openMobileManufacturer) {
		this.openMobileManufacturer = openMobileManufacturer;
	}

	/**
	 * @return the openState
	 */
	public String getOpenState() {
		return openState;
	}

	/**
	 * @param openState the openState to set
	 */
	public void setOpenState(String openState) {
		this.openState = openState;
	}

	/**
	 * @return the openMobileModel
	 */
	public String getOpenMobileModel() {
		return openMobileModel;
	}

	/**
	 * @param openMobileModel the openMobileModel to set
	 */
	public void setOpenMobileModel(String openMobileModel) {
		this.openMobileModel = openMobileModel;
	}

	/**
	 * @return the openMobileMobileOpt
	 */
	public String getOpenMobileMobileOpt() {
		return openMobileMobileOpt;
	}

	/**
	 * @param openMobileMobileOpt the openMobileMobileOpt to set
	 */
	public void setOpenMobileMobileOpt(String openMobileMobileOpt) {
		this.openMobileMobileOpt = openMobileMobileOpt;
	}

	/**
	 * @return the openLocation
	 */
	public String getOpenLocation() {
		return openLocation;
	}

	/**
	 * @param openLocation the openLocation to set
	 */
	public void setOpenLocation(String openLocation) {
		this.openLocation = openLocation;
	}

	/**
	 * @return the openDateTime
	 */
	public Date getOpenDateTime() {
		return openDateTime;
	}

	/**
	 * @param openDateTime the openDateTime to set
	 */
	public void setOpenDateTime(Date openDateTime) {
		this.openDateTime = openDateTime;
	}

	/**
	 * @return the salesChannel
	 */
	public String getSalesChannel() {
		return salesChannel;
	}

	/**
	 * @param salesChannel the salesChannel to set
	 */
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	/**
	 * @return the ogName
	 */
	public String getOgName() {
		return ogName;
	}

	/**
	 * @param ogName the ogName to set
	 */
	public void setOgName(String ogName) {
		this.ogName = ogName;
	}

	/**
	 * @return the ogCode
	 */
	public String getOgCode() {
		return ogCode;
	}

	/**
	 * @param ogCode the ogCode to set
	 */
	public void setOgCode(String ogCode) {
		this.ogCode = ogCode;
	}

	/**
	 * @return the lastDealDateTime
	 */
	public Date getLastDealDateTime() {
		return lastDealDateTime;
	}

	/**
	 * @param lastDealDateTime the lastDealDateTime to set
	 */
	public void setLastDealDateTime(Date lastDealDateTime) {
		this.lastDealDateTime = lastDealDateTime;
	}

	/**
	 * @return the dealCardLimit
	 */
	public Integer getDealCardLimit() {
		return dealCardLimit;
	}

	/**
	 * @param dealCardLimit the dealCardLimit to set
	 */
	public void setDealCardLimit(Integer dealCardLimit) {
		this.dealCardLimit = dealCardLimit;
	}

	/**
	 * @return the dealCardNo
	 */
	public List<String> getDealCardNo() {
		return dealCardNo;
	}

	/**
	 * @param dealCardNo the dealCardNo to set
	 */
	public void setDealCardNo(List<String> dealCardNo) {
		this.dealCardNo = dealCardNo;
	}

	/**
	 * @return the psam
	 */
	public String getPsam() {
		return psam;
	}

	/**
	 * @param psam the psam to set
	 */
	public void setPsam(String psam) {
		this.psam = psam;
	}

	/**
	 * @return the bindingTelNo
	 */
	public String getBindingTelNo() {
		return bindingTelNo;
	}

	/**
	 * @param bindingTelNo the bindingTelNo to set
	 */
	public void setBindingTelNo(String bindingTelNo) {
		this.bindingTelNo = bindingTelNo;
	}

	/**
	 * @return the orgCardRuleName
	 */
	public String getOrgCardRuleName() {
		return orgCardRuleName;
	}

	/**
	 * @param orgCardRuleName the orgCardRuleName to set
	 */
	public void setOrgCardRuleName(String orgCardRuleName) {
		this.orgCardRuleName = orgCardRuleName;
	}

	/**
	 * @return the ogId
	 */
	public String getOgId() {
		return ogId;
	}

	/**
	 * @param ogId the ogId to set
	 */
	public void setOgId(String ogId) {
		this.ogId = ogId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerInfoDTO [customerId=" + customerId
				+ ", customerRealname=" + customerRealname
				+ ", customerMobileNum=" + customerMobileNum
				+ ", customerIdCardId=" + customerIdCardId
				+ ", customerLastLoginDateTime=" + customerLastLoginDateTime
				+ ", customerRealNameAuthStatus=" + customerRealNameAuthStatus
				+ ", customerAddr=" + customerAddr + ", openMobileNum="
				+ openMobileNum + ", openMobileManufacturer="
				+ openMobileManufacturer + ", openState=" + openState
				+ ", openMobileModel=" + openMobileModel
				+ ", openMobileMobileOpt=" + openMobileMobileOpt
				+ ", openLocation=" + openLocation + ", openDateTime="
				+ openDateTime + ", salesChannel=" + salesChannel + ", ogId="
				+ ogId + ", ogName=" + ogName + ", ogCode=" + ogCode
				+ ", lastDealDateTime=" + lastDealDateTime + ", dealCardLimit="
				+ dealCardLimit + ", dealCardNo=" + dealCardNo + ", psam="
				+ psam + ", bindingTelNo=" + bindingTelNo
				+ ", orgCardRuleName=" + orgCardRuleName + "]";
	}
	
	
	
	
	
	
}
