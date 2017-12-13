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
@Table(name="TAB_CARD_INFO_UNAPPEND_TEL")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_TEL_UNAPPEND", initialValue = 10000000)
public class CardTelNoUnAppend extends IdEntity<Long>  {

	private static final long serialVersionUID = 2807559627554430667L;
	@Column(name="CARD_INFO_ID")
	private long cardInfoId;
	@Column(name="BINDING_TEL_NO")
	private String bindingTelNo;
	@Column(name="INSERT_TIME")
	private Date date;
	@Column(name="STATUS")
	private int status;

	
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

	
}
