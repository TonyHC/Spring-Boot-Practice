package com.springboot.employeecruddemo.rest;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends NoResultException {
	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}