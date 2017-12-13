package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午05:59:55
 * 类说明
 */

public class UserCardNotBindException extends ApplicationException {
	private static final long serialVersionUID = -5533216070704534885L;

	private final static String CODE = CodeDefine.USER_CARD_BINDING;

	public static UserCardNotBindException ERROR = new UserCardNotBindException();

	public UserCardNotBindException() {
		super(CODE);
	}
}
