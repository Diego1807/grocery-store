package com.questionpro.assignment.grocerystore.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assignment.grocerystore.model.request.GroceryItem;
import com.questionpro.assignment.grocerystore.service.GroceriesService;
import com.questionpro.assignment.grocerystore.util.Role;

@RestController
@RequestMapping("/admin/v1")
public class GroceriesController {

	private GroceriesService groceriesService;

	public GroceriesController(GroceriesService groceriesService) {
		this.groceriesService = groceriesService;
	}

	@GetMapping("/listAllGroceries")
	public ResponseEntity<List<GroceryItem>> listAllGroceries() {
		return new ResponseEntity<List<GroceryItem>>(groceriesService.fetchAllGroceries(), HttpStatus.OK);
	}

	@PostMapping("/addGroceryItems")
	public ResponseEntity<List<GroceryItem>> addGroceryItems(@RequestHeader(defaultValue = "ADMIN") Role role,
			@RequestBody List<GroceryItem> groceryItems) {
		return new ResponseEntity<List<GroceryItem>>(groceriesService.addAllGroceries(role, groceryItems),
				HttpStatus.OK);
	}

	@PatchMapping("/updateExistingGroceryItem")
	public ResponseEntity<List<GroceryItem>> updateGroceryItem(@RequestHeader(defaultValue = "ADMIN") Role role,
			@RequestBody List<GroceryItem> groceryItems) {
		return new ResponseEntity<List<GroceryItem>>(groceriesService.updateAllGroceries(role, groceryItems), HttpStatus.OK);
	}

	@DeleteMapping("/deleteGroceryById")
	public ResponseEntity<?> deleteById(@RequestHeader String id) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("message", groceriesService.deleteByGroceryId(id));
		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

	@GetMapping("/getGroceryDetailsById")
	public ResponseEntity<GroceryItem> getGroceryDetailsById(@RequestHeader String id) {
		return new ResponseEntity<GroceryItem>(groceriesService.fetchByGroceryId(id), HttpStatus.OK);
	}

	@GetMapping("/searchItems")
	public ResponseEntity<List<GroceryItem>> searchItems(@RequestHeader String searchString) {
		return new ResponseEntity<List<GroceryItem>>(groceriesService.search(searchString), HttpStatus.OK);
	}

}
