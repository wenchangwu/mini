package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午05:13:19
 * 类说明
 */

public class CardResourceNotEqualsCardInfoException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.CARDRESOURCE_NOTEQUALS_CARDINFO;
	
	public static CardResourceNotEqualsCardInfoException ERROR=new CardResourceNotEqualsCardInfoException();
	
	public CardResourceNotEqualsCardInfoException(){
		super(CODE);
	}
}
