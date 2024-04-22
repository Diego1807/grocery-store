package com.questionpro.assignment.grocerystore.util;

import java.beans.PropertyDescriptor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class GroceryStoreUtil {
	
	private HttpServletRequest requestContext;
	
	public GroceryStoreUtil(HttpServletRequest requestContext) {
		this.requestContext = requestContext;
	}
	
	public static void copyNonNullValues(Object src, Object dest) {
		BeanUtils.copyProperties(src, dest, getNullPropertyNames(src));
	}
	
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<String>();
		for(PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if(srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	public String generateRandomOrderId(Long userId) {
		return RandomStringUtils.random(6, true, true) + "_" + userId.toString();
	}
	
	public Map<String, String> getRequestHeaders() {
		Enumeration<String> headerNames = requestContext.getHeaderNames();
		Map<String, String> headers = new HashMap<>();
		if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.put(headerName, requestContext.getHeader(headerName));
            }
        }
		return headers;
	}

}
