package com.questionpro.assignment.grocerystore.mapper;

import org.springframework.stereotype.Component;

@Component
public interface IMapper<TSource, TDestination> {
	
	TDestination map(TSource src, TDestination dest);

}
