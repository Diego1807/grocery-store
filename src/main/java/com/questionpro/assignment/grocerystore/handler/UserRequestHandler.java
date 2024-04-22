package com.questionpro.assignment.grocerystore.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;
import com.questionpro.assignment.grocerystore.service.IModelService;
import com.questionpro.assignment.grocerystore.util.Operation;

@Component
public class UserRequestHandler implements IRequestHandler<List<UserRequest>, UserResponse> {

	private HttpServletRequest requestContext;
	private List<IModelService<List<UserRequest>, UserResponse>> services;

	public UserRequestHandler(HttpServletRequest requestContext,
			List<IModelService<List<UserRequest>, UserResponse>> services) {
		this.requestContext = requestContext;
		this.services = services;
	}

	@Override
	public UserResponse handle(List<UserRequest> request) {
		return services.stream()
				.filter(services -> services.isApplicable(Operation.valueOf(requestContext.getHeader("operation"))) != null)
				.findAny()
				.map(match -> match.process(request))
				.orElseThrow();
	}

}
