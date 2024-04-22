package com.questionpro.assignment.grocerystore.model.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UserRequest {
	
	private String username;
	private String password;
	private String emailId;
	private List<CartItem> cartItems;

}
