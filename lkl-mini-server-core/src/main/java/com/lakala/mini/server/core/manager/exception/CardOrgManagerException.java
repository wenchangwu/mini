/**
 * 
 */
package com.lakala.mini.server.core.manager.exception;

import com.lakala.common.exception.ManagerException;

/**
 * @author ray
 *
 */
public class CardOrgManagerException extends ManagerException {


	private static final long serialVersionUID = 2225121796666132647L;

	public CardOrgManagerException() {
		super();
	}

	public CardOrgManagerException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public CardOrgManagerException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public CardOrgManagerException(String errorMsg) {
		super(errorMsg);
	}

	public CardOrgManagerException(Throwable e) {
		super(e);
	}

	
}
