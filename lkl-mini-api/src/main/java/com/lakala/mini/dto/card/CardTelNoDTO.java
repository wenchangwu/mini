/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;



/**
 * CardTelNoDTO Web Service使用对象
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午02:32:22
 */
@XmlType(name="CardTelNoDTO")
public class CardTelNoDTO implements Serializable {

	private static final long serialVersionUID = 8660103632898765506L;
	
	private String cardInfoId;
	private String cardNo;
	private String psamNo;
	private String telNo;
	private boolean using;
	private Date date;
	
	
	public String getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(String cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPsamNo() {
		return psamNo;
	}
	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public boolean isUsing() {
		return using;
	}
	public void setUsing(boolean using) {
		this.using = using;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
