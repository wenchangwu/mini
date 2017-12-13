package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:58:40
 * 类说明//缺少联名卡商户标识
 */

public class CopflagNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.NO_COPFLAG;
	
	public static CopflagNotFoundException ERROR=new CopflagNotFoundException();
	
	public CopflagNotFoundException(){
		super(CODE);
	}
}
