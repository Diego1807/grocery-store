package com.questionpro.assignment.grocerystore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "groceries")
@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Groceries {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_name", unique = true)
	private String itemName;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "item_quantity")
	private Integer itemQuantity;

}
