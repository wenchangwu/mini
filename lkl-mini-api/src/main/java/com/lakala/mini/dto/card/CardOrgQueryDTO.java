/**
 * 
 */
package com.lakala.mini.dto.card;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 * 联名卡机构查询参数对象
 * @author QW
 * @CardOrgQueryDTO.java
 * @2011-4-20 下午02:35:44
 */
@XmlType(name = "CardOrgQuery")
@WSDLDocumentation(value = "MINI联名卡机构查询参数", placement = Placement.BINDING)
public class CardOrgQueryDTO extends BaseQueryDTO   {
	
	private Long[] ids;
	
	private String[] codes;
	
	private String[] names;
	
	/**
	 * 移机规则
	 *@since 1.4.0 
	 */
	private List<Integer> movingRule;
	
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String[] getCodes() {
		return codes;
	}
	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	/**
	 * @return the movingRule
	 */
	public List<Integer> getMovingRule() {
		return movingRule;
	}
	/**
	 * @param movingRule the movingRule to set
	 */
	public void setMovingRule(List<Integer> movingRule) {
		this.movingRule = movingRule;
	}
	
}
