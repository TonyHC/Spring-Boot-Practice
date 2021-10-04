package com.springboot.thymeleafcruddemo.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestExceptionHandler {
	// Add an exception handler for EmployeeNotFoundException (Employee not found by its ID, EmployeeService returns null)
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleEmployeeException(EmployeeNotFoundException exception) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	 /// Add an exception handler to handle all Exceptions (primarily the NumberFormatExcpetion, when a String input fails to convert to int)
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleAllExceptions(Exception exception) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
