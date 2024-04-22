package com.questionpro.assignment.grocerystore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionpro.assignment.grocerystore.exception.GroceryDataPlatformCustomException;
import com.questionpro.assignment.grocerystore.mapper.IMapper;
import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.request.GroceryItem;
import com.questionpro.assignment.grocerystore.repository.GroceriesRepository;
import com.questionpro.assignment.grocerystore.util.GroceryStoreUtil;
import com.questionpro.assignment.grocerystore.util.Role;
import com.questionpro.assignment.grocerystore.validator.IValidator;

@Service
public class GroceriesService {

	private IMapper<List<GroceryItem>, List<Groceries>> groceryEntityRequestMapper;
	private IMapper<List<Groceries>, List<GroceryItem>> groceryResponseMapper;
	private GroceriesRepository groceriesRepository;
	private IValidator<List<GroceryItem>> groceryRequestValidator;
	private ObjectMapper objectMapper;
	private GroceryStoreUtil groceryStoreUtil;
	private IMapper<GroceryItem, Groceries> grocerySingleItemMapper;

	public GroceriesService(IMapper<List<GroceryItem>, List<Groceries>> groceryEntityRequestMapper,
			IMapper<List<Groceries>, List<GroceryItem>> groceryResponseMapper, GroceriesRepository groceriesRepository,
			IValidator<List<GroceryItem>> groceryRequestValidator, ObjectMapper objectMapper,
			GroceryStoreUtil groceryStoreUtil, IMapper<GroceryItem, Groceries> grocerySingleItemMapper) {
		this.groceryEntityRequestMapper = groceryEntityRequestMapper;
		this.groceryResponseMapper = groceryResponseMapper;
		this.groceriesRepository = groceriesRepository;
		this.groceryRequestValidator = groceryRequestValidator;
		this.objectMapper = objectMapper;
		this.groceryStoreUtil = groceryStoreUtil;
		this.grocerySingleItemMapper = grocerySingleItemMapper;
	}

	public List<GroceryItem> addAllGroceries(Role role, List<GroceryItem> groceryItems) {
		try {
			if (!Role.ADMIN.equals(role)) {
				throw new GroceryDataPlatformCustomException("Operation not permitted", HttpStatus.NOT_ACCEPTABLE);
			}
			groceryRequestValidator.validate(groceryItems);
			return groceryResponseMapper
					.map(groceriesRepository.saveAll(groceryEntityRequestMapper.map(groceryItems, null)), null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	public List<GroceryItem> fetchAllGroceries() {
		try {
			return groceryResponseMapper.map(groceriesRepository.findAll(), null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error Fetching Data!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	public GroceryItem fetchByGroceryId(String id) {
		try {
			if (StringUtils.isNotBlank(id) && groceriesRepository.existsById(Long.valueOf(id))) {
				GroceryItem item = objectMapper.convertValue(groceriesRepository.findById(Long.valueOf(id)).get(),
						GroceryItem.class);
				return item;
			} else {
				throw new GroceryDataPlatformCustomException("Item not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error Fetching Data!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	public String deleteByGroceryId(String id) {
		try {
			if (StringUtils.isNotBlank(id) && groceriesRepository.existsById(Long.valueOf(id))) {
				groceriesRepository.deleteById(Long.valueOf(id));
				return "Deleted Successfully!";
			} else {
				throw new GroceryDataPlatformCustomException("Item not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error Fetching Data!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	public List<GroceryItem> search(String searchString) {
		try {
			return groceryResponseMapper.map(groceriesRepository.search(searchString), null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error Fetching Data!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@SuppressWarnings("static-access")
	public List<GroceryItem> updateAllGroceries(Role role, List<GroceryItem> groceryItems) {
		try {
			if (!Role.ADMIN.equals(role)) {
				throw new GroceryDataPlatformCustomException("Operation not permitted", HttpStatus.NOT_ACCEPTABLE);
			}
			if (groceryItems.stream().anyMatch(item -> ObjectUtils.isEmpty(item)
					|| StringUtils.isEmpty(item.getId().toString()) || !groceriesRepository.existsById(item.getId()))) {
				throw new GroceryDataPlatformCustomException("Blank/Invalid Id", HttpStatus.BAD_REQUEST);
			}
			List<Groceries> updatedGroceryItems = new ArrayList<>();
			for (GroceryItem item : groceryItems) {
				Optional<Groceries> existing = groceriesRepository.findById(item.getId());
				groceryStoreUtil.copyNonNullValues(grocerySingleItemMapper.map(item, null), existing.get());
				updatedGroceryItems.add(groceriesRepository.save(existing.get()));
			}
			return groceryResponseMapper.map(updatedGroceryItems, null);
		} catch (Exception e) {
			throw new GroceryDataPlatformCustomException("Error Occured While Updating", HttpStatus.EXPECTATION_FAILED);
		}
	}

}
