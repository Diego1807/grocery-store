package com.questionpro.assignment.grocerystore.handler;

import org.springframework.stereotype.Component;

@Component
public interface IRequestHandler<TRequest, TResponse> {
	
	TResponse handle(TRequest request);

}
