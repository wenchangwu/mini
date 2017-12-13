/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseRequestDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2013-1-9
 */
@XmlRootElement(name = "QueryUserInfoRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "", placement = Placement.BINDING)
public class QueryUserInfoRequest extends BaseRequestDTO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 开通手机号
	 */
	private String openMobileNum;
	/**
	 * PSAM
	 */
	private String psam;
	/**
	 * 客户手机号
	 */
	private String customerMobileNum;
	/**
	 * 绑定线路号码
	 */
	private String bindingTelNo;
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
	
	
}
