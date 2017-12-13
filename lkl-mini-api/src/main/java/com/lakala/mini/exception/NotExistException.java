package com.lakala.mini.exception;

import com.lakala.common.exception.BaseException;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午03:13:49
 * 类说明,用于命令模式中不存在的命令异常
 */

public class NotExistException extends BaseException {
	
	public NotExistException(String errorMsg){
		super(errorMsg);
	}

}
