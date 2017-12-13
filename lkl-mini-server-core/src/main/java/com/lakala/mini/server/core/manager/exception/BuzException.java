/**
 * 
 */
package com.lakala.mini.server.core.manager.exception;

import com.lakala.mini.common.CodeDefine;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.0.0
 * @crate_date 2013-4-18
 */
public class BuzException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	/**
	 * 
	 */
	public BuzException() {
		super(CodeDefine.SYS_ERR_MSG);
		this.errorCode=CodeDefine.SYS_ERR;
	}
	
	
	/**
	 * @param errorCode
	 */
	public BuzException(String msg,String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
