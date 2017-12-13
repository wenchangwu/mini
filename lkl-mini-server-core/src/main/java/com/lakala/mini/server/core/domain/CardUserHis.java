/**
 * com.lakala.mini.domain.CardUserHis.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 用户卡、PSAM等绑定关系历史流水表
 * @author QW
 * 2010-12-3 下午04:15:48
 */
@Entity
@Table(name="TAB_CARD_USER_HISTORY")
@SequenceGenerator(name="SEQ_GEN", allocationSize=1,sequenceName="SEQ_CARD_USER_HISTORY",initialValue=10000000)
public class CardUserHis extends IdEntity<Long> {
	private static final long serialVersionUID = -7018596866078666169L;
	/**
	 * 卡用户关系表主键
	 */
	@Column(name="CARD_USER_ID")
	private long cardUserId;
	/**
	 * PSAM卡号
	 */
	@Column(name="PSAM_NO",length=16)
	private String psamNo;
	/**
	 * 用户服务卡唯一标识ID
	 */
	@Column(name="CARD_INFO_ID")
	private long cardInfoId;
	/**
	 * 移机时间
	 */
	@Column(name="TEL_MODIFY_DATE")
	private Timestamp telModifyDate;
	/**
	 * 首次开通时间
	 */
	@Column(name="SELF_OPEN_DATE")
	private Timestamp selfOpenDate;
	/**
	 * 核审日(需在核审日前提交核审资料)
	 */
	@Column(name="AUDITING_DATE")
	private Timestamp auditingDate;
	/**
	 * 终端号码(用户开通时绑定的线路号码)
	 */
	@Column(name="TEL_NO",length=30)
	private String telNo;
	/**
	 * 终端所属区号
	 */
	@Column(name="TEL_AREA_NO",length=10)
	private String telAreaNo;
	/**
	 * 审核状态
	 */
	@Column(name="AUDITING_STATE",length=6)
	private int auditingState;
	/**
	 * 移机次数
	 */
	@Column(name="TEL_MOVING_COUNT",length=6)
	private int telMovingCount;
	/**
	 * 操作类型
	 */
	@Column(name="OPERAT_TYPE",length=6)
	private String operatType;
	/**
	 * 用户姓名
	 */
	@Column(name="USER_NAME",length=32)
	private String userName;
	/**
	 * 用户身份证图片
	 */
	@Column(name="IDENTITY_CARD_PIC",length=200)
	private String identityCardPic;
	/**
	 * 用户上传图片地址
	 */
	@Column(name="USER_PIC",length=200)
	private String userPic;
	/**
	 * 证件号
	 */
	@Column(name="IDENTITY_CARD",length=32)
	private String identityCard;
	/**
	 * 手机号
	 */
	@Column(name="USER_MOBILE",length=20)
	private String userMobile;
	
	@Column(name="USER_EMAIL",length=200)
	private String email;
	/**
	 * 注册绑定电话号码
	 */
	@Column(name="BINGDING_TEL_NO",length=200)
	private String bindingTelNo;
	/**
	 * 省
	 */
	@Column(name="PROVOICE",length=30)
	private String provoice;
	/**
	 * 市
	 */
	@Column(name="CITY",length=30)
	private String city;
	/**
	 * 区
	 */
	@Column(name="AREA",length=30)
	private String area;
	/**
	 * 装机地址
	 */
	@Column(name="ADDRESS",length=200)
	private String address;
	/**
	 * 邮政编码
	 */
	@Column(name="POST",length=20)
	private String post;
	/**
	 * 变更时间
	 */
	@Column(name="CHANGE_DATE")
	private Timestamp changeDate;

	@Column(name="MODIFY_DATE")
	private Timestamp modifyDate;
	/**
	 * 网点编号
	 */
	@Column(name="NODE_NO")
	private String nodeNo;
	
	public long getCardUserId() {
		return cardUserId;
	}
	public void setCardUserId(long cardUserId) {
		this.cardUserId = cardUserId;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPsamNo() {
		return psamNo;
	}
	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public Timestamp getTelModifyDate() {
		return telModifyDate;
	}
	public void setTelModifyDate(Timestamp telModifyDate) {
		this.telModifyDate = telModifyDate;
	}
	public Timestamp getSelfOpenDate() {
		return selfOpenDate;
	}
	public void setSelfOpenDate(Timestamp selfOpenDate) {
		this.selfOpenDate = selfOpenDate;
	}
	public Timestamp getAuditingDate() {
		return auditingDate;
	}
	public void setAuditingDate(Timestamp auditingDate) {
		this.auditingDate = auditingDate;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public int getAuditingState() {
		return auditingState;
	}
	public void setAuditingState(int auditingState) {
		this.auditingState = auditingState;
	}
	public int getTelMovingCount() {
		return telMovingCount;
	}
	public void setTelMovingCount(int telMovingCount) {
		this.telMovingCount = telMovingCount;
	}
	public String getOperatType() {
		return operatType;
	}
	public void setOperatType(String operatType) {
		this.operatType = operatType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdentityCardPic() {
		return identityCardPic;
	}
	public void setIdentityCardPic(String identityCardPic) {
		this.identityCardPic = identityCardPic;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBindingTelNo() {
		return bindingTelNo;
	}
	public void setBindingTelNo(String bindingTelNo) {
		this.bindingTelNo = bindingTelNo;
	}
	public String getProvoice() {
		return provoice;
	}
	public void setProvoice(String provoice) {
		this.provoice = provoice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Timestamp getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}
	public String getTelAreaNo() {
		return telAreaNo;
	}
	public void setTelAreaNo(String telAreaNo) {
		this.telAreaNo = telAreaNo;
	}
	public String getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

}
