package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

public class CardUserNotFoundException extends ApplicationException {
	public static CardUserNotFoundException ERROR = new CardUserNotFoundException();
	private static final String CODE = CodeDefine.CARD_USER_NOT_FOUND;
	private static final String ERROR_MSG_CODE = "error.mini.carduser.notfound";
	
	public CardUserNotFoundException() {
		super(CODE, ERROR_MSG_CODE);
	}

	private static final long serialVersionUID = -8586310064142457517L;

}
