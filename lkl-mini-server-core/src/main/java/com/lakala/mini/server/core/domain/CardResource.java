/**
 * com.lakala.mini.domain.CardResource.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 用户卡资源表
 * @author QW
 * 2010-12-3 下午04:51:57
 */
@Entity
@Table(name="TAB_CARD_RES")
@SequenceGenerator(name="SEQ_GEN", allocationSize=25,sequenceName="SEQ_CARD_RES",initialValue=10000000)
public class CardResource extends IdEntity<Long>{

	private static final long serialVersionUID = -3100442546765218793L;
	@Column(name="CD_NO",length=20)
	private String cardNo;
	@Column(name="cop_no",length=6)
	private String copNo;
	@Column(name="cop_flag")
	private Long copFlag;
	@Column(name="card_bin",length=8)
	private String cardBin;
	@Column(name="cd_space",length=1)
	private int cdSpace;
	@Column(name="cd_class",length=2)
	private String cdClass;
	@Column(name="cd_type",length=1)
	private int cdType;
	@Column(name="sort",length=8)
	private int sort;
	@Column(name="amt_par",length=12)
	private int amtPar;
	@Column(name="ic_key",length=3)
	private String icKey;
	@Column(name="pvn",length=4)
	private String pvn;
	@Column(name="track_2_dat",length=255)
	private String track2;
	@Column(name="passwd",length=16)
	private String passwd;
	@Column(name="pin_net",length=16)
	private String pinNet;
	@Column(name="is_active")
	private int active;
	@Column(name="ts_enable")
	private Timestamp enableDate;
	@Column(name="ts_vail",length=8)
	private String vailDate;
	@Column(name="ts_req")
	private Timestamp reqDate;
	@Column(name="user_req",length=20)
	private String userReq;
	@Column(name="stance")
	private int stance;
	@Column(name="req_mode")
	private int mode;
	@Column(name="status")
	private int status;

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCopNo() {
		return copNo;
	}
	public void setCopNo(String copNo) {
		this.copNo = copNo;
	}
	public Long getCopFlag() {
		return copFlag;
	}
	public void setCopFlag(Long copFlag) {
		this.copFlag = copFlag;
	}
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	public int getCdSpace() {
		return cdSpace;
	}
	public void setCdSpace(int cdSpace) {
		this.cdSpace = cdSpace;
	}
	public String getCdClass() {
		return cdClass;
	}
	public void setCdClass(String cdClass) {
		this.cdClass = cdClass;
	}
	public int getCdType() {
		return cdType;
	}
	public void setCdType(int cdType) {
		this.cdType = cdType;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getAmtPar() {
		return amtPar;
	}
	public void setAmtPar(int amtPar) {
		this.amtPar = amtPar;
	}
	public String getIcKey() {
		return icKey;
	}
	public void setIcKey(String icKey) {
		this.icKey = icKey;
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
	public String getPinNet() {
		return pinNet;
	}
	public void setPinNet(String pinNet) {
		this.pinNet = pinNet;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Timestamp getEnableDate() {
		return enableDate;
	}
	public void setEnableDate(Timestamp enableDate) {
		this.enableDate = enableDate;
	}
	public String getVailDate() {
		return vailDate;
	}
	public void setVailDate(String vailDate) {
		this.vailDate = vailDate;
	}
	public Timestamp getReqDate() {
		return reqDate;
	}
	public void setReqDate(Timestamp reqDate) {
		this.reqDate = reqDate;
	}
	public String getUserReq() {
		return userReq;
	}
	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}
	public int getStance() {
		return stance;
	}
	public void setStance(int stance) {
		this.stance = stance;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CardResource [cardNo=" + cardNo + ", copNo=" + copNo + ", copFlag=" + copFlag + ", cardBin=" + cardBin + ", cdSpace=" + cdSpace + ", cdClass=" + cdClass
				+ ", cdType=" + cdType + ", sort=" + sort + ", amtPar=" + amtPar + ", icKey=" + icKey + ", pvn=" + pvn + ", track2=" + track2 + ", passwd=" + passwd + ", pinNet="
				+ pinNet + ", active=" + active + ", enableDate=" + enableDate + ", vailDate=" + vailDate + ", reqDate=" + reqDate + ", userReq=" + userReq + ", stance=" + stance
				+ ", mode=" + mode + ", status=" + status + "]";
	}
	
}
