package com.kanggara.budgetin.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Service
public class ValidationService {

	private final Validator validator;

	ValidationService(Validator validator) {
		this.validator = validator;
	}

	public void validate(Object request) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

}
