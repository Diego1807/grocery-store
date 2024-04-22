package com.questionpro.assignment.grocerystore.model.response;

import java.util.List;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.entity.UserDetails;
import com.questionpro.assignment.grocerystore.model.request.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UserResponse {
	
	private List<UserDetails> createdUserList; 
	private UserDetails userDetails;
	private List<CartItem> cartItems;
	private List<Groceries> groceryList;
	private Long totalElements;

}
