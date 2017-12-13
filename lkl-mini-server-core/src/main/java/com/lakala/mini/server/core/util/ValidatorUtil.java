package com.lakala.mini.server.core.util;

import com.lakala.mini.exception.ConstraintsViolatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidatorUtil {
	@Autowired
	private Validator validator;
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public  void validateJPAObject(Object jpaObject) {
		Set<ConstraintViolation<Object>> violations = validator.validate(jpaObject);
		
		if( violations.size() > 0 ) {
			throw new ConstraintsViolatedException(violations);
		}
	}
	
}
