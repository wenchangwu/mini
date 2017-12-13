/**
 * 
 */
package com.lakala.mini.dto.card;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 * 用户卡查询DTO参数
 * @author QW
 * @CardQueryDTO.java
 * @2011-4-8 下午04:02:42
 */
@XmlType(name = "CardQuery")
@WSDLDocumentation(value = "MINI用户卡信息查询参数", placement = Placement.BINDING)
public class CardQueryDTO extends BaseQueryDTO {
	private String[] cardNos;
	private String[] cardOrgCodes;
	private String[] cardOrgNames;
	private String[] psamNos;
	private String[] mobilePhones;
	private String[] telNos;
	private String[] telAreaNos;
	private Integer[] status;
	private Integer[] types;
	public String[] getCardNos() {
		return cardNos;
	}
	public void setCardNos(String[] cardNos) {
		this.cardNos = cardNos;
	}
	public String[] getCardOrgCodes() {
		return cardOrgCodes;
	}
	public void setCardOrgCodes(String[] cardOrgCodes) {
		this.cardOrgCodes = cardOrgCodes;
	}
	public String[] getCardOrgNames() {
		return cardOrgNames;
	}
	public void setCardOrgNames(String[] cardOrgNames) {
		this.cardOrgNames = cardOrgNames;
	}
	public String[] getPsamNos() {
		return psamNos;
	}
	public void setPsamNos(String[] psamNos) {
		this.psamNos = psamNos;
	}
	public String[] getMobilePhones() {
		return mobilePhones;
	}
	public void setMobilePhones(String[] mobilePhones) {
		this.mobilePhones = mobilePhones;
	}
	public String[] getTelNos() {
		return telNos;
	}
	public void setTelNos(String[] telNos) {
		this.telNos = telNos;
	}
	public String[] getTelAreaNos() {
		return telAreaNos;
	}
	public void setTelAreaNos(String[] telAreaNos) {
		this.telAreaNos = telAreaNos;
	}
	public Integer[] getStatus() {
		return status;
	}
	public void setStatus(Integer[] status) {
		this.status = status;
	}
	public Integer[] getTypes() {
		return types;
	}
	public void setTypes(Integer[] types) {
		this.types = types;
	}
	@Override
	public String toString() {
		return "CardQueryDTO [cardNos=" + Arrays.toString(cardNos) + ", cardOrgCodes=" + Arrays.toString(cardOrgCodes) + ", cardOrgNames=" + Arrays.toString(cardOrgNames)
				+ ", psamNos=" + Arrays.toString(psamNos) + ", mobilePhones=" + Arrays.toString(mobilePhones) + ", telNos=" + Arrays.toString(telNos) + ", telAreaNos="
				+ Arrays.toString(telAreaNos) + ", status=" + Arrays.toString(status) + ", types=" + Arrays.toString(types) + "]";
	}

}
