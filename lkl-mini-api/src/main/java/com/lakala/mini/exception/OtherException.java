package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:59:26
 * 类说明
 */

public class OtherException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.OTHER_ERR;
	
	public static OtherException ERROR=new OtherException();
	
	public OtherException(){
		super(CODE);
	}
}
