/**
 * 
 */
package com.lakala.mini.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2012-12-25
 */
@XmlRootElement(name = "RuleParamQuery")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "", placement = Placement.BINDING)
public class RuleParamQueryDTO extends BaseQueryDTO{
	
	private static final long serialVersionUID = 1L;
	private List<Long> id;
	private List<String> paramCode;
	private List<String> paramName;
	private List<Integer> ruleId;
	private List<String> paramValue;
	/**
	 * @return the id
	 */
	public List<Long> getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(List<Long> id) {
		this.id = id;
	}
	/**
	 * @return the paramCode
	 */
	public List<String> getParamCode() {
		return paramCode;
	}
	/**
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(List<String> paramCode) {
		this.paramCode = paramCode;
	}
	/**
	 * @return the paramName
	 */
	public List<String> getParamName() {
		return paramName;
	}
	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(List<String> paramName) {
		this.paramName = paramName;
	}
	/**
	 * @return the ruleId
	 */
	public List<Integer> getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(List<Integer> ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return the paramValue
	 */
	public List<String> getParamValue() {
		return paramValue;
	}
	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(List<String> paramValue) {
		this.paramValue = paramValue;
	}
	
}
