/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.PageResultDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.0.0
 * @crate_date 2012-12-25
 */
@XmlRootElement(name = "RuleParams")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "", placement = Placement.BINDING)
public class RuleParamsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List< RuleParamDTO> data;
	private PageResultDTO pageResultDTO;
	/**
	 * @return the data
	 */
	public List<RuleParamDTO> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<RuleParamDTO> data) {
		this.data = data;
	}
	/**
	 * @return the pageResultDTO
	 */
	public PageResultDTO getPageResultDTO() {
		return pageResultDTO;
	}
	/**
	 * @param pageResultDTO the pageResultDTO to set
	 */
	public void setPageResultDTO(PageResultDTO pageResultDTO) {
		this.pageResultDTO = pageResultDTO;
	}
	
}
