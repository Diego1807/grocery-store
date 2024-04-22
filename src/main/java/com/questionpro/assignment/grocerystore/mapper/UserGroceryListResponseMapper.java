package com.questionpro.assignment.grocerystore.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;

@Component
public class UserGroceryListResponseMapper implements IMapper<Page<Groceries>, UserResponse> {

	@Override
	public UserResponse map(Page<Groceries> src, UserResponse dest) {
		if (ObjectUtils.isEmpty(dest)) {
			dest = new UserResponse();
		}
		if (!CollectionUtils.isEmpty(src.getContent())) {
			dest.setGroceryList(src.getContent());
			dest.setTotalElements(src.getTotalElements());
		}
		return dest;
	}

}
