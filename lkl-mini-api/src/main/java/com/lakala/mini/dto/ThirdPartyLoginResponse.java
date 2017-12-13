/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-13
 */
@XmlRootElement(name="ThirdPartyLoginResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyLoginResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;
	private String lineNo;
	private Long userId;
	private String psamNo;
	private String orgId;
	private String orgUserId;
	/**
	 * @return the lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
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
	
}
