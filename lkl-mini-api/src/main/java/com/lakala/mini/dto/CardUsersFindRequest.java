/**
 * 
 */
package com.lakala.mini.dto;

import com.lakala.core.support.query.Page;

/**
 * @author ray
 * 
 */
public class CardUsersFindRequest {
	private Page page;

	private String userName;
	private String userCardNo;
	private String psamNo;
	private String mobile;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
