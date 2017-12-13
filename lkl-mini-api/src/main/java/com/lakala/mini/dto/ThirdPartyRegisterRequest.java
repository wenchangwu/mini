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
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-11
 */
@XmlRootElement(name = "ThirdPartyRegisterRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyRegisterRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 20)
	private String psamNo;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String orgId;
	
	@NotNull
	@Size(min=1,max=20)
	private String orgUserId;
	
	
	/**
	 * lakala用户id。选填
	 */
	private Long userId;

	/**
	 * @return the psamNo
	 */
	public String getPsamNo() {
		return psamNo;
	}

	/**
	 * @param psamNo
	 *            the psamNo to set
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
	 * @param orgUserId
	 *            the orgUserId to set
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
