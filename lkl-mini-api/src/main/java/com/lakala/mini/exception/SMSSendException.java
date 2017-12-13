package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:54:25
 * 类说明//短信发送失败
 */

public class SMSSendException extends ApplicationException {

	
	private static final String ERROR_MSG_CODE = "error.sms.send";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6200043901891030381L;
	
	public SMSSendException(String code) {
		super(code, ERROR_MSG_CODE);
	}
	
}
