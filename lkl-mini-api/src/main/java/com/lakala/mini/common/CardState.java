package com.lakala.mini.common;
/**
 * @author 刘文成 
 * @version 创建时间：2010-12-2 下午04:28:40
 * 类说明，	用户卡和PSAM卡状态编号
 */

public class CardState {

	public static final int INITIALIZATION = 0;//初始化
	public static final int BINGDING = 1;//绑定
	public static final int RELEASE = 2;//解除绑定
	public static final int BAD = 3;//废卡
	public static final int STOP = 4;//停用
	public static final int UNINITIALIZATION = 9 ; //待初始化（未初始化）
	public static final int REPLACE = 8 ; //被換卡
	public static final int DROP = 7;     //挂失
}
