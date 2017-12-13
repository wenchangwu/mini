package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:55:34
 * 类说明//用户卡已存在
 */

public class UserCardHasExistException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.USER_CARD_HAS_EXIST;
	
	public static UserCardHasExistException ERROR=new UserCardHasExistException();
	
	public UserCardHasExistException(){
		super(CODE);
	}
}
