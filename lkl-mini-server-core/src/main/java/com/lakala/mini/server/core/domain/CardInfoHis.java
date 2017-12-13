/**
 * com.lakala.mini.domain.CardInfoHis.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 用户卡信息历史流水表
 * @author QW
 * 2010-12-3 下午04:47:05
 */
@Entity
@Table(name="TAB_CARD_INFO_HISTORY")
@SequenceGenerator(name="SEQ_GEN", allocationSize=25,sequenceName="SEQ_CARD_INFO_HISTORY",initialValue=10000000)
public class CardInfoHis extends IdEntity<Long> {
	private static final long serialVersionUID = 5925741539131210983L;
	/**
	 * 用户卡唯一标识序列
	 */
	@Column(name="CARD_INFO_ID")
	private long cardInfoId;
	/**
	 * 用户卡号
	 */
	@Column(name="CARD_NO",length=20)
	private String cardNo;
	/**
	 * 卡片PIN效验码
	 */
	@Column(name="PVN",length=4)
	private String pvn;
	/**
	 * 第二磁轨信息
	 */
	@Column(name="track_2_dat",length=255)
	private String track2;
	/**
	 * 使用密码
	 */
	@Column(name="passwd",length=16)
	private String passwd;
	/**
	 * 卡保密号CVN
	 */
	@Column(name="CVN",length=3)
	private String cvn;
	/**
	 * 卡商户标识
	 */
	@Column(name="cop_flag")
	private Long copFlag;
	/**
	 * 状态
	 */
	@Column(name="status")
	private int status;
	/**
	 * 移机规则
	 */
	@Column(name="MOVING_RULE")
	private int movingRule;
	/**
	 * 更新时间
	 */
	@Column(name="MODIFY_DATE")
	private Timestamp modifyDate;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPvn() {
		return pvn;
	}
	public void setPvn(String pvn) {
		this.pvn = pvn;
	}
	public String getTrack2() {
		return track2;
	}
	public void setTrack2(String track2) {
		this.track2 = track2;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCvn() {
		return cvn;
	}
	public void setCvn(String cvn) {
		this.cvn = cvn;
	}
	public Long getCopFlag() {
		return copFlag;
	}
	public void setCopFlag(Long copFlag) {
		this.copFlag = copFlag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMovingRule() {
		return movingRule;
	}
	public void setMovingRule(int movingRule) {
		this.movingRule = movingRule;
	}
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "CardInfoHis [cardInfoId=" + cardInfoId + ", cardNo=" + cardNo + ", pvn=" + pvn + ", track2=" + track2 + ", passwd=" + passwd + ", cvn=" + cvn + ", copFlag="
				+ copFlag + ", status=" + status + ", movingRule=" + movingRule + ", modifyDate=" + modifyDate + "]";
	}

}
