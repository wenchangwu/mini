/*
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
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name="TransferMiniQueryResult")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "迷你移机申请单查询结果", placement = Placement.BINDING)
public class TransferMiniQueryResultDTO  implements Serializable {

	private static final long serialVersionUID = 4978630436929540463L;
	private List< TransferMiniOrderDTO> datas;
	private PageResultDTO pageResultDTO;
	/**
	 * @return the datas
	 */
	public List<TransferMiniOrderDTO> getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<TransferMiniOrderDTO> datas) {
		this.datas = datas;
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
