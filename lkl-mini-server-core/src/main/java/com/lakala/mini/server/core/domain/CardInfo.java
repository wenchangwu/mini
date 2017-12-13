/**
 * com.lakala.mini.domain.CardInfo.java
 */
package com.lakala.mini.server.core.domain;

import com.lakala.common.util.Tools;
import com.lakala.core.entity.IdEntity;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.exception.CardUserNotFoundException;
import com.lakala.mini.exception.PasswordNotMatchException;
import com.lakala.mini.exception.UserCardAllreadyBindException;
import com.lakala.mini.exception.UserCardBindOtherUserException;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * 当前用户卡信息表
 * 
 * @author QW 2010-12-3 下午04:35:31
 */

@Entity
@Table(name = "TAB_CARD_INFO")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_CARD_INFO", initialValue = 10000000)
public class CardInfo extends IdEntity<Long> {
	private static final long serialVersionUID = 7149378700008383250L;
	@Transient
	private CardInfoHis his;

	
	/**
	 * 用户卡号
	 */
	@Column(name = "CARD_NO", length = 20)
	private String cardNo;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "TAB_LOGIN_MINIUSER_MAPPING"
	, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }
	, joinColumns = { @JoinColumn(name = "CARD_INFO_ID", referencedColumnName = "ID") })
	private LoginMiniUser miniUser;
	/**
	 * 卡片PIN效验码
	 */
	@Column(name = "PVN", length = 4)
	private String pvn;
	/**
	 * 第二磁轨信息
	 */
	@Column(name = "track_2_dat", length = 255)
	private String track2;
	/**
	 * 使用密码
	 */
	@Column(name = "passwd", length = 16)
	private String passwd;
	/**
	 * 卡保密号CVN
	 */
	@Column(name = "CVN", length = 3)
	private String cvn;
	/**
	 * 卡商户标识
	 */
	@Column(name = "cop_flag")
	private Long copFlag;
	/**
	 * 状态
	 */
	@Column(name = "status")
	private int status;
	/**
	 * 移机规则
	 */
	@Column(name = "MOVING_RULE")
	private int movingRule;
	
	@ManyToOne
	@JoinColumn(name="MOVING_RULE",referencedColumnName="RULE_ID",updatable=false,insertable=false)
	private CardOrgRule rule;
	/**
	 * 使用手机号做终端变更时的随机验证码
	 */
	@Column(name = "identifying_code")
	private String identifyingCode;
	/**
	 * 使用手机号做终端变更时的随机验证码的有效时间
	 */
	@Column(name = "identifying_code_valid_time")
	private Timestamp identifyingCodeValidTime;
	/**
	 * 类型，0为用户卡用户，1为使用手机号（PSAM卡号）用户，2为U盾用户
	 */
	@Column(name = "type")
	private int type;

	@OneToOne(mappedBy = "cardInfo")
	private CardUser cardUser;
	
	@ManyToOne
	@JoinTable(
			name="TAB_TD_USER_CINFO_MAPPING"
			,joinColumns=@JoinColumn( name="CARD_INFO_ID", referencedColumnName="ID" )
			,inverseJoinColumns=@JoinColumn(name="TD_USER_MAPPING_ID",referencedColumnName="ID")
	)
	private ThirdPartyUserMapping thirdPartyUserMapping;

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

	public LoginMiniUser getMiniUser() {
		return miniUser;
	}

	public void setMiniUser(LoginMiniUser miniUser) {
		this.miniUser = miniUser;
		getCardUser().setBindingDate(new Date());
	}

	public CardUser getCardUser() {
		return cardUser;
	}

	public void setCardUser(CardUser cardUser) {
		this.cardUser = cardUser;
	}

	/**
	 * 绑定用户
	 * @param user 带帮用户
	 * @param password 用户卡密码
	 * @throws PasswordNotMatchException
	 * @throws CardUserNotFoundException
	 * @throws UserCardBindOtherUserException
	 * @throws UserCardAllreadyBindException
	 */
	@Transient
	public void bind(LoginMiniUser user, String password)
			throws PasswordNotMatchException, CardUserNotFoundException,
            UserCardBindOtherUserException, UserCardAllreadyBindException {
		// 判断是否已被绑定
		if (isNotBind()) {
			CardUser cardUser = getCardUser();
			if (cardUser != null) {
				if (!getPasswd().equals(password)) {
					throw PasswordNotMatchException.ERROR;
				}
				//设置用户关联
				setMiniUser(user);
			} else {
				throw CardUserNotFoundException.ERROR;
			}
		} else {
			if (getMiniUser().getUid() != user.getUid()) {
				throw UserCardBindOtherUserException.ERROR;
			} else {
				throw UserCardAllreadyBindException.ERROR;
			}
		}

	}

	/**
	 * 判断是否未绑定用户
	 * @return true:未绑定
	 */
	@Transient
	public boolean isNotBind() {
		return getMiniUser() == null;
	}
	@Transient
	public CardInfoHis getHis(){
		return this.his;
	}


	@Override
	public String toString() {
		return "CardInfo [his=" + his + ", cardNo=" + cardNo + ", miniUser=" + miniUser + ", pvn=" + pvn + ", track2=" + track2 + ", passwd=" + passwd + ", cvn=" + cvn
				+ ", copFlag=" + copFlag + ", status=" + status + ", movingRule=" + movingRule + ", identifyingCode=" + identifyingCode + ", identifyingCodeValidTime="
				+ identifyingCodeValidTime + ", type=" + type + ", cardUser=" + cardUser + "]";
	}

	@PostLoad
	private void copyToOld(){
		this.his = new CardInfoHis();
		String[] createHisCopyIgnore = { "id", "cardInfoId" };
		BeanUtils.copyProperties(this, this.his,createHisCopyIgnore);
	}
	/**
	 * @since 1.3.1
	 */
	public void initHis(){
		if(his==null){
			copyToOld();
		}
	}
	/**
	 * @return the rule
	 */
	public CardOrgRule getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(CardOrgRule rule) {
		this.rule = rule;
	}
	
	public String resetPasswd(){
		String randomNumStr = Tools.getRandomNumStr(6);
		setPasswd(randomNumStr);
		return randomNumStr;
	}
	
	public String unBindToUser(){
		int _status = getStatus();
		if (_status == CardState.UNINITIALIZATION
				|| _status == CardState.INITIALIZATION)
			return CodeDefine.USER_CARD_STATUS_ERROR;
		LoginMiniUser loginMiniUser = getMiniUser();
		if (loginMiniUser == null)
			return CodeDefine.USER_CARD_UNBIND;
		//TODO 可以改成级联操作
		Iterator<CardInfo> cardInfos = loginMiniUser.getCardInfos().iterator();
		while (cardInfos.hasNext()) {
			CardInfo oCardInfo = cardInfos.next();
			if (oCardInfo.getId().longValue() == getId().longValue()){
				cardInfos.remove();
			}
		}
		//第三方设置
		if(this.thirdPartyUserMapping!=null){
			setThirdPartyUserMapping(null);
		}
		return CodeDefine.SUCCESS;
	}
	public void initCardOrg(CardOrg cardOrg){
		if(cardOrg==null){
			throw new IllegalArgumentException(CodeDefine.NO_CARD_ORG);
		}
		if(CardState.UNINITIALIZATION==getStatus()){
			setCopFlag(cardOrg.getId());
			setMovingRule(cardOrg.getMovingRule());
			setStatus(CardState.INITIALIZATION);
			CardUser cardUser = getCardUser();
			cardUser.setOperatType(OperateType.INITIALIZATION);
			cardUser.setChangeDate(new Date(System.currentTimeMillis()));
		}else{
			throw new IllegalStateException(CodeDefine.USER_CARD_STATUS_ERROR);
		}
	}
	/**
	 * 判断该终端是否可以被转赠
	 * @return true 可以;false 不可以
	 */
	public boolean canReInitTerminal(){
		int movingRule = getMovingRule();
		return movingRule != 4&& movingRule!= CardOrgRule.RULE_ID_THRID_PARTY_APP;
	}
	
	public String getLineNo(){
		Long id = getId();
		if(this.getType()== CardType.USER_EWALLET){
			return getEWalletLineNo();
		}
		return getLineNo(id);
	}
	public String getEWalletLineNo(){
		Long id = getId();
		return getEWalletLineNo(id);
	}
	private static String getLineNo(Long cardInfoId){
		if(cardInfoId==null){
			throw new IllegalArgumentException("没有标识");
		}
		String cardInfoIdStr = String.valueOf(cardInfoId);
		if(cardInfoIdStr.length() < 8)
			cardInfoIdStr = Tools.getStringFromString(cardInfoIdStr, 8, "0", true);		
		return "4600L00"+cardInfoIdStr;
	}
	
	private static String getEWalletLineNo(Long cardInfoId){
		if(cardInfoId==null){
			throw new IllegalArgumentException("没有标识");
		}
		return "10029" +new DecimalFormat("0000000000") .format(cardInfoId);
	}

	/**
	 * @return the thirdPartyUserMapping
	 */
	public ThirdPartyUserMapping getThirdPartyUserMapping() {
		return thirdPartyUserMapping;
	}

	/**
	 * @param thirdPartyUserMapping the thirdPartyUserMapping to set
	 */
	public void setThirdPartyUserMapping(ThirdPartyUserMapping thirdPartyUserMapping) {
		this.thirdPartyUserMapping = thirdPartyUserMapping;
	}
	
	
}
