/**
 * @author ray
 * @filename: MiniBindUserCardRequest.java
 * @create date: 下午03:37:26
 */
package com.lakala.mini.wrap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.lakala.core.wrap.BaseRequestType;

@XmlType(name = "MiniBindUserCardRequest")
public class MiniBindUserCardRequest extends BaseRequestType {

	private static final long serialVersionUID = -2559260773691600314L;
	@XmlElement(nillable = false, required = true)
	private long uid;
	@XmlElement(nillable = false, required = true)
	private String password;
	@XmlElement(nillable = false, required = true)
	private String userCardNum;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCardNum() {
		return userCardNum;
	}

	public void setUserCardNum(String userCardNum) {
		this.userCardNum = userCardNum;
	}

}
