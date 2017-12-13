package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:53:40
 * 类说明//超过核审期未核审
 */

public class OverdueAuditingException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.OUT_AUDITING;
	
	public static OverdueAuditingException ERROR=new OverdueAuditingException();
	
	public OverdueAuditingException(){
		super(CODE);
	}
}
