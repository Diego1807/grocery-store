package com.questionpro.assignment.grocerystore.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.mapper.IMapper;
import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.request.UserRequest;
import com.questionpro.assignment.grocerystore.model.response.UserResponse;
import com.questionpro.assignment.grocerystore.repository.GroceriesRepository;
import com.questionpro.assignment.grocerystore.util.GroceryStoreUtil;
import com.questionpro.assignment.grocerystore.util.Operation;

@Service
public class ViewGroceriesService implements IModelService<List<UserRequest>, UserResponse> {

	private GroceriesRepository groceriesRepository;
	private GroceryStoreUtil groceryStoreUtil;
	private IMapper<Page<Groceries>, UserResponse> userGroceryListResponseMapper;

	public ViewGroceriesService(GroceriesRepository groceriesRepository, GroceryStoreUtil groceryStoreUtil,
			IMapper<Page<Groceries>, UserResponse> userGroceryListResponseMapper) {
		this.groceriesRepository = groceriesRepository;
		this.groceryStoreUtil = groceryStoreUtil;
		this.userGroceryListResponseMapper = userGroceryListResponseMapper;
	}

	@Override
	public UserResponse process(List<UserRequest> request) {
		try {
			Map<String, String> requestHeaders = groceryStoreUtil.getRequestHeaders();
			Pageable pageable = PageRequest.of(Integer.valueOf(requestHeaders.get("pageno")),
					Integer.valueOf(requestHeaders.get("pagesize")), Direction.valueOf(requestHeaders.get("direction")),
					requestHeaders.get("columnproperty"));
			Page<Groceries> groceryPage = groceriesRepository.findAll(pageable);
			return userGroceryListResponseMapper.map(groceryPage, null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Data fetching exception", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public IModelService<List<UserRequest>, UserResponse> isApplicable(Operation request) {
		return (!ObjectUtils.isEmpty(request) && Operation.VIEW_GROCERIES.name().equalsIgnoreCase(request.name()))
				? this
				: null;
	}

}
