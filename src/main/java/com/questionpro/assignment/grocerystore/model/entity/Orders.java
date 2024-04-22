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

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@SuperBuilder
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_id")
	private Long itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "price")
	private String price;

}
