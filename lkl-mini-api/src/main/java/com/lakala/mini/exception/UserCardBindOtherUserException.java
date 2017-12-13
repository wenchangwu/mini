package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author Ray
 * @version 创建时间：2010-12-21 下午02:07:17
 * 类说明//用户卡被其他用户绑定
 */

public class UserCardBindOtherUserException extends ApplicationException {
	
	private static final long serialVersionUID = -1996729638065552743L;
	public static UserCardBindOtherUserException ERROR = new UserCardBindOtherUserException();
	private static final String CODE = CodeDefine.USER_CARD_BINDING_OTHER;
	private static final String ERROR_MSG_CODE = "error.mini.usercard.bindother";
	public UserCardBindOtherUserException(){
		super(CODE,ERROR_MSG_CODE);
	}
}
