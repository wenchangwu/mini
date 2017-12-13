package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:50:04
 * 类说明：移机次数超限
 */

public class MovingCountLimitException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.OUT_MOVING;
	
	public static MovingCountLimitException ERROR=new MovingCountLimitException();
	
	public MovingCountLimitException(){
		super(CODE);
	}
}
