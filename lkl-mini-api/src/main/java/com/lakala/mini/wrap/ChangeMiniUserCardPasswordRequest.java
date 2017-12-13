/**
 * @author ray
 * @filename: ChangeMiniUserCardPasswordRequest.java
 * @create date: 下午03:31:11
 */
package com.lakala.mini.wrap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.lakala.core.wrap.BaseRequestType;

@XmlType(name = "ChangeMiniUserCardPasswordRequest")
public class ChangeMiniUserCardPasswordRequest extends BaseRequestType {

	private static final long serialVersionUID = 2226747144469261495L;
	@XmlElement(nillable = false, required = true)
	private long uid;
	@XmlElement(nillable = false, required = true)
	private String userCardNum;
	@XmlElement(nillable = false, required = true)
	private String oldPassword;
	@XmlElement(nillable = false, required = true)
	private String newPassword;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserCardNum() {
		return userCardNum;
	}

	public void setUserCardNum(String userCardNum) {
		this.userCardNum = userCardNum;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
