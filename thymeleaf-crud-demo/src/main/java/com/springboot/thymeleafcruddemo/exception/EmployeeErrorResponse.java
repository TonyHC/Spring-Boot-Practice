package com.springboot.thymeleafcruddemo.exception;

import java.sql.Timestamp;

public class EmployeeErrorResponse {
	private int status;
	private String message;
	private Timestamp timeStamp;
	
	public EmployeeErrorResponse() {
		
	}

	public EmployeeErrorResponse(int status, String message, Timestamp timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}