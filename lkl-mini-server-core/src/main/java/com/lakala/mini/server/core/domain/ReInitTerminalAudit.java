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
 * 转赠审计日志
 * @author leijiajian@lakala.com
 * @since 1.3.1
 * @crate_date 2013-3-4
 */
@Entity
@Table(name="TAB_INIT_TERMINAL_AUDIT")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_TAB_INIT_TERMINAL_AUDIT",allocationSize=1,initialValue=10000000)
public class ReInitTerminalAudit extends IdEntity<Long>{

	
	private static final long serialVersionUID = 1L;
	@Column(name="OLD_CARD_INFO_ID" ,length=20)
	private Long oldCardInfoId;
	@Column(name="NEW_CARD_INFO_ID" ,length=20)
	private Long newCardInfoId;
	@Column(name="PSAM_NO" ,length=32)
	private String psamNo;
	@Column(name="OLD_CARD_USER_ID" ,length=20)
	private Long oldCardUserId;
	@Column(name="NEW_CARD_USER_ID" ,length=20)
	private Long newCardUserId;
	@Column(name="LOG_DATE")
	private Date logDate;
	@Column(name="STATUS")
	private String status;
	

	/**
	 * 
	 */
	public ReInitTerminalAudit() {
		super();
		this.logDate=new Date();
	}

	/**
	 * @param olderCardInfo
	 * @param newCardInfo
	 */
	public ReInitTerminalAudit(CardInfo oldCardInfo, CardInfo newCardInfo, String psamNo, String status) {
		this();
		if(oldCardInfo!=null){
			this.oldCardInfoId=oldCardInfo.getId();
			CardUser cardUser = oldCardInfo.getCardUser();
			if(cardUser!=null){
				this.oldCardUserId=cardUser.getId();
			}
			
		}
		if(newCardInfo!=null){
			this.newCardInfoId=newCardInfo.getId();
			CardUser cardUser = newCardInfo.getCardUser();
			if(cardUser!=null){
				this.newCardUserId=cardUser.getId();
			}
			
		}
		this.psamNo=psamNo;
		this.setStatus(status);
	}
	/**
	 * @param olderCardInfo
	 * @param newCardInfo
	 */
	public ReInitTerminalAudit(CardInfo oldCardInfo, CardInfo newCardInfo, String psamNo, String status, Date logDate) {
		this(oldCardInfo,newCardInfo,psamNo,status);
		setLogDate(logDate);
	}

	/**
	 * @return the newCardInfo
	 */

	/**
	 * @return the psamNo
	 */
	public String getPsamNo() {
		return psamNo;
	}

	/**
	 * @param psamNo the psamNo to set
	 */
	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}



	/**
	 * @return the logDate
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setLogDate(Date logDate) {
		if(logDate!=null){
			this.setLogDate(logDate);
		}
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		if(status!=null){
			if(status.length()>100){
				status=status.substring(0,100);
			}
			this.status = status;
		}
	}

	/**
	 * @return the oldCardInfoId
	 */
	public Long getOldCardInfoId() {
		return oldCardInfoId;
	}

	/**
	 * @param oldCardInfoId the oldCardInfoId to set
	 */
	public void setOldCardInfoId(Long oldCardInfoId) {
		this.oldCardInfoId = oldCardInfoId;
	}

	/**
	 * @return the newCardInfoId
	 */
	public Long getNewCardInfoId() {
		return newCardInfoId;
	}

	/**
	 * @param newCardInfoId the newCardInfoId to set
	 */
	public void setNewCardInfoId(Long newCardInfoId) {
		this.newCardInfoId = newCardInfoId;
	}

	/**
	 * @return the oldCardUserId
	 */
	public Long getOldCardUserId() {
		return oldCardUserId;
	}

	/**
	 * @param oldCardUserId the oldCardUserId to set
	 */
	public void setOldCardUserId(Long oldCardUserId) {
		this.oldCardUserId = oldCardUserId;
	}

	/**
	 * @return the newCardUserId
	 */
	public Long getNewCardUserId() {
		return newCardUserId;
	}

	/**
	 * @param newCardUserId the newCardUserId to set
	 */
	public void setNewCardUserId(Long newCardUserId) {
		this.newCardUserId = newCardUserId;
	}

}
