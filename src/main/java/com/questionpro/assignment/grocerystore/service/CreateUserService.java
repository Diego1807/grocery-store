package com.questionpro.assignment.grocerystore.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.mapper.IMapper;
import com.questionpro.assignment.grocerystore.model.entity.UserDetails;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;
import com.questionpro.assignment.grocerystore.repository.UserDetailsRepository;
import com.questionpro.assignment.grocerystore.util.Operation;

@Service
public class CreateUserService implements IModelService<List<UserRequest>, UserResponse> {

	private IMapper<List<UserRequest>, List<UserDetails>> userRequestEntityMapper;
	private UserDetailsRepository userDetailsRepository;
	private IMapper<List<UserDetails>, UserResponse> userListResponseMapper;
	private HttpServletRequest requestContext;

	public CreateUserService(IMapper<List<UserRequest>, List<UserDetails>> userRequestEntityMapper,
			UserDetailsRepository userDetailsRepository,
			IMapper<List<UserDetails>, UserResponse> userListResponseMapper, HttpServletRequest requestContext) {
		this.userRequestEntityMapper = userRequestEntityMapper;
		this.userDetailsRepository = userDetailsRepository;
		this.userListResponseMapper = userListResponseMapper;
		this.requestContext = requestContext;
	}

	@Override
	public UserResponse process(List<UserRequest> request) {
		try {
			if(StringUtils.isBlank(requestContext.getHeader("role"))) {
				throw new GroceryDataPlatformCustomException("Role is required for creating User", HttpStatus.BAD_REQUEST);
			}
			return userListResponseMapper.map(userDetailsRepository.saveAll(userRequestEntityMapper.map(request, null)),
					null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error storing data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public IModelService<List<UserRequest>, UserResponse> isApplicable(Operation request) {
		return (!ObjectUtils.isEmpty(request) && Operation.CREATE_USER.name().equalsIgnoreCase(request.name())) ? this
				: null;
	}

}
