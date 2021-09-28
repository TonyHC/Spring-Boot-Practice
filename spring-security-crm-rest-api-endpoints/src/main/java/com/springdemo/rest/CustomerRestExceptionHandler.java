package com.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	// Add an exception handler for CustomerNotFoundException (Customer not found by its ID, CustomerService returns null)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleCustomerException(CustomerNotFoundException exception) {
		CustomerErrorResponse errorResponse = new CustomerErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	 /// Add an exception handler to handle all Exceptions (primarily the NumberFormatExcpetion, when a String input fails to convert to int)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleAllExceptions(Exception exception) {
		CustomerErrorResponse errorResponse = new CustomerErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}