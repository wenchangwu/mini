/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * @author 全伟(QW)
 * @date 2012-4-13
 * @time 下午03:51:47
 */
@XmlType(name="ApplyMiniModifyDTO")
public class ApplyMiniModifyDTO implements Serializable{

	private static final long serialVersionUID = 4268900902685446638L;

	private long uid;
	private long cardInfoId;
	private String oldTelNo;
	private String newTelNo;
	private int status;
	private String statusDesc;
	private String content;
	private Date applyDate;
	private Date auditDate;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public String getOldTelNo() {
		return oldTelNo;
	}
	public void setOldTelNo(String oldTelNo) {
		this.oldTelNo = oldTelNo;
	}
	public String getNewTelNo() {
		return newTelNo;
	}
	public void setNewTelNo(String newTelNo) {
		this.newTelNo = newTelNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	
}
