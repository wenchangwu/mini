/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

/**
 * @author qw
 * @CardOrgRuleResultDTO.java
 * @2011-6-30 上午10:28:57
 */
@XmlType(name = "CardOrgRuleResultDTO")
@WSDLDocumentation(value = "MINI联名卡机构规则查询结果对象", placement = Placement.BINDING)
public class CardOrgRuleResultDTO implements Serializable {

	private static final long serialVersionUID = -4954631661198301364L;
	private List<CardOrgRuleDTO> cardOrgRuleDTOs;
	private String retCode;
	public List<CardOrgRuleDTO> getCardOrgRuleDTOs() {
		return cardOrgRuleDTOs;
	}
	public void setCardOrgRuleDTOs(List<CardOrgRuleDTO> cardOrgRuleDTOs) {
		this.cardOrgRuleDTOs = cardOrgRuleDTOs;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
}
