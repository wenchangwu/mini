package com.lakala.mini.common;

import java.io.FileNotFoundException;

import com.lakala.ErrorCode;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.BaseExceptionHandler;

/**
 * @author 刘文成
 * @version 创建时间：2010-12-21 下午01:31:42 类说明
 */

public class ExceptionHandler {

	public static String convertExceptionToCode(Exception e) {
		String code = BaseExceptionHandler.convertExceptionToCode(e);
		if (ErrorCode.ERROR_CODE_BASE.equals(code)) {
			if (e instanceof FileNotFoundException) {
				return CodeDefine.FILE_NO_EXIST;
			} else
				return CodeDefine.SYS_ERR;
		} else {
			return code;
		}

	}

	public static ApplicationException convertExceptionToAppException(
			Throwable e) {
		return new ApplicationException(e);

	}

}
