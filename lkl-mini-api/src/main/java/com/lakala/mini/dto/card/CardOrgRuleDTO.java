package com.lakala.mini.dto.card;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

/**
 * 
 * @author qw
 * @CardOrgRuleDTO.java
 * @2011-6-29 下午05:06:34
 */
@XmlType(name = "CardOrgRuleDTO")
@WSDLDocumentation(value = "MINI联名卡机构规则描述对象", placement = Placement.BINDING)
public class CardOrgRuleDTO implements Serializable{

	private static final long serialVersionUID = 8251835118075817285L;
	/**
	 * 规则的ID标识
	 */
	private int ruleId;
	/**
	 * 规则编码，可为空或者和RULE_ID值一致
	 */
	private String ruleCode;
	/**
	 * 规则名称
	 */
	private String ruleName;
	/**
	 * 规则描述
	 */
	private String ruleDesc;
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	
}
