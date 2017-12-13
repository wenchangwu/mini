/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * @author 全伟(QW)
 * @date 2012-6-26
 * @time 下午03:06:16
 */
@XmlType(name="CommonRequestDTO")
public class UDServiceRequestDTO implements Serializable {

	private static final long serialVersionUID = -6877532965765707784L;

	private String psamNo;
	
	private String passwd;
	
	private String areaNo;
	
	private String oldPasswd;
	
	private String newPasswd;
	
	private long uid;
	
	private int cardType;
	
	private String bindingNo;
	
	/**
	 * 第三方机构交易时需要提供
	 * @since 1.4.0
	 */
	private String orgId;


	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getBindingNo() {
		return bindingNo;
	}

	public void setBindingNo(String bindingNo) {
		this.bindingNo = bindingNo;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UDServiceRequestDTO [psamNo=" + psamNo + ", passwd=" + passwd
				+ ", areaNo=" + areaNo + ", oldPasswd=" + oldPasswd
				+ ", newPasswd=" + newPasswd + ", uid=" + uid + ", cardType="
				+ cardType + ", bindingNo=" + bindingNo + ", orgId=" + orgId
				+ "]";
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


	
}
