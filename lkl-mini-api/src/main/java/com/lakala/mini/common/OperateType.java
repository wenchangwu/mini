package com.lakala.mini.common;
/**
 * @author 刘文成 
 * @version 创建时间：2010-12-2 下午04:10:57
 * 类说明，	卡用户表操作码（TAB_CARD_USER .OPERAT_TYPE）
 */

public class OperateType {
	
	public static final String IN_STORAGE = "M00000";// 入库
	public static final String INITIALIZATION = "M00001";//初始化
	public static final String AUTO_OPEN = "M00002";//自助开通
	public static final String AUTO_MOVING = "M00003";//自助移机
	public static final String HAND_OPEN = "M00004";//人工开通
	public static final String HAND_MOVING = "M00005";//人工移机
	public static final String CHANGE_PSAM = "M00006";//换PSAM卡
	public static final String CHANGE_USER_CARD = "M00007";//换用户卡
	public static final String HAND_CLOSE = "M00008";//人工关闭
	public static final String AUTO_CLOSE = "M00009";//自动关闭
	public static final String BACK_MINI = "M00010";//退机
	public static final String RESET_CARD_PASSWD = "M00011";// 重置用户卡密码
	public static final String CHANGE_CARD_PASSWD = "M00012";//修改用户卡密码
	public static final String DROP_CARD = "M00013";//挂失
	public static final String RESTART_CARD = "M00014";//重新启用
	public static final String DELIVER_CONFIRM = "M00015";//分发（发货）确认
	public static final String RECYCLE_PSAM = "M00016";//PSAM卡回库
	public static final String REVOKE_DELIVER_CONFIRM = "M00017";//撤销分发（发货）确认
	public static final String REVOKE_INITIALIZATION = "M00018";//撤销初始化
}
