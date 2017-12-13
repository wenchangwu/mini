/**
 * 
 */
package com.lakala.mini.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author ray
 *
 */
/**
 * @author ray
 *
 */
public class UserInfoCheckRequest {
	/**
	 * PSAM卡号
	 */
	@NotNull
	@Max(16)
	private String psamNo;
	/**
	 * 用户服务卡唯一标识ID
	 */
	@NotNull
	private long cardInfoNum;

	
	/**
	 * 终端号码(用户开通时绑定的线路号码)
	 */
	@NotNull
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
	 * 提交日期
	 */
	private Date submitDate=new Date();
}
