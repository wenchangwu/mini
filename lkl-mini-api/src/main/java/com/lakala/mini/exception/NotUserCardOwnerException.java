package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午02:07:17
 * 类说明//用户卡不存在
 */

public class NotUserCardOwnerException extends ApplicationException {
	
	private static final long serialVersionUID = -1996729638065552743L;
	final public static NotUserCardOwnerException ERROR = new NotUserCardOwnerException();
	private static final String CODE = CodeDefine.USER_CARD_NOTOWNER;
	private static final String ERROR_MSG_CODE = "error.mini.usercard.notowner";
	public NotUserCardOwnerException(){
		super(CODE,ERROR_MSG_CODE);
	}
}
