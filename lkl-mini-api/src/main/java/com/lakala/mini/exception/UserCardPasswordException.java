package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:38:19
 * 类说明,密码错误异常
 */

public class UserCardPasswordException extends ApplicationException {
	
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.PWD_ERR;
	
	public static UserCardPasswordException ERROR=new UserCardPasswordException();
	
	public UserCardPasswordException(){
		super(CODE);
	}
}
