/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.dto.BaseRequestDTO;

/**
 * 第三方用户绑定请求
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
@XmlRootElement(name = "ThirdPartyVerifyBindRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyVerifyBindRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * psam号。可选
	 */
	private String psamNo;

	/**
	 * 机构号
	 */
	private String orgId;

	/**
	 * 机构用户号
	 */
	private String orgUserId;
	
	/**
	 * 用户id。可选。
	 */
	private Long userId;

	/**
	 * @return the psamNo
	 */
	public String getPsamNo() {
		return psamNo;
	}

	/**
	 * @param psamNo the psamNo to set
	 */
	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}


	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgUserId
	 */
	public String getOrgUserId() {
		return orgUserId;
	}

	/**
	 * @param orgUserId the orgUserId to set
	 */
	public void setOrgUserId(String orgUserId) {
		this.orgUserId = orgUserId;
	}

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
	
	
}
