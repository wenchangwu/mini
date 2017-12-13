package com.lakala.mini.common;

import com.lakala.BaseCodeDefine;

/**
 * @author 刘文成
 * @version 创建时间：2010-11-30 下午03:56:59 类说明，webservice统一返回码定义
 */

public interface CodeDefine extends BaseCodeDefine {
	/**
	 * 成功
	 */
	String SUCCESS = "00";// 成功
	
	String SUCCESS_MSG = "成功";// 成功
	/**
	 * 其他异常
	 */
	String SYS_ERR = "U1";// 系统异常
	
	String SYS_ERR_MSG = "系统异常";// 系统异常
	/**
	 * 其他异常
	 */
	String OTHER_ERR = "UU";// 其他异常
	
	String OTHER_ERR_MSG = "其他异常";
	
	/**
	 * 服务卡与密码不匹配
	 */
	String PWD_ERR = "U2";// 服务卡与密码不匹配
	
	String PWD_ERR_MSG = "服务卡与密码不匹配";
	/**
	 * 用户卡不存在
	 */
	String NO_USER_CARD = "U3";// 用户卡不存在
	
	String NO_USER_CARD_MSG="用户卡不存在";
	/**
	 * 服务卡已绑定 已经和其他终端绑定
	 */
	String USER_CARD_HAS_BINDING = "U4";// 服务卡已绑定 已经和其他终端绑定
	
	
	String USER_CARD_HAS_BINDING_MSG = "服务卡已绑定 已经和其他终端绑定";
	/**
	 * 终端psam卡不存在
	 */
	String NO_PSAM = "U5";// 终端psam卡不存在
	String NO_PSAM_MSG = "终端psam卡不存在";
	/**
	 * 终端psam卡已绑定 已经和其他服务卡绑定
	 */
	String PSAM_HAS_BINDING = "U6";// 终端psam卡已绑定 已经和其他服务卡绑定
	String PSAM_HAS_BINDING_MSG = "终端已和其他用户绑定";
	/**
	 * 移机次数超限，不允许移机
	 */
	String OUT_MOVING = "U7";// 移机次数超限，不允许移机
	String OUT_MOVING_MSG = "移机次数超限，不允许移机";
	/**
	 * 手机号非法
	 */
	String NO_MOBILE = "U8";// 手机号非法
	String NO_MOBILE_MSG = "手机号非法";
	/**
	 * 超过核审期未核审
	 */
	String OUT_AUDITING = "U9";// 超过核审期未核审
	String OUT_AUDITING_MSG = "超过核审期未核审";
	/**
	 * 短信发送失败
	 */
	String SMS_SEND_FAILT = "UA";// 短信发送失败
	String SMS_SEND_FAILT_MSG = " 短信发送失败";
	/**
	 * 用户卡已存在
	 */
	String USER_CARD_HAS_EXIST = "UB";// 用户卡已存在
	String USER_CARD_HAS_EXIST_MSG = "用户卡已存在";
	/**
	 * PSAM卡已存在
	 */
	String PSAM_HAS_EXIST = "UC";// PSAM卡已存在
	String PSAM_HAS_EXIST_MSG = "PSAM卡已存在";
	/**
	 * 文件不存在
	 */
	String FILE_NO_EXIST = "UD";// 文件不存在
	String FILE_NO_EXIST_MSG = "文件不存在";
	/**
	 * 文件数据中卡数量和制卡数不一致
	 */
	String CARDNUM_NON_COMPLIANCE = "UE";// 文件数据中卡数量和制卡数不一致
	String CARDNUM_NON_COMPLIANCE_MSG = "文件数据中卡数量和制卡数不一致";
	/**
	 * 缺少联名卡商户标识
	 */
	String NO_COPFLAG = "UF";// 缺少联名卡商户标识
	String NO_COPFLAG_MSG = "缺少联名卡商户标识";
	/**
	 * 換卡时商户标识不一致
	 */
	String COPFLAG_NO_EQUAL = "UK";
	String COPFLAG_NO_EQUAL_MSG = "換卡时商户标识不一致";
	/**
	 * 系统中保存的psam卡和用户卡的对应关系和开通时传入的用户卡号不一致
	 */
	String CARDRESOURCE_NOTEQUALS_CARDINFO = "UG";// 系统中保存的psam卡和用户卡的对应关系和开通时传入的用户卡号不一致
	String CARDRESOURCE_NOTEQUALS_CARDINFO_MSG = "系统中保存的psam卡和用户卡的对应关系和开通时传入的用户卡号不一致";
	/**
	 * 终端psam卡未绑定
	 */
	String PSAM_NOT_BINDING = "UH";// 终端psam卡未绑定
	String PSAM_NOT_BINDING_MSG = "终端psam卡未绑定";
	/**
	 * 用户卡未绑定
	 */
	String USER_CARD_BINDING = "UI";// 用户卡未绑定
	String USER_CARD_BINDING_MSG = "用户卡未绑定";
	/**
	 * 用户卡被其他用户绑定
	 */
	String USER_CARD_BINDING_OTHER = "UO";// 用户卡被其他用户绑定
	String USER_CARD_BINDING_OTHER_MSG = "用户卡被其他用户绑定";
	/**
	 * 用户卡状态错误
	 */
	String USER_CARD_STATUS_ERROR="UJ"; //用户卡状态错误
	
	String USER_CARD_STATUS_ERROR_MSG="终端状态错误";
	/**
	 * 用户卡已被绑定
	 */
	String USER_CARD_BINDING_ALLREADY = "UP";// 用户卡已被绑定
	
	String USER_CARD_BINDING_ALLREADY_MSG = "用户卡已被绑定";
	/**
	 * 卡用户不存在
	 */
	String CARD_USER_NOT_FOUND = "UQ";// 卡用户不存在
	
	String CARD_USER_NOT_FOUND_MSG = "卡用户不存在";
	/**
	 * 二磁轨信息不正确，卡非法
	 */
	String TRACK2_INVALID = "UT";// 二磁轨信息不正确，卡非法
	
	String TRACK2_INVALID_MSG = "二磁轨信息不正确，卡非法";
	
	/**
	 * 密码不正确
	 */
	String USER_CARD_PASSWORD_NOT_MATCH = "PE";//密码不正确
	
	String USER_CARD_PASSWORD_NOT_MATCH_MSG = "密码不正确";
	/**
	 * 参数错
	 */
	String PARAM_ERROR="UL"; //参数非法
	
	String PARAM_ERROR_MSG="参数非法"; //参数非法
	
	/**
	 * 用户卡未绑定
	 */
	String USER_CARD_UNBIND = "700001";
	
	String USER_CARD_UNBIND_MSG = "用户卡未绑定";
	/**
	 * 用户卡不属于该用户
	 */
	String USER_CARD_NOTOWNER = "70002";
	
	String USER_CARD_NOTOWNER_MSG = "用户卡不属于该用户";
	/**
	 * 不存在联名卡商户标识
	 */
	String NO_CARD_ORG = "70003";
	
	String NO_CARD_ORG_MSG = "不存在联名卡商户标识";
	/**
	 * 短信发送成功的应答码
	 */
	String SMS_SEND_SUCCESS = "1";
	
	String SMS_SEND_SUCCESS_MSG = "短信发送成功的应答码";
	/**
	 *  密钥强度不够
	 */
	String PWD_STRENGTH_ERR = "PS";
	
	String PWD_STRENGTH_ERR_MSG = "密钥强度不够";
	/**
	 * 卡已经分发（发货）
	 */
	String CARD_HAS_DELIVER = "UV";
	
	String CARD_HAS_DELIVER_MSG = "UV";
	/**
	 * 卡还未分发（发货）
	 */
	String CARD_HAS_NOT_DELIVER = "UM";
	
	String CARD_HAS_NOT_DELIVER_MSG = "卡还未分发（发货）";
	/**
	 * 卡类型错误
	 */
	String CARD_TYPE_ERROR = "UY";
	
	String CARD_TYPE_ERROR_MSG = "卡类型错误";
	/**
	 * 绑定信息错误
	 */
	String BINDING_NO_ERROR = "BE";
	
	String BINDING_NO_ERROR_MSG = "绑定信息错误";
	/**
	 * 绑定信息为空
	 */
	String BINDING_NO_ISNULL = "BN";
	
	String BINDING_NO_ISNULL_MSG = "绑定信息为空";
	/**
	 * 不可以转赠
	 */
	String CAN_NOT_REINITTERMINAL = "BR";
	
	String CAN_NOT_REINITTERMINAL_MSG = "不可以转赠";
	
	/**
	 * 转赠次数超过限制
	 * @since 1.4.0
	 */
	String REINITTERMINAL_OVER_COUNT = "BO";
	
	String REINITTERMINAL_OVER_COUNT_MSG = "转赠次数超过限制";
	/**
	 * 电话号码已经被绑定
	 */
	String TELNO_HAS_BINDING = "UX";
	
	String TELNO_HAS_BINDING_MSG = "电话号码已经被绑定";
	/**
	 * 运营商不匹配
	 */
	String TELECOM_OPERATORS_NOT_MATCH = "TM";
	
	String TELECOM_OPERATORS_NOT_MATCH_MSG = "运营商不匹配";
	/**
	 * 手机号的运营商不匹配
	 */
	String MOBILE_PHONE_NOT_MATCH =  "PM";
	
	String MOBILE_PHONE_NOT_MATCH_MSG =  "手机号的运营商不匹配";
	/**
	 * 手机型号不匹配
	 */
	String MOBILE_PHONE_MODEL_NOT_MATCH= "PMM";
	
	String MOBILE_PHONE_MODEL_NOT_MATCH_MSG= "手机型号不匹配";
	/**
	 * 手机厂商不匹配
	 */
	String MOBILE_PHONE_FACTORY_NOT_MATCH= "PFM";
	String MOBILE_PHONE_FACTORY_NOT_MATCH_MSG= "手机厂商不匹配";
	
	/**
	 * 用户已开通
	 */
	String USER_ALLREADY_BIND = "UAB";
	String USER_ALLREADY_BIND_MSG = "用户已开通";
	
	/**
	 * 用户不存在
	 */
	String USER_NOT_EXISTS = "UNE";
	String USER_NOT_EXISTS_MSG = "用户不存在";
}
