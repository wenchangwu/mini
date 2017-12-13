package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


/**
 * 查询mini用户绑定的电话号码的返回结果
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午03:23:52
 */
@XmlType(name="CardTelNoQueryResultDTO")
public class CardTelNoQueryResultDTO implements Serializable{

	private static final long serialVersionUID = 3507674076819972072L;
	
	private List<CardTelNoDTO> cardTelNos;
	private List<CardTelNoAppendDTO> cardTelNoAppends;
	private List<CardTelNoUnAppendDTO> cardTelNoUnAppends;
	@XmlElementWrapper(name="cardTelNos")
	@XmlElement(name="CardTelNoDTO")
	public List<CardTelNoDTO> getCardTelNos() {
		return cardTelNos;
	}

	public void setCardTelNos(List<CardTelNoDTO> cardTelNos) {
		this.cardTelNos = cardTelNos;
	}
	@XmlElementWrapper(name="cardTelNoAppends")
	@XmlElement(name="CardTelNoAppendDTO")
	public List<CardTelNoAppendDTO> getCardTelNoAppends() {
		return cardTelNoAppends;
	}

	public void setCardTelNoAppends(List<CardTelNoAppendDTO> cardTelNoAppends) {
		this.cardTelNoAppends = cardTelNoAppends;
	}
	@XmlElementWrapper(name="cardTelNoUnAppends")
	@XmlElement(name="cardTelNoUnAppendDTO")
	public List<CardTelNoUnAppendDTO> getCardTelNoUnAppends() {
		return cardTelNoUnAppends;
	}

	public void setCardTelNoUnAppends(List<CardTelNoUnAppendDTO> cardTelNoUnAppends) {
		this.cardTelNoUnAppends = cardTelNoUnAppends;
	}
	
	

}
