/**
 * @author ray
 * @filename: UserCardBindException.java
 * @create date: 下午01:00:15
 */
package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

public class UserCardBindException extends ApplicationException {

	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.USER_CARD_HAS_BINDING;
	
	private static UserCardBindException ERROR=new UserCardBindException();
	
	public UserCardBindException(){
		super(CODE);
	}
}
