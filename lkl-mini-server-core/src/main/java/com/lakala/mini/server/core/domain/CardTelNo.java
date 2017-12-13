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
 * 用户卡绑定的电话号码关系
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午03:41:25
 */
@Entity
@Table(name="VIEW_CARD_TELNO")
public class CardTelNo implements Serializable {

	private static final long serialVersionUID = 4351222527150866791L;
	@Id
	@Column(name="ID")
	public String id;
	@Column(name="CARD_INFO_ID")
	public long cardInfoId;
	@Column(name="TEL_NO")
	public String telNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	
}
