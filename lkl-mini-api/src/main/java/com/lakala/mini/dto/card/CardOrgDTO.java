/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * MINI发卡机构联名标识
 * @author QW
 * @CardOrgDTO.java
 * @2011-4-20 下午02:21:15
 */
@XmlType(name="CardOrg")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardOrgDTO implements Serializable {

	private static final long serialVersionUID = -4878754523093763121L;
	private Long id;
	private String code;
	private String name;
	private Integer movingRule;
	private Integer orgState;
	private Integer movAuditingType;
	private Integer openAuditingType;
	private String desc;
	private Date ineffectiveDate;
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getMovingRule() {
		return movingRule;
	}

	public void setMovingRule(Integer movingRule) {
		this.movingRule = movingRule;
	}

	public Integer getOrgState() {
		return orgState;
	}

	public void setOrgState(Integer orgState) {
		this.orgState = orgState;
	}

	public Integer getMovAuditingType() {
		return movAuditingType;
	}

	public void setMovAuditingType(Integer movAuditingType) {
		this.movAuditingType = movAuditingType;
	}

	public Integer getOpenAuditingType() {
		return openAuditingType;
	}

	public void setOpenAuditingType(Integer openAuditingType) {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
