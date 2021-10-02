package com.springboot.employeecruddemo.exception;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends NoResultException {
	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}