/**
 * 
 */
package com.lakala.mini.server.core.domain;

import java.util.Date;

/**
 * @author ray
 * 
 */
public class CardOrg {
	
	private Long id;
	
	private String code;
	
	private String name;
	
	/**
	 * 移机规则
	 */
	private int movingRule;
	/**
	 * 机构状态
	 */
	private int orgState;

	/**
	 * 移机审核类型
	 */
	private int movAuditingType;
	/**
	 * 开通审核类型
	 */
	private int openAuditingType;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 失效时间
	 */
	private Date ineffectiveDate;

	private Integer version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMovingRule() {
		return movingRule;
	}

	public void setMovingRule(int movingRule) {
		this.movingRule = movingRule;
	}

	public int getOrgState() {
		return orgState;
	}

	public void setOrgState(int orgState) {
		this.orgState = orgState;
	}

	public int getMovAuditingType() {
		return movAuditingType;
	}

	public void setMovAuditingType(int movAuditingType) {
		this.movAuditingType = movAuditingType;
	}

	public int getOpenAuditingType() {
		return openAuditingType;
	}

	public void setOpenAuditingType(int openAuditingType) {
		this.openAuditingType = openAuditingType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getIneffectiveDate() {
		return ineffectiveDate;
	}

	public void setIneffectiveDate(Date ineffectiveDate) {
		this.ineffectiveDate = ineffectiveDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
