/**
 * 
 */
package com.lakala.mini.dto.card;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * CardInfoDTO Web Service使用对象
 * @author QW
 * @CardInfoDTO.java
 * @2011-4-12 下午03:43:54
 */
@XmlType(name="CardInfo")
public class CardInfoDTO implements Serializable {

	private static final long serialVersionUID = -2960797397494118564L;
	private Long id;
	
	private String cardNo;
	
	private String pvn;

	private String track2;

	private String passwd;

	private String cvn;

	private Long copFlag;

	private int status;

	private int movingRule;

	private String identifyingCode;

	private Timestamp identifyingCodeValidTime;
	
	private int type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}
	@XmlJavaTypeAdapter(com.lakala.mini.common.TimestampAdapter.class)
	public Timestamp getIdentifyingCodeValidTime() {
		return identifyingCodeValidTime;
	}

	public void setIdentifyingCodeValidTime(Timestamp identifyingCodeValidTime) {
		this.identifyingCodeValidTime = identifyingCodeValidTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
