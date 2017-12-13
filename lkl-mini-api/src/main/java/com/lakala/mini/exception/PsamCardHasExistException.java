package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:56:12
 * 类说明//PSAM卡已存在
 */

public class PsamCardHasExistException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.PSAM_HAS_EXIST;
	
	public static PsamCardHasExistException ERROR=new PsamCardHasExistException();
	
	public PsamCardHasExistException(){
		super(CODE);
	}
}
