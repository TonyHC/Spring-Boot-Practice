package com.springboot.thymeleafcruddemo.exception;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends NoResultException {
	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}