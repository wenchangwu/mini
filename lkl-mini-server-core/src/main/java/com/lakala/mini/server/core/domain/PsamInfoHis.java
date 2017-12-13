/**
 * com.lakala.mini.domain.PsamInfoHis.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * PSAM卡历史操作流水表
 * @author QW
 * 2010-12-2 下午09:06:45
 */
@Entity
@Table(name="TAB_PSAM_INFO_HISTORY")
@SequenceGenerator(name="SEQ_GEN", allocationSize=1,sequenceName="SEQ_PSAM_INFO_HISTORY",initialValue=10000000)
public class PsamInfoHis extends IdEntity<Long>{
	private static final long serialVersionUID = 8499525064784993915L;
	/**
	 * PSAM卡号
	 */
	@Column(name="PSAM_NO", length=16)
	private String pasmNo;
	/**
	 * PSAM卡状态
	 */
	@Column(name="PSAM_STATE")
	private int pasmState;
	/**
	 * PSAM卡入库时间
	 */
	@Column(name="PSAM_INSERT_DATE")
	private Timestamp insertDate;
	/**
	 * PSAM卡绑定时间
	 */
	@Column(name="PSAM_BINDING_DATE")
	private Timestamp bindDate;
	/**
	 * PSAM卡解绑时间
	 */
	@Column(name="PSAM_RELEASE_DATE")
	private Timestamp releaseDate;
	/**
	 * 批次号
	 */
	@Column(name="IN_BATCH",length=32)
	private String inBatch;
	/**
	 * 变更时间
	 */
	@Column(name="MODIFY_DATE")
	private Timestamp  modifyDate;
	
	/**
	 * 转赠次数
	 */
	@Column(name="DONATION_COUNT",length=10)
	private Integer donationCount=0;

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPasmNo() {
		return pasmNo;
	}

	public void setPasmNo(String pasmNo) {
		this.pasmNo = pasmNo;
	}

	public int getPasmState() {
		return pasmState;
	}

	public void setPasmState(int pasmState) {
		this.pasmState = pasmState;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public Timestamp getBindDate() {
		return bindDate;
	}

	public void setBindDate(Timestamp bindDate) {
		this.bindDate = bindDate;
	}

	public Timestamp getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getInBatch() {
		return inBatch;
	}

	public void setInBatch(String inBatch) {
		this.inBatch = inBatch;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PsamInfoHis [pasmNo=" + pasmNo + ", pasmState=" + pasmState
				+ ", insertDate=" + insertDate + ", bindDate=" + bindDate
				+ ", releaseDate=" + releaseDate + ", inBatch=" + inBatch
				+ ", modifyDate=" + modifyDate + ", donationCount="
				+ donationCount + "]";
	}

	/**
	 * @return the donationCount
	 */
	public Integer getDonationCount() {
		return donationCount;
	}

	/**
	 * @param donationCount the donationCount to set
	 */
	public void setDonationCount(Integer donationCount) {
		this.donationCount = donationCount;
	}
	
}
