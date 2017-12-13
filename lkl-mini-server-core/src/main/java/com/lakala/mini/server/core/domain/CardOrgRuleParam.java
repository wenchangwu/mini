/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 规则扩展参数
 * @author QW
 *
 */
@Entity
@Table(name = "TAB_CARD_ORG_RULE_PARAM")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_CARD_ORG_RULE_PARAM", initialValue = 10000000)
@NamedQueries(
	{
		@NamedQuery(name="batchRemove",query="delete from CardOrgRuleParam cardOrgRuleParam where cardOrgRuleParam.id in (:ids) ")
		,@NamedQuery(name="removeByCodeAndRuleId",query="delete from CardOrgRuleParam cardOrgRuleParam where cardOrgRuleParam.paramCode = :paramCode and cardOrgRuleParam.ruleId=:ruleId ")
		,@NamedQuery(name="batchUpdateNameAndDesc",query="update CardOrgRuleParam p set p.paramName=:paramName, p.paramDesc=:paramDesc  where p.paramCode = :paramCode and p.ruleId=:ruleId and p.paramValue in (:paramValue) ")
		,@NamedQuery(name="getByRuleIdAndCodeAndValues",query="from CardOrgRuleParam p  where p.paramCode = :paramCode and p.ruleId=:ruleId and p.paramValue in (:paramValue) ")
	}
)
public class CardOrgRuleParam extends IdEntity<Long> implements Serializable {

	private static final long serialVersionUID = -1835603257273790671L;
	
	
	
	@Column(name="RULE_ID",length=10)
	private int ruleId;
	@Column(name="PARAM_CODE")
	private String paramCode;
	@Column(name="PARAM_NAME")
	private String paramName;
	@Column(name="PARAM_VALUE")
	private String paramValue;
	@Column(name="PARAM_DESC")
	private String paramDesc;
	
	@ManyToOne
	@JoinColumn(insertable=false,updatable=false ,name="RULE_ID")
	private CardOrgRule rule;
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	@Override
	public String toString() {
		return "CardOrgRuleParam [ruleId=" + ruleId + ", paramCode="
				+ paramCode + ", paramName=" + paramName + ", paramValue="
				+ paramValue + ", paramDesc=" + paramDesc + ", getId()="
				+ getId() + "]";
	}
	
	/**
	 * @param ruleId
	 * @param paramCode
	 * @param paramName
	 * @param paramValue
	 * @param paramDesc
	 */
	public CardOrgRuleParam(int ruleId, String paramCode, String paramName,
			String paramValue, String paramDesc) {
		super();
		this.ruleId = ruleId;
		this.paramCode = paramCode;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.paramDesc = paramDesc;
	}
	/**
	 * 
	 */
	public CardOrgRuleParam() {
		super();
	}
	/**
	 * @return the rule
	 */
	public CardOrgRule getRule() {
		return rule;
	}
	/**
	 * @param rule the rule to set
	 */
	public void setRule(CardOrgRule rule) {
		this.rule = rule;
	}

	
}
