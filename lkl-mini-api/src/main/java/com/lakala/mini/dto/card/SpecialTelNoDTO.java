/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * @author 全伟(QW)
 * @date 2012-8-2
 * @time 下午04:23:10
 */
@XmlType(name="SpecialTelNo")
public class SpecialTelNoDTO implements Serializable {

	private static final long serialVersionUID = -8538249346420349568L;

	private String id; 
	
	private String areaNo;

	private String telNo;
	/**
	 * 0标识为总机，1表示为特服号码
	 */

	private int type;
	
	/**
	 * 0表示启用，1表示禁用
	 */
	private int status;

	private String remark;

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
