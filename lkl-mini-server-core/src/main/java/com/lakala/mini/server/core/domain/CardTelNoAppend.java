/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午03:51:38
 */
@Entity
@Table(name="TAB_CARD_INFO_APPEND_TEL")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_TEL_APPEND", initialValue = 10000000)
public class CardTelNoAppend extends IdEntity<Long>  {

	private static final long serialVersionUID = 2807559627554430667L;
	@Column(name="CARD_INFO_ID")
	private long cardInfoId;
	@Column(name="BINDING_TEL_NO")
	private String bindingTelNo;
	@Column(name="INSERT_TIME")
	private Date date;
	@Column(name="STATUS")
	private int status;
	@Column(name="CARD_TELNO_UNAPPEND_ID")
	private Long cardTelNoUnAppendId;
	
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getCardTelNoUnAppendId() {
		return cardTelNoUnAppendId;
	}
	public void setCardTelNoUnAppendId(Long cardTelNoUnAppendId) {
		this.cardTelNoUnAppendId = cardTelNoUnAppendId;
	}
	@Override
	public String toString() {
		return "CardTelNoAppend [cardInfoId=" + cardInfoId + ", bindingTelNo="
				+ bindingTelNo + ", date=" + date + ", status=" + status
				+ ", cardTelNoUnAppendId=" + cardTelNoUnAppendId + "]";
	}		
	
}
