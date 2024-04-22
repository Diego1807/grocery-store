package com.questionpro.assignment.grocerystore.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class GroceryItem {
	
	private Long id;
	private String itemName;
	private Double price;
	private Integer itemQuantity;

}
