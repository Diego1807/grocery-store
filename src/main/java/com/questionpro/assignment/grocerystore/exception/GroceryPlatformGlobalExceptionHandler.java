package com.questionpro.assignment.grocerystore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GroceryPlatformGlobalExceptionHandler {

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleException(HttpMessageNotReadableException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "JSON parse Error", ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation Error", null);
		apiError.addValidationErrors(ex.getBindingResult().getAllErrors());

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(value = { GroceryDataPlatformCustomException.class })
	public ResponseEntity<Object> handleApiException(GroceryDataPlatformCustomException ex) {
		ApiError apiError = new ApiError(ex.getStatus(), ex.getMessage(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
