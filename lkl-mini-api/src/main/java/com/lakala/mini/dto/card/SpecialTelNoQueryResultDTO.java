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
 * @author 全伟(QW)
 * @date 2012-8-2
 * @time 下午04:27:26
 */
@XmlType(name = "SpecialTelNoQueryResult")
@WSDLDocumentation(value = "特服号码查询返回结果对象", placement = Placement.BINDING)

public class SpecialTelNoQueryResultDTO implements Serializable {

	private static final long serialVersionUID = 8037560390728339531L;
	private List<SpecialTelNoDTO> specialTelNoDTOs;
	private PageResultDTO pageResultDTO;
	private String retCode;
	public List<SpecialTelNoDTO> getSpecialTelNoDTOs() {
		return specialTelNoDTOs;
	}
	public void setSpecialTelNoDTOs(List<SpecialTelNoDTO> specialTelNoDTOs) {
		this.specialTelNoDTOs = specialTelNoDTOs;
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
