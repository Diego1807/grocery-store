package com.questionpro.assignment.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.mapper.IMapper;
import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.entity.Orders;
import com.questionpro.assignment.grocerystore.model.entity.UserDetails;
import com.questionpro.assignment.grocerystore.model.request.CartItem;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.model.response.UserOrderWrapper;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;
import com.questionpro.assignment.grocerystore.repository.GroceriesRepository;
import com.questionpro.assignment.grocerystore.repository.OrdersRepository;
import com.questionpro.assignment.grocerystore.repository.UserDetailsRepository;
import com.questionpro.assignment.grocerystore.util.GroceryStoreUtil;
import com.questionpro.assignment.grocerystore.util.Operation;
import com.questionpro.assignment.grocerystore.validator.IValidator;

@Service
public class OrderGroceryService implements IModelService<List<UserRequest>, UserResponse> {

	private OrdersRepository ordersRepository;
	private UserDetailsRepository userDetailsRepository;
	private IMapper<UserOrderWrapper, UserResponse> multiGroceryResponseMapper;
	private GroceryStoreUtil groceryStoreUtil;
	private IValidator<List<UserRequest>> userRequestValidator;
	private GroceriesRepository groceryRepository;

	public OrderGroceryService(OrdersRepository ordersRepository, UserDetailsRepository userDetailsRepository,
			IMapper<UserOrderWrapper, UserResponse> multiGroceryResponseMapper, GroceryStoreUtil groceryStoreUtil,
			IValidator<List<UserRequest>> userRequestValidator, GroceriesRepository groceryRepository) {
		this.ordersRepository = ordersRepository;
		this.userDetailsRepository = userDetailsRepository;
		this.multiGroceryResponseMapper = multiGroceryResponseMapper;
		this.groceryStoreUtil = groceryStoreUtil;
		this.userRequestValidator = userRequestValidator;
		this.groceryRepository = groceryRepository;
	}

	@Override
	public UserResponse process(List<UserRequest> request) {
		try {
			userRequestValidator.validate(request);
			UserRequest userRequest = request.get(0);
			UserDetails userDetails = userDetailsRepository.findByUsername(userRequest.getUsername());
			if (ObjectUtils.isEmpty(userDetails)) {
				throw new GroceryDataPlatformCustomException("User Not Found", HttpStatus.BAD_REQUEST);
			}
			List<Orders> orderList = new ArrayList<>();
			String generatedOrderIdString = generateUniqueOrderId(userDetails.getId());
			for (CartItem cartItem : userRequest.getCartItems()) {
				Orders order = new Orders();
				order.setId(0L);
				Groceries grocery = groceryRepository.findById(Long.valueOf(cartItem.getGroceryId())).get();
				order.setItemId(grocery.getId());
				order.setItemName(grocery.getItemName());
				order.setQuantity(cartItem.getQuantity());
				order.setUserId(userDetails.getId().toString());
				order.setUsername(userDetails.getUsername());
				order.setOrderId(generatedOrderIdString);
				order.setPrice(grocery.getPrice().toString());
				orderList.add(order);
			}
			List<Orders> savedOrders = ordersRepository.saveAll(orderList);
			UserOrderWrapper responseWrapper = new UserOrderWrapper();
			responseWrapper.setUserInfo(userDetails);
			responseWrapper.setOrderList(savedOrders);
			return multiGroceryResponseMapper.map(responseWrapper, null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error while Saving order details",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public IModelService<List<UserRequest>, UserResponse> isApplicable(Operation request) {
		return (!ObjectUtils.isEmpty(request) && Operation.ORDER_ITEMS.name().equalsIgnoreCase(request.name())) ? this
				: null;
	}

	private String generateUniqueOrderId(Long userId) {
		String generatedOrderIdString;
		do {
			generatedOrderIdString = groceryStoreUtil.generateRandomOrderId(userId);
		} while (ordersRepository.existsByOrderId(generatedOrderIdString));
		return generatedOrderIdString;
	}

}
