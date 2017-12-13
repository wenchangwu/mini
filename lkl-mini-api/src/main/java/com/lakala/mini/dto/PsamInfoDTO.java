package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leijiajian@lakala.com
 * @since 1.5.0
 */
@XmlRootElement(name="PsamInfo")
public class PsamInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserMiniInfoDTO miniUserInfo;
	private String psamNo;
	private String psamStatus;
	private String bindUserId;

	public UserMiniInfoDTO getMiniUserInfo() {
		return miniUserInfo;
	}

	public void setMiniUserInfo(UserMiniInfoDTO miniUserInfo) {
		this.miniUserInfo = miniUserInfo;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public String getPsamStatus() {
		return psamStatus;
	}

	public void setPsamStatus(String psamStatus) {
		this.psamStatus = psamStatus;
	}

	public String getBindUserId() {
		return bindUserId;
	}

	public void setBindUserId(String bindUserId) {
		this.bindUserId = bindUserId;
	}
	

}
