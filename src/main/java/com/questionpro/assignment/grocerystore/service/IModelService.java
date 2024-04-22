package com.questionpro.assignment.grocerystore.service;

import org.springframework.stereotype.Component;

import com.questionpro.assignment.grocerystore.util.Operation;

@Component
public interface IModelService<TRequest, TResponse> {
	
	TResponse process(TRequest request);
	
	IModelService<TRequest, TResponse> isApplicable(Operation request);

}
