package com.lakala.mini.common;

/**
 * 用户卡的类型枚举
 * @author qw
 * @CardType.java
 * @2011-5-20 下午04:27:25
 */
public interface CardType {
	int USER_CARD = 0;      //使用磁条卡卡号的用户类型
	int UESR_MPHONE = 1;    //使用手机号的用户类型
	int USER_UD = 2;        //使用U盾的用户类型
	int UESR_SJ = 3;        //使用手机刷卡头的用户类型
	int USER_MANUAL = 4;    //人工开通的个人终端
	int USER_EWALLET = 5;    //电子钱包
	int USER_POS_PLUS = 6;    //POS+
}
