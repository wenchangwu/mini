/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 特殊号码列表
 * @author 全伟(QW)
 * @date 2012-7-31
 * @time 下午03:21:52
 */
@Entity

@Table(name = "TAB_SPECIAL_TELNO")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_SPECIAL_TELNO", initialValue = 10000000)
public class SpecialTelNo extends IdEntity<Long> {

	private static final long serialVersionUID = 7478667859664808134L;
	@Column(name = "AREA_NO")
	private String areaNo;
	@Column(name = "TEL_NO",unique=true)
	private String telNo;
	/**
	 * 0标识为总机，1表示为特服号码
	 */
	@Column(name = "TYPE")
	private int type;
	/**
	 * 0表示为启动，1表示为禁用
	 */
	@Column(name = "STATUS")
	private int status;
	@Column(name = "REMARK")
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
