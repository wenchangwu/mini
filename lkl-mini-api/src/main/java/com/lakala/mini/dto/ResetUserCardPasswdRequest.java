/**
 * 
 */
package com.lakala.mini.dto;

/**
 * @author ray
 *
 */
public class ResetUserCardPasswdRequest {
	private String userCardNum;
	private String mobileNum;
	public String getUserCardNum() {
		return userCardNum;
	}
	public void setUserCardNum(String userCardNum) {
		this.userCardNum = userCardNum;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	
	
}
