package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeRequest")
public class ChangeRequest implements Serializable {
	private Long cardUserId; // 卡用户表主键
	private String cardId; // 用户卡ID

	private String changedCardId; // 新用户卡（可为null）
	private String changedPSAMNo; // 新psam卡号（可为null）





	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setChangedCardId(String changedCardId) {
		this.changedCardId = changedCardId;
	}

	public String getChangedCardId() {
		return changedCardId;
	}

	public void setChangedPSAMNo(String changedPSAMNo) {
		this.changedPSAMNo = changedPSAMNo;
	}

	public String getChangedPSAMNo() {
		return changedPSAMNo;
	}

	public void setCardUserId(Long cardUserId) {
		this.cardUserId = cardUserId;
	}

	public Long getCardUserId() {
		return cardUserId;
	}

}
