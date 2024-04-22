package com.questionpro.assignment.grocerystore.validator;

import java.util.Iterator;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;

@SuppressWarnings("rawtypes")
public abstract class BaseValidator {
	
	public BaseValidator() {
		
	}
	
	protected void buildErrorMessage(List<String> errors, String errorCode, String errorKey) {
		if(!CollectionUtils.isEmpty(errors)) {
			StringBuilder sb = new StringBuilder();
			Iterator var5 = errors.iterator();
			
			while(var5.hasNext()) {
				String item = (String)var5.next();
				sb.append(item + ". ");
			}
			
			throw new GroceryDataPlatformCustomException(errorCode, sb.toString(), errorKey);
		}
	}

}
