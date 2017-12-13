/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.List;

import com.lakala.core.dto.PageResultDTO;

/**
 * 用户卡查询应答的WebService对象
 * @author QW
 * @CardQueryResultDTO.java
 * @2011-4-12 下午04:36:56
 */
public class CardQueryResultDTO implements Serializable {

	private static final long serialVersionUID = -4078371138163664061L;
	private List<CardResultDTO> cardResultDTOs;
	private PageResultDTO pageResultDTO;
	private String retCode;
	public List<CardResultDTO> getCardResultDTOs() {
		return cardResultDTOs;
	}
	public void setCardResultDTOs(List<CardResultDTO> cardResultDTOs) {
		this.cardResultDTOs = cardResultDTOs;
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
