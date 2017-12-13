package com.lakala.mini.server.core.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VIEW_PSAM_NO database table.
 * 
 */
@Entity
@Table(name="VIEW_PSAM_NO")
public class ViewPsamNo implements Serializable {
	
	private static final long serialVersionUID = -8631925190616567934L;

	private String address;

	private String area;

    @Temporal( TemporalType.DATE)
	@Column(name="AUDITING_DATE")
	private Date auditingDate;

	@Column(name="AUDITING_STATE")
	private int auditingState;

    @Temporal( TemporalType.DATE)
	@Column(name="BIND_DATE")
	private Date bindDate;

	@Column(name="BINGDING_TEL_NO")
	private String bindingTelNo;

	@Column(name="CARD_INFO_ID")
	private long cardInfoId;

	@Column(name="CARD_NO")
	private String cardNo;

	@Column(name="CARD_USER_ID")
	private long cardUserId;

    @Temporal( TemporalType.DATE)
	@Column(name="CHANGE_DATE")
	private Date changeDate;

	private String city;

	@Column(name="COP_FLAG")
	private Long copFlag;

	private String cvn;

	@Column(name="IDENTIFYING_CODE")
	private String identifyingCode;

    @Temporal( TemporalType.DATE)
	@Column(name="IDENTIFYING_CODE_VALID_TIME")
	private Date identifyingCodeValidTime;

	@Column(name="IDENTITY_CARD")
	private String identityCard;

	@Column(name="IDENTITY_CARD_PIC")
	private String identityCardPic;

	@Column(name="IN_BATCH")
	private String inBatch;

	@Column(name="MOVING_RULE")
	private int movingRule;

	@Column(name="NODE_NO")
	private String nodeNo;

	@Column(name="OPERAT_TYPE")
	private String operatType;

	private String passwd;

	private String post;

	private String provoice;

    @Temporal( TemporalType.DATE)
	@Column(name="PSAM_BINDING_DATE")
	private Date psamBindingDate;

    @Temporal( TemporalType.DATE)
	@Column(name="PSAM_INSERT_DATE")
	private Date psamInsertDate;
    @Id
	@Column(name="PSAM_NO")
	private String psamNo;

    @Temporal( TemporalType.DATE)
	@Column(name="PSAM_RELEASE_DATE")
	private Date psamReleaseDate;

	@Column(name="PSAM_STATE")
	private int psamState;

	private String pvn;

    @Temporal( TemporalType.DATE)
	@Column(name="SELF_OPEN_DATE")
	private Date selfOpenDate;

	private int status;

	@Column(name="TEL_AREA_NO")
	private String telAreaNo;

    @Temporal( TemporalType.DATE)
	@Column(name="TEL_MODIFY_DATE")
	private Date telModifyDate;

	@Column(name="TEL_MOVING_COUNT")
	private int telMovingCount;

	@Column(name="TEL_NO")
	private String telNo;

	@Column(name="TRACK_2_DAT")
	private String track2Dat;

	private int type;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_MOBILE")
	private String userMobile;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PIC")
	private String userPic;
	
	@OneToOne
	@JoinColumn(name = "CARD_INFO_ID", referencedColumnName = "ID",insertable=false,updatable=false)
	@NotFound(action= NotFoundAction.IGNORE)
	private CardInfo cardInfo;
	
	@OneToOne
	@JoinColumn(name = "CARD_USER_ID", referencedColumnName = "CARD_USER_ID",insertable=false,updatable=false)
	private CardUserExtInfo cardUserExtInfo;

    public ViewPsamNo() {
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}

	public int getAuditingState() {
		return auditingState;
	}

	public void setAuditingState(int auditingState) {
		this.auditingState = auditingState;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public String getBindingTelNo() {
		return bindingTelNo;
	}

	public void setBindingTelNo(String bindingTelNo) {
		this.bindingTelNo = bindingTelNo;
	}

	public long getCardInfoId() {
		return cardInfoId;
	}

	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public long getCardUserId() {
		return cardUserId;
	}

	public void setCardUserId(long cardUserId) {
		this.cardUserId = cardUserId;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCopFlag() {
		return copFlag;
	}

	public void setCopFlag(Long copFlag) {
		this.copFlag = copFlag;
	}

	public String getCvn() {
		return cvn;
	}

	public void setCvn(String cvn) {
		this.cvn = cvn;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public Date getIdentifyingCodeValidTime() {
		return identifyingCodeValidTime;
	}

	public void setIdentifyingCodeValidTime(Date identifyingCodeValidTime) {
		this.identifyingCodeValidTime = identifyingCodeValidTime;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getIdentityCardPic() {
		return identityCardPic;
	}

	public void setIdentityCardPic(String identityCardPic) {
		this.identityCardPic = identityCardPic;
	}

	public String getInBatch() {
		return inBatch;
	}

	public void setInBatch(String inBatch) {
		this.inBatch = inBatch;
	}

	public int getMovingRule() {
		return movingRule;
	}

	public void setMovingRule(int movingRule) {
		this.movingRule = movingRule;
	}

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public String getOperatType() {
		return operatType;
	}

	public void setOperatType(String operatType) {
		this.operatType = operatType;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProvoice() {
		return provoice;
	}

	public void setProvoice(String provoice) {
		this.provoice = provoice;
	}

	public Date getPsamBindingDate() {
		return psamBindingDate;
	}

	public void setPsamBindingDate(Date psamBindingDate) {
		this.psamBindingDate = psamBindingDate;
	}

	public Date getPsamInsertDate() {
		return psamInsertDate;
	}

	public void setPsamInsertDate(Date psamInsertDate) {
		this.psamInsertDate = psamInsertDate;
	}

	public String getPsamNo() {
		return psamNo;
	}

	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}

	public Date getPsamReleaseDate() {
		return psamReleaseDate;
	}

	public void setPsamReleaseDate(Date psamReleaseDate) {
		this.psamReleaseDate = psamReleaseDate;
	}

	public int getPsamState() {
		return psamState;
	}

	public void setPsamState(int psamState) {
		this.psamState = psamState;
	}

	public String getPvn() {
		return pvn;
	}

	public void setPvn(String pvn) {
		this.pvn = pvn;
	}

	public Date getSelfOpenDate() {
		return selfOpenDate;
	}

	public void setSelfOpenDate(Date selfOpenDate) {
		this.selfOpenDate = selfOpenDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTelAreaNo() {
		return telAreaNo;
	}

	public void setTelAreaNo(String telAreaNo) {
		this.telAreaNo = telAreaNo;
	}

	public Date getTelModifyDate() {
		return telModifyDate;
	}

	public void setTelModifyDate(Date telModifyDate) {
		this.telModifyDate = telModifyDate;
	}

	public int getTelMovingCount() {
		return telMovingCount;
	}

	public void setTelMovingCount(int telMovingCount) {
		this.telMovingCount = telMovingCount;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTrack2Dat() {
		return track2Dat;
	}

	public void setTrack2Dat(String track2Dat) {
		this.track2Dat = track2Dat;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}


	/**
	 * @return the cardInfo
	 */
	public CardInfo getCardInfo() {
		return cardInfo;
	}

	/**
	 * @param cardInfo the cardInfo to set
	 */
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	/**
	 * @return the cardUserExtInfo
	 */
	public CardUserExtInfo getCardUserExtInfo() {
		return cardUserExtInfo;
	}

	/**
	 * @param cardUserExtInfo the cardUserExtInfo to set
	 */
	public void setCardUserExtInfo(CardUserExtInfo cardUserExtInfo) {
		this.cardUserExtInfo = cardUserExtInfo;
	}


}