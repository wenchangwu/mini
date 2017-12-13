/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.common.util.CollectionHelper;
import com.lakala.core.entity.IdEntity;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 第三方用户关系实体
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
@Entity
@Table(name="TAB_TD_USER_MAPPING")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_TAB_TD_USER_MAPPING",allocationSize=1,initialValue=10000000)
@Audited
@NamedQueries(value={
		@NamedQuery(name="findByOrgIdAndOrgUserId",query=" from ThirdPartyUserMapping m where m.orgId = :orgId and m.orgUserId = :orgUserId" )
		,@NamedQuery(name="getByOrgIdAndOrgUserIdAndPsamNo",query=" select m from ThirdPartyUserMapping m , CardInfo u where  m.userId=u.miniUser.uid and m.orgId = :orgId and m.orgUserId = :orgUserId and u.cardUser.psamNo=:psamNo")
		,@NamedQuery(name="getByOrgIdAndOrgUserIdAndUserId",query=" from ThirdPartyUserMapping m  where m.orgId = :orgId and m.orgUserId = :orgUserId and m.userId=:userId ")
		,@NamedQuery(name="getCardInfoByOrgIdAndOrgUserId",query=" select u from ThirdPartyUserMapping m , CardInfo u where  m.userId=u.miniUser.uid and m.orgId = :orgId and m.orgUserId = :orgUserId")
})
public class ThirdPartyUserMapping extends IdEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 第三方机构序号
	 */
	@Column(name="ORG_ID",length=20)
	@NotNull
	private String orgId;
	
	
	
	/**
	 * 第三方客户编号
	 */
	@Column(name="ORG_USER_ID",length=200)
	@NotNull
	@Size(min=1,max=50)
	private String orgUserId;
	
	/**
	 * 绑定时间
	 */
	@Column(name="BIND_TIME")
	@NotNull
	private Date bindTime;
	
	@Column(name="USER_ID",length=20)
	private Long userId;
	
	
	@OneToMany(mappedBy="thirdPartyUserMapping")
	@NotAudited
	private List<CardInfo> cardInfos=new ArrayList<CardInfo>();
	
	
	/**
	 * @return the cardInfos
	 */
	public List<CardInfo> getCardInfos() {
		return cardInfos;
	}



	/**
	 * @param cardInfos the cardInfos to set
	 */
	public void setCardInfos(List<CardInfo> cardInfos) {
		this.cardInfos = cardInfos;
	}



	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}



	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	/**
	 * @return the orgUserId
	 */
	public String getOrgUserId() {
		return orgUserId;
	}

	/**
	 * @param orgUserId the orgUserId to set
	 */
	public void setOrgUserId(String orgUserId) {
		this.orgUserId = orgUserId;
	}

	/**
	 * @return the bindTime
	 */
	public Date getBindTime() {
		return bindTime;
	}

	/**
	 * @param bindTime the bindTime to set
	 */
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}


	/**
	 * 
	 */
	public ThirdPartyUserMapping() {
		super();
		this.setBindTime(new Date());
	}

	
	/**
	 * @param orgId
	 * @param cardInfoId
	 * @param thridPartyUserId
	 */
	public ThirdPartyUserMapping(String orgId, CardInfo cardInfoId,
			String orgUserId) {
		this();
		this.orgId = orgId;
		this.orgUserId = orgUserId;
	}

	/**
	 * @param orgId
	 * @param cardInfoId
	 * @param thridPartyUserId
	 * @param bindTime
	 */
	public ThirdPartyUserMapping(String orgId, CardInfo cardInfoId,
			String orgUserId, Date bindTime) {
		this(orgId,cardInfoId,orgUserId);
		this.setBindTime (bindTime);
	}
	
	
	/**
	 * 判断是否同用户绑定
	 * @param userId
	 * @return
	 */
	public boolean verifyBind(Long bindId){
		if(bindId==null){
			return false;
		}
		if(getUserId()==bindId){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否同用户绑定
	 * @param userId
	 * @return
	 */
	public boolean verifyBindPsam(Long bindId){
		if(bindId==null){
			return false;
		}
		List<CardInfo> cardInfos = this.getCardInfos();
		if(CollectionHelper.isNotEmpty(cardInfos)){
			for (CardInfo cardInfo : cardInfos) {
				if(cardInfo.getId().equals(bindId)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * @param psamNo
	 * @return
	 */
	public boolean verifyLogin(String psamNo) {
		List<CardInfo> cardInfos = this.getCardInfos();
		if(CollectionHelper.isNotEmpty(cardInfos)){
			for (CardInfo cardInfo : cardInfos) {
				CardUser cardUser = cardInfo.getCardUser();
				if(cardUser!=null&&cardUser.getPsamNo().equals(psamNo)){
					return true;
				}
			}
		}
		return false;
	}


}
