/**
 * 
 */
package com.lakala.mini.server.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ray 用户和用户卡绑定信息
 */
@Entity
@Table(name = "TAB_LOGIN_MINIUSER")
public class LoginMiniUser implements Serializable{

	private static final long serialVersionUID = -8270364221431090444L;

	/**
	 * 用户
	 */
	@Column(name = "USER_ID")
	@Id
	private long uid;

	/**
	 * 关联的cardInfo
	 */
	@OneToMany
	@JoinTable(name = "TAB_LOGIN_MINIUSER_MAPPING"
		, joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }
		, inverseJoinColumns = { @JoinColumn(name = "CARD_INFO_ID", referencedColumnName = "ID") })
	private Set<CardInfo> cardInfos = new HashSet<CardInfo>();

	/**
	 * 关联的cardUser
	 */
	@OneToMany
	@JoinTable(name = "TAB_LOGIN_MINIUSER_MAPPING"
		, joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }
		, inverseJoinColumns = { @JoinColumn(name = "CARD_INFO_ID", referencedColumnName = "CARD_INFO_ID") })
	private Set<CardUser> cardUsers = new HashSet<CardUser>();
	

	public Set<CardInfo> getCardInfos() {
		return cardInfos;
	}

	public void setCardInfos(Set<CardInfo> cardInfos) {
		this.cardInfos = cardInfos;
	}

	public Set<CardUser> getCardUsers() {
		return cardUsers;
	}

	public void setCardUsers(Set<CardUser> cardUsers) {
		this.cardUsers = cardUsers;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public LoginMiniUser() {
		super();
	}

	public LoginMiniUser(long uid) {
		super();
		this.uid = uid;
	}

}
