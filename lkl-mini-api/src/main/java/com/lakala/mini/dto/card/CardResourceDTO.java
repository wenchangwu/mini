/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



/**
 * 用户卡资源WebService参数对象类
 * @author QW
 * 2011-4-8 上午09:26:43
 */
@XmlType(name="CardResource")

public class CardResourceDTO implements Serializable {


	private static final long serialVersionUID = -7682494593417031649L;

	private String cardNo;

	private String copNo;

	private Long copFlag;

	private String cardBin;

	private int cdSpace;

	private String cdClass;

	private int cdType;

	private int sort;

	private int amtPar;

	private String icKey;

	private String pvn;

	private String track2;

	private String passwd;

	private String pinNet;

	private int active;

	private Timestamp enableDate;

	private String vailDate;

	private Timestamp reqDate;

	private String userReq;

	private int stance;

	private int mode;

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
	@XmlJavaTypeAdapter(com.lakala.mini.common.TimestampAdapter.class)
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
	@XmlJavaTypeAdapter(com.lakala.mini.common.TimestampAdapter.class)
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
	
	
}
