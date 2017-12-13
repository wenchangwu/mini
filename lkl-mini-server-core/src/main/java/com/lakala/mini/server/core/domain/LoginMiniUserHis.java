/**
 * 
 */
package com.lakala.mini.server.core.domain;

import com.lakala.core.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ray 用户和用户卡绑定信息
 */
@Entity
@Table(name = "TAB_LOGIN_MINIUSER_HIS")
@SequenceGenerator(name = "SEQ_GEN", allocationSize = 25, sequenceName = "SEQ_TAB_LOGIN_MINIUSER_HIS", initialValue = 10000000)
public class LoginMiniUserHis extends IdEntity<Long>{

	private static final long serialVersionUID = -8270364221431090444L;


	@Column(name = "USER_ID")
	private long uid;
	@Column(name = "CARD_INFO_ID")
	private long cardInfoId;
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(long cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
}
