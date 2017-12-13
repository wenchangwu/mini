package com.lakala.mini.exception;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CodeDefine;

public class PSAMCardInfoNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 7411667617445427796L;

	private final static String CODE=CodeDefine.NO_PSAM;
	
	public static PSAMCardInfoNotFoundException ERROR=new PSAMCardInfoNotFoundException();
	
	public PSAMCardInfoNotFoundException(){
		super(CODE);
	}
}
