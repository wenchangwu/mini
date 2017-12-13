package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成
 * @version 创建时间：2010-12-21 下午01:57:30 类说明//文件数据中卡数量和制卡数不一致
 */

public class CardNumNonComplianceException extends ApplicationException {

	private static final long serialVersionUID = -5533216070704534885L;

	private final static String CODE = CodeDefine.PSAM_NOT_BINDING;

	public static CardNumNonComplianceException ERROR = new CardNumNonComplianceException();

	public CardNumNonComplianceException() {
		super(CODE);
	}

}
