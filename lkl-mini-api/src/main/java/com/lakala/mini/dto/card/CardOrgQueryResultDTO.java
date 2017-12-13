/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.PageResultDTO;

/**
 * @author QW
 * @CardOrgQueryResultDTO.java
 * @2011-4-20 下午02:45:41
 */
@XmlType(name = "CardOrgQueryResult")
@WSDLDocumentation(value = "MINI联名卡机构查询返回结果对象", placement = Placement.BINDING)
public class CardOrgQueryResultDTO implements Serializable {

	private static final long serialVersionUID = 3453535884495345381L;
	private List<CardOrgDTO> cardOrgDTOs;
	private PageResultDTO pageResultDTO;
	private String retCode;
	public List<CardOrgDTO> getCardOrgDTOs() {
		return cardOrgDTOs;
	}
	public void setCardOrgDTOs(List<CardOrgDTO> cardOrgDTOs) {
		this.cardOrgDTOs = cardOrgDTOs;
	}
	public PageResultDTO getPageResultDTO() {
		return pageResultDTO;
	}
	public void setPageResultDTO(PageResultDTO pageResultDTO) {
		this.pageResultDTO = pageResultDTO;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
}
