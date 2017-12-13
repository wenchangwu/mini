package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

public class PasswordNotMatchException extends ApplicationException {
	public static PasswordNotMatchException ERROR = new PasswordNotMatchException();
	private static final String CODE = CodeDefine.USER_CARD_PASSWORD_NOT_MATCH;
	private static final String ERROR_MSG_CODE = "error.mini.usercard.passwordNotMatch";
	
	public PasswordNotMatchException() {
		super(CODE, ERROR_MSG_CODE);
	}

	private static final long serialVersionUID = -8586310064142457517L;

}
