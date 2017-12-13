/**
 * @author ray
 * @filename: ConstraintsViolatedException.java
 * @create date: 下午01:42:07
 */
package com.lakala.mini.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.lakala.common.exception.BaseUncheckException;

public class ConstraintsViolatedException extends BaseUncheckException {

	public ConstraintsViolatedException(
			Set<ConstraintViolation<Object>> violations) {
		// TODO Auto-generated constructor stub
	}

}
