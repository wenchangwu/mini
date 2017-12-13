package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午01:48:02
 * 类说明：终端psam卡已绑定	已经和其他服务卡绑定
 */

public class PsamCardBindException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.PSAM_HAS_BINDING;
	
	public static PsamCardBindException ERROR=new PsamCardBindException();
	
	public PsamCardBindException(){
		super(CODE);
	}
}
