/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.lakala.core.dto.BaseRequestDTO;

/**
 * 第三方合作商户绑定信息查询请求
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-13
 */
@XmlRootElement(name="ThirdPartyBindInfoQueryRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value="第三方合作商户绑定信息查询请求")
public class ThirdPartyBindInfoQueryRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	
	private String orgId;
	
	private String orgUserId;

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
