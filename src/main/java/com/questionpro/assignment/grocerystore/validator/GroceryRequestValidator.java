package com.questionpro.assignment.grocerystore.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.model.request.GroceryItem;

@Component
public class GroceryRequestValidator extends BaseValidator implements IValidator<List<GroceryItem>> {

	@Override
	public void validate(List<GroceryItem> request) {
	    if (CollectionUtils.isEmpty(request)) {
	        throw new GroceryDataPlatformCustomException("Cannot Serve Blank Request!", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (request.stream().anyMatch(item ->
	            ObjectUtils.isEmpty(item) ||
	            StringUtils.isEmpty(item.getItemName()) ||
	            StringUtils.isEmpty(item.getItemQuantity().toString()) ||
	            StringUtils.isEmpty(item.getPrice().toString()))) {
	        throw new GroceryDataPlatformCustomException("One or more required fields are blank!", HttpStatus.BAD_REQUEST);
	    }
	}

	
}
