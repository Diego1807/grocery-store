package com.questionpro.assignment.grocerystore.model.response;

import java.util.List;

import com.questionpro.assignment.grocerystore.model.entity.Orders;
import com.questionpro.assignment.grocerystore.model.entity.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UserOrderWrapper {
	
	private UserDetails userInfo;
	private List<Orders> orderList;

}
