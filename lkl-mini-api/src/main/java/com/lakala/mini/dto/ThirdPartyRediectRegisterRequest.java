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
 * psam和userId需要选一种方式
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-11
 */
@XmlRootElement(name = "ThirdPartyRediectRegisterRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyRediectRegisterRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;

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
	@Size(min=1,max=200)
	private String orgUserId;
	
	/**
	 * lakala用户id。
	 */
	@NotNull
	@Size(min=1,max=20)
	private Long userId;
	
	/**
	 *机构扩展信息<br/>
	 *可选，预先保留 
	 */
	@Size(max=300)
	private String orgExInfo;
	
	


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

	
	
	
}
