/**
 * 
 */
package com.lakala.mini.server.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 卡机构规则
 * @author qw
 * @CardOrgRule.java
 * @2011-6-24 下午03:50:37
 */
@Entity
@Table(name = "TAB_CARD_ORG_RULE")
public class CardOrgRule implements Serializable {

	
	private static final long serialVersionUID = -2433138677396110637L;
	public final static int RULE_ID_MOBILE_NUM_RANGE=10;
	public final static int RULE_ID_THRID_PARTY_APP=11;
	public final static int RULE_ID_ORG_PUBLIC=-1;
	/**
	 * 规则的ID标识
	 */
	@Id
	@Column(name="RULE_ID",length=10)
	private int ruleId;
	/**
	 * 规则编码，可为空或者和RULE_ID值一致
	 */
	@Column(name="RULE_CODE",length=20)
	private String ruleCode;
	/**
	 * 规则名称
	 */
	@Column(name="RULE_NAME")
	private String ruleName;
	/**
	 * 规则描述
	 */
	@Column(name="RULE_DESC")
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
