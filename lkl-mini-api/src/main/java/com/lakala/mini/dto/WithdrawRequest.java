package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithdrawRequest")
public class WithdrawRequest {
	private String deviceNo; // 终端号码
	private String PSAMNo; // PSAM卡号

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public String getPSAMNo() {
		return PSAMNo;
	}

	public void setPSAMNo(String pSAMNo) {
		PSAMNo = pSAMNo;
	}

	


}
