/**
 * 
 */
package com.lakala.mini.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.dto.BaseRequestDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-11
 */
@XmlRootElement(name="ThirdPartyLoginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyLoginRequest extends BaseRequestDTO {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * psam。选填
	 */
	@Size(max=20)
	private String psamNo;
	/**
	 * 机构号。必填
	 */
	@NotNull
	@Size(min=1,max=20)
	private String orgId;
	/**
	 * 机构用户号。必填
	 */
	@NotNull
	@Size(min=1,max=20)
	private String orgUserId;
	
	/**
	 *机构扩展信息<br/>
	 *可选，预先保留 
	 */
	@Size(max=300)
	private String orgExInfo;

	
	/**
	 * lakal用户号。选填
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
	 * @return the orgExInfo
	 */
	public String getOrgExInfo() {
		return orgExInfo;
	}

	/**
	 * @param orgExInfo the orgExInfo to set
	 */
	public void setOrgExInfo(String orgExInfo) {
		this.orgExInfo = orgExInfo;
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
