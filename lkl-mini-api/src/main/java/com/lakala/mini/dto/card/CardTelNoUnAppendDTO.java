package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * Mini绑定电话删除记录
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午03:16:50
 */
@XmlType(name="CardTelNoUnAppendDTO")
public class CardTelNoUnAppendDTO implements Serializable {

	private static final long serialVersionUID = 6765896363499811238L;
	private String id;
	private String cardInfoId;
	private String bindingTelNo;
	private Date date;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(String cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public String getBindingTelNo() {
		return bindingTelNo;
	}
	public void setBindingTelNo(String bindingTelNo) {
		this.bindingTelNo = bindingTelNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
