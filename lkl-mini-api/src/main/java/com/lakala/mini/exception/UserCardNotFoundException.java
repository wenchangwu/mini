package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午02:07:17
 * 类说明//用户卡不存在
 */

public class UserCardNotFoundException extends ApplicationException {
	
	private static final long serialVersionUID = -1996729638065552743L;
	public static UserCardNotFoundException ERROR = new UserCardNotFoundException();
	private static final String CODE = CodeDefine.NO_USER_CARD;
	private static final String ERROR_MSG_CODE = "error.mini.usercard.notfound";
	public UserCardNotFoundException(){
		super(CODE,ERROR_MSG_CODE);
	}
}
