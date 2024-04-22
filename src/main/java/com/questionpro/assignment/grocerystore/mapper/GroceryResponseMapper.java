package com.questionpro.assignment.grocerystore.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.request.GroceryItem;

@Component
public class GroceryResponseMapper implements IMapper<List<Groceries>, List<GroceryItem>> {

	@Override
	public List<GroceryItem> map(List<Groceries> src, List<GroceryItem> dest) {
		if (CollectionUtils.isEmpty(dest)) {
			dest = new ArrayList<GroceryItem>();
		}
		if (!CollectionUtils.isEmpty(src)) {
			dest.addAll(src.stream().map(grocery -> {
				GroceryItem groceryItem = new GroceryItem();
				groceryItem.setId(grocery.getId());
				groceryItem.setItemName(grocery.getItemName());
				groceryItem.setItemQuantity(grocery.getItemQuantity());
				groceryItem.setPrice(grocery.getPrice());
				return groceryItem;
			}).collect(Collectors.toList()));
		}
		return dest;
	}

}
