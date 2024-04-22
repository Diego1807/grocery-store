package com.questionpro.assignment.grocerystore.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CartItem {
	
	private String groceryId;
	private String itemName;
	private String quantity;
	private Double price;
	private String orderId;

}
