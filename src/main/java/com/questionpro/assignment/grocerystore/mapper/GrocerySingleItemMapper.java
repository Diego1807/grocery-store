package com.questionpro.assignment.grocerystore.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;
import com.questionpro.assignment.grocerystore.model.request.GroceryItem;

@Component
public class GrocerySingleItemMapper implements IMapper<GroceryItem, Groceries> {

	@Override
	public Groceries map(GroceryItem src, Groceries dest) {
		if (ObjectUtils.isEmpty(dest)) {
			dest = new Groceries();
		}
		if (!ObjectUtils.isEmpty(src)) {
			Groceries groceries = new Groceries();
			groceries.setId(null);
			groceries.setItemName(src.getItemName());
			groceries.setItemQuantity(src.getItemQuantity());
			groceries.setPrice(src.getPrice());
			return groceries;
		}
		return dest;
	}

}
