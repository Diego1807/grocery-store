package com.questionpro.assignment.grocerystore.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.questionpro.assignment.grocerystore.model.entity.UserDetails;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.util.GroceryStoreUtil;
import com.questionpro.assignment.grocerystore.util.Role;

@Component
public class UserRequestEntityMapper implements IMapper<List<UserRequest>, List<UserDetails>> {
	
	private GroceryStoreUtil groceryStoreUtil;
	
	public UserRequestEntityMapper(GroceryStoreUtil groceryStoreUtil) {
		this.groceryStoreUtil = groceryStoreUtil;
	}

	@Override
	public List<UserDetails> map(List<UserRequest> src, List<UserDetails> dest) {
		if (CollectionUtils.isEmpty(dest)) {
			dest = new ArrayList<UserDetails>();
		}
		if (!CollectionUtils.isEmpty(src)) {
			dest.addAll(src.stream().map(user -> {
				UserDetails userDetails = new UserDetails();
				userDetails.setId(0L);
				userDetails.setEmailId(user.getEmailId());
				userDetails.setUsername(user.getUsername());
				userDetails.setPassword(user.getPassword());
				userDetails.setRole(Role.valueOf(groceryStoreUtil.getRequestHeaders().get("role")));
				return userDetails;
			}).collect(Collectors.toList()));
		}
		return dest;
	}
}
