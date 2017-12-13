package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午06:00:19
 * 类说明
 */

public class PsamCardNotBindException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.PSAM_NOT_BINDING;
	
	public static PsamCardNotBindException ERROR=new PsamCardNotBindException();
	
	public PsamCardNotBindException(){
		super(CODE);
	}
}
