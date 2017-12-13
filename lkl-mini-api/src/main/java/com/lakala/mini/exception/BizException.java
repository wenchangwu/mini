/*
 * 
 */
package com.lakala.mini.exception;

import java.io.Serializable;

import javax.xml.ws.WebFault;

import com.lakala.ErrorCode;
import com.lakala.common.exception.BaseUncheckException;
import com.lakala.common.exception.ExceptionDetail;

/**
 * @author ray
 * @since 1.0.0
 */

@WebFault(faultBean = "ExceptionDetail", name = "BizException")
public class BizException extends BaseUncheckException implements Serializable {

	private static final long serialVersionUID = 8200148127008785263L;

	private static final String DEFAULT_ERROR_CODE = ErrorCode.ERROR_CODE_SERVICE;

	private static final String DEFAULT_ERROR_MSG_CODE = "error.biz";

	private static final String DEFAULT_ERROR_MSG = "Biz Error";

	private ExceptionDetail faultInfo;

	public BizException() {
		this(DEFAULT_ERROR_MSG, DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG_CODE);
	}

	public BizException(String errorCode) {
		super(errorCode);
		setFaultInfo(new ExceptionDetail(errorCode));
	}

	public BizException(String errorCode, String errorMsgCode) {
		super(errorCode, DEFAULT_ERROR_MSG);
		setFaultInfo(new ExceptionDetail(errorCode));
	}

	public BizException(String message, String errorCode, String errorMsgCode) {
		super(errorCode, message);
		setFaultInfo(new ExceptionDetail(errorCode));
	}

	public BizException(Throwable e) {
		this(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG_CODE, e);
	}

	public BizException(String errorCode, Throwable e) {
		this(errorCode, DEFAULT_ERROR_MSG_CODE, e);
	}

	public BizException(String errorCode, String errorMsgCode, Throwable e) {
		super(errorCode, e);
	}

	public BizException(ExceptionDetail faultInfo) {
		setFaultInfo(faultInfo);
	}

	public BizException(String message, ExceptionDetail faultInfo) {
		super(message);
		setFaultInfo(faultInfo);
	}

	public ExceptionDetail getFaultInfo() {
		return faultInfo;
	}

	public void setFaultInfo(ExceptionDetail faultInfo) {
		this.faultInfo = faultInfo;
	}
}
