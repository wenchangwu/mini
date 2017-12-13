/**
 * 
 */
package com.lakala.mini.server.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 全伟(QW)
 * @date 2012-1-9
 * @time 下午12:43:04
 */
@Entity
@Table(name="VIEW_CARD_TELNO")
public class ViewCardTelNo implements Serializable {

	private static final long serialVersionUID = 1825804946304780059L;
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="CARD_INFO_ID")
	private Long cardInfoId;
	@Column(name="TEL_NO")
	private String telNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(Long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

}
