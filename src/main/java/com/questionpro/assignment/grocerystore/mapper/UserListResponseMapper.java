package com.questionpro.assignment.grocerystore.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.model.entity.UserDetails;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;

@Component
public class UserListResponseMapper implements IMapper<List<UserDetails>, UserResponse> {

	@Override
	public UserResponse map(List<UserDetails> src, UserResponse dest) {
		if (ObjectUtils.isEmpty(dest)) {
			dest = new UserResponse();
		}
		if (!CollectionUtils.isEmpty(src)) {
			dest.setCreatedUserList(src);
		}
		return dest;
	}

}
