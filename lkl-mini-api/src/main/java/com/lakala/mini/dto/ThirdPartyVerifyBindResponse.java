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
 * @crate_date 2013-3-12
 */
@XmlRootElement(name = "ThirdPartyVerifyBindResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyVerifyBindResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否已绑定。
	 */
	private Boolean bind;
	/**
	 * 线路号。
	 */
	private String lineNo;
	/**
	 * lakala用户号
	 */
	private Long userId;
	/**
	 * psamNo
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
	 * 
	 */
	public ThirdPartyVerifyBindResponse() {
		super();
	}

	/**
	 * @param b
	 */
	public ThirdPartyVerifyBindResponse(Boolean bind) {
		this();
		this.bind=bind;
	}

	/**
	 * @return the bind
	 */
	public Boolean getBind() {
		return bind;
	}

	/**
	 * @param bind the bind to set
	 */
	public void setBind(Boolean bind) {
		this.bind = bind;
	}

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
