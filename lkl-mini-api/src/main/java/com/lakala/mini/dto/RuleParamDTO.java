/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.0.0
 * @crate_date 2012-12-25
 */
@XmlType(name = "RuleParam")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "", placement = Placement.BINDING)
public class RuleParamDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String paramCode;
	private String paramName;
	private String paramDesc;
	private String paramValue;
	private Integer ruleId;
	private String ruleName;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}
	/**
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * @return the paramDesc
	 */
	public String getParamDesc() {
		return paramDesc;
	}
	/**
	 * @param paramDesc the paramDesc to set
	 */
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}
	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * @return the ruleId
	 */
	public Integer getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
}
