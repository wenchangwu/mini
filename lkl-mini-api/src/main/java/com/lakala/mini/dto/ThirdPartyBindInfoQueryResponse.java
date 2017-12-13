/**
 * 
 */
package com.lakala.mini.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;

/**
 * 第三方合作商户绑定信息查询响应
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-13
 */
@WSDLDocumentation(value = "第三方合作商户绑定信息查询响应")
@XmlRootElement(name = "ThirdPartyBindInfoQueryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyBindInfoQueryResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;
	private List<UserMiniInfoDTO> userMiniInfos;


	/**
	 * @return the userMiniInfos
	 */
	public List<UserMiniInfoDTO> getUserMiniInfos() {
		return userMiniInfos;
	}

	/**
	 * @param userMiniInfos
	 *            the userMiniInfos to set
	 */
	public void setUserMiniInfos(List<UserMiniInfoDTO> userMiniInfos) {
		this.userMiniInfos = userMiniInfos;
	}

}
