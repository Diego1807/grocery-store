package com.questionpro.assignment.grocerystore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assignment.grocerystore.handler.IRequestHandler;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;
import com.questionpro.assignment.grocerystore.util.Operation;
import com.questionpro.assignment.grocerystore.util.Role;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	IRequestHandler<List<UserRequest>, UserResponse> userRequestHandler;

	public UserController(IRequestHandler<List<UserRequest>, UserResponse> userRequestHandler) {
		this.userRequestHandler = userRequestHandler;
	}

	@PostMapping("/user")
	public ResponseEntity<UserResponse> createUser(@RequestHeader(defaultValue = "ORDER_ITEMS") Operation operation,
			@RequestBody List<UserRequest> userRequest, @RequestHeader(defaultValue = "0", required = false) String pageNo,
			@RequestHeader(defaultValue = "USER", required = false) Role role, 
			@RequestHeader(defaultValue = "10", required = false) String pageSize,
			@RequestHeader(defaultValue = "ASC", required = false) String direction,
			@RequestHeader(defaultValue = "id", required = false) String columnProperty) {
		return new ResponseEntity<UserResponse>(userRequestHandler.handle(userRequest), HttpStatus.OK);
	}

//	@PostMapping("/view")
//	public ResponseEntity<UserResponse> viewGroceryList(@RequestHeader Operation operation) {
//		return new ResponseEntity<UserResponse>(userRequestHandler.handle(userRequest), HttpStatus.OK);
//	}

//	@PostMapping("/addToCart")
//	public ResponseEntity<?> bookItems(@RequestHeader Operation operation, @RequestBody UserRequest userRequest) {
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
