package com.questionpro.assignment.grocerystore.validator;

import org.springframework.stereotype.Component;

@Component
public interface IValidator<TRequest> {

	void validate(TRequest request);
	
}
