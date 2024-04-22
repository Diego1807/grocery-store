package com.questionpro.assignment.grocerystore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.model.entity.Orders;
import com.questionpro.assignment.grocerystore.model.request.CartItem;
import com.questionpro.assignment.grocerystore.model.response.UserOrderWrapper;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;

@Component
public class UserOrderResponseMapper implements IMapper<UserOrderWrapper, UserResponse> {

	@Override
	public UserResponse map(UserOrderWrapper src, UserResponse dest) {
		if (ObjectUtils.isEmpty(dest)) {
			dest = new UserResponse();
		}
		if (!ObjectUtils.isEmpty(src)) {
			dest.setUserDetails(src.getUserInfo());
			List<CartItem> cartItems = new ArrayList<>();
			for(Orders order : src.getOrderList()) {
				CartItem cartItem = new CartItem();
				cartItem.setItemName(order.getItemName());
				cartItem.setQuantity(order.getQuantity());
				cartItem.setOrderId(order.getOrderId());
				cartItem.setGroceryId(order.getItemId().toString());
				cartItem.setPrice(Double.valueOf(order.getPrice()));
				cartItems.add(cartItem);
			}
			dest.setCartItems(cartItems);
		}
		return dest;
	}

}
