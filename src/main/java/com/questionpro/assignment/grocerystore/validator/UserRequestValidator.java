package com.questionpro.assignment.grocerystore.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;

@Component
public class UserRequestValidator extends BaseValidator implements IValidator<List<UserRequest>> {

	@Override
	public void validate(List<UserRequest> request) {
		if (CollectionUtils.isEmpty(request) && ObjectUtils.isEmpty(request.get(0))) {
			throw new GroceryDataPlatformCustomException("Cannot Serve Blank Request!", HttpStatus.BAD_REQUEST);
		}

		UserRequest userRequest = request.get(0);
		if (StringUtils.isBlank(userRequest.getUsername()) || StringUtils.isBlank(userRequest.getEmailId())
				|| CollectionUtils.isEmpty(userRequest.getCartItems())) {
			throw new GroceryDataPlatformCustomException("One or more required fields are blank!",
					HttpStatus.BAD_REQUEST);
		}

	}

}
