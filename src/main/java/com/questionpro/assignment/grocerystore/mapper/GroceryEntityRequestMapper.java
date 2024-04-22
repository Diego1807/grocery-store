package com.questionpro.assignment.grocerystore.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.request.GroceryItem;

@Component
public class GroceryEntityRequestMapper implements IMapper<List<GroceryItem>, List<Groceries>> {

	@Override
	public List<Groceries> map(List<GroceryItem> src, List<Groceries> dest) {
		if (CollectionUtils.isEmpty(dest)) {
			dest = new ArrayList<Groceries>();
		}
		if (!CollectionUtils.isEmpty(src)) {
			dest.addAll(src.stream().map(groceryItem -> {
				Groceries groceries = new Groceries();
				groceries.setId(0L);
				groceries.setItemName(groceryItem.getItemName());
				groceries.setItemQuantity(groceryItem.getItemQuantity());
				groceries.setPrice(groceryItem.getPrice());
				return groceries;
			}).collect(Collectors.toList()));
		}
		return dest;
	}
}
