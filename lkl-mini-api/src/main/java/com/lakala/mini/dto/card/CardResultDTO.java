/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;

/**
 * @author QW
 * @CardResultDTO.java
 * @2011-4-12 下午04:57:08
 */
public class CardResultDTO implements Serializable {

	private static final long serialVersionUID = 8642388251929157375L;
	
	private CardResourceDTO cardResourceDTO;
	private CardInfoDTO cardInfoDTO;
	private CardUserDTO cardUserDTO;
	private CardOrgDTO cardOrgDTO;

	public CardOrgDTO getCardOrgDTO() {
		return cardOrgDTO;
	}
	public void setCardOrgDTO(CardOrgDTO cardOrgDTO) {
		this.cardOrgDTO = cardOrgDTO;
	}
	public CardResourceDTO getCardResourceDTO() {
		return cardResourceDTO;
	}
	public void setCardResourceDTO(CardResourceDTO cardResourceDTO) {
		this.cardResourceDTO = cardResourceDTO;
	}
	public CardInfoDTO getCardInfoDTO() {
		return cardInfoDTO;
	}
	public void setCardInfoDTO(CardInfoDTO cardInfoDTO) {
		this.cardInfoDTO = cardInfoDTO;
	}
	public CardUserDTO getCardUserDTO() {
		return cardUserDTO;
	}
	public void setCardUserDTO(CardUserDTO cardUserDTO) {
		this.cardUserDTO = cardUserDTO;
	}

}
