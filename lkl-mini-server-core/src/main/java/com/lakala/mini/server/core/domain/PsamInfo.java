/**
 * com.lakala.mini.domain.PsamInfo.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.common.exception.ApplicationException;
import com.lakala.core.entity.IdEntity;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * PSAM卡资源信息
 * @author QW
 * 2010-12-2 下午05:11:06
 */
@Entity
@Table(name="TAB_PSAM_INFO")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_TAB_PSAM_INFO",allocationSize=1,initialValue=10000000)
public class PsamInfo extends IdEntity<Long>{
	
	private static final long serialVersionUID = 6644322943306546058L;
	/**
	 * PSAM卡号
	 */
	@Column(name="PSAM_NO", length=16,unique=true,updatable=false)
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
	 * 转赠次数
	 */
	@Column(name="DONATION_COUNT",length=10)
	private Integer donationCount=0;
	
	@Transient
	private PsamInfoHis his;
	
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
	@Override
	public String toString() {
		return "PsamInfo [pasmNo=" + pasmNo + ", pasmState=" + pasmState
				+ ", insertDate=" + insertDate + ", bindDate=" + bindDate
				+ ", releaseDate=" + releaseDate + ", inBatch=" + inBatch
				+ ", toString()=" + super.toString() + "]";
	}
	@PostLoad
	private void copyToOld(){
		this.his = new PsamInfoHis();
		BeanUtils.copyProperties(this, this.his);
	}
	@Transient
	public PsamInfoHis getHis() {
		return his;
	}
	@Transient
	public void setHis(PsamInfoHis his) {
		this.his = his;
	}
	/**
	 * 
	 */
	public void recyclePsam() {
		uninitialization();
	}
	/**
	 * 设置psam状态未初始化
	 */
	public void uninitialization() {
		setPasmState(CardState.UNINITIALIZATION);
		//转增次数重置
		setDonationCount(0);
	}
	/**
	 * @return the donationCount
	 */
	public Integer getDonationCount() {
		return donationCount==null?0:donationCount;
	}
	/**
	 * @param donationCount the donationCount to set
	 */
	public void setDonationCount(Integer donationCount) {
		if(donationCount==null){
			this.donationCount = 0;
		}else{
			this.donationCount = donationCount;
		}
	}
	
	/**
	 * 增加转赠次数
	 * @return 
	 * @throws ApplicationException 
	 */
	public Integer increaceDonationCount(CardOrgPublicParam cardOrgPublicParam) throws ApplicationException {
		if(cardOrgPublicParam!=null&&!cardOrgPublicParam.isDonationCountDefault()&&getDonationCount()>=cardOrgPublicParam.getDonationCount()){
			//超过限制
			throw new ApplicationException(CodeDefine.REINITTERMINAL_OVER_COUNT);
		}
		setDonationCount(this.getDonationCount()+1);
		return this.getDonationCount();
	}
	
}
