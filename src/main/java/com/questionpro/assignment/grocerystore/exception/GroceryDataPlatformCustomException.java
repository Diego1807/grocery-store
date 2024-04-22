package com.questionpro.assignment.grocerystore.exception;

import org.springframework.http.HttpStatus;

public class GroceryDataPlatformCustomException extends RuntimeException {

	private static final long serialVersionUID = -8607183964953502615L;
	private HttpStatus status;
	private String errorCode;
	private String errorMessage;
	private String errorKey;

	public GroceryDataPlatformCustomException(String message, HttpStatus status, String errorCode, String errorMessage,
			String errorKey) {
		super(message);
		this.setStatus(status);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorKey = errorKey;
	}

	public GroceryDataPlatformCustomException(String errorCode, String errorMessage, String errorKey) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorKey = errorKey;
	}

	public GroceryDataPlatformCustomException(String string, HttpStatus expectationFailed) {
		this.errorMessage = string;
		this.status = expectationFailed;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

}
