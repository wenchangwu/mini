package com.lakala.mini.server.core.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;


@StaticMetamodel( CardUserHis.class )
public class CardUserHis_  {

	public static volatile SingularAttribute<CardUserHis, Long> id;
	/**
	 * 卡用户关系表主键
	 * private long cardUserId;
	 */	
	public static volatile SingularAttribute<CardUserHis, Long> cardUserId;
	
	/**
	 * PSAM卡号
	 * private String psamNo;
	 */
	public static volatile SingularAttribute<CardUserHis, String> psamNo;
	
		
	/**
	 * 用户服务卡唯一标识ID
	 * private long cardInfoId;
	 */
	public static volatile SingularAttribute<CardUserHis, Long> cardInfoId;
	
	/**
	 * 移机时间
	 * private Timestamp telModifyDate;
	 */
	public static volatile SingularAttribute<CardUserHis, Timestamp> telModifyDate;
	
	/**
	 * 首次开通时间
	 * private Timestamp selfOpenDate;
	 */
	public static volatile SingularAttribute<CardUserHis, Timestamp> selfOpenDate;
	
	/**
	 * 核审日(需在核审日前提交核审资料)
	 * private Timestamp auditingDate;
	 */
	public static volatile SingularAttribute<CardUserHis, Timestamp> auditingDate;
	
	/**
	 * 终端号码(用户开通时绑定的线路号码)
	 * private String telNo;
	 */
	public static volatile SingularAttribute<CardUserHis, String> telNo;
	
	/**
	 * 终端所属区号
	 * private String telAreaNo;
	 */
	public static volatile SingularAttribute<CardUserHis, String> telAreaNo;
	
	
	/**
	 * 审核状态
	 * private int auditingState;
	 */
	public static volatile SingularAttribute<CardUserHis, Integer> auditingState;
	
	/**
	 * 移机次数
	 * private int telMovingCount;
	 */
	public static volatile SingularAttribute<CardUserHis, Integer> telMovingCount;
	
	
	/**
	 * 操作类型
	 * private String operatType;
	 */
	public static volatile SingularAttribute<CardUserHis, String> operatType;
	
	
	/**
	 * 用户姓名
	 * private String userName;
	 */
	public static volatile SingularAttribute<CardUserHis, String> userName;
	
	
	/**
	 * 用户身份证图片
	 * private String identityCardPic;
	 */
	public static volatile SingularAttribute<CardUserHis, String> identityCardPic;
	
	
	/**
	 * 用户上传图片地址
	 * private String userPic;
	 */
	public static volatile SingularAttribute<CardUserHis, String> userPic;
	
	
	/**
	 * 证件号
	 * private String identityCard;
	 */
	public static volatile SingularAttribute<CardUserHis, String> identityCard;
	
	
	/**
	 * 手机号
	 * private String userMobile;
	 */
	public static volatile SingularAttribute<CardUserHis, String> userMobile;
	
    /**
     * private String email;
     */
	public static volatile SingularAttribute<CardUserHis, String> email;
	
	/**
	 * 注册绑定电话号码
	 * private String bindingTelNo;
	 */
	public static volatile SingularAttribute<CardUserHis, String> bindingTelNo;
	
	
	/**
	 * 省
	 * private String provoice;
	 */
	public static volatile SingularAttribute<CardUserHis, String> provoice;
	
	
	/**
	 * 市
	 * private String city;
	 */
	public static volatile SingularAttribute<CardUserHis, String> city;
	
	/**
	 * 区
	 * private String area;
	 */
	public static volatile SingularAttribute<CardUserHis, String> area;
	
	/**
	 * 装机地址
	 * private String address;
	 */
	public static volatile SingularAttribute<CardUserHis, String> address;
	
	/**
	 * 邮政编码
	 * private String post;
	 */
	public static volatile SingularAttribute<CardUserHis, String> post;
	
	/**
	 * 变更时间
	 * private Timestamp changeDate;
	 */
	public static volatile SingularAttribute<CardUserHis, Timestamp> changeDate;

    /**
     * private Timestamp modifyDate;
     */
	public static volatile SingularAttribute<CardUserHis, Timestamp> modifyDate;
}
