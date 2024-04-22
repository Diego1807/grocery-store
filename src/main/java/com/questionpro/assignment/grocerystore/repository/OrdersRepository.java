package com.questionpro.assignment.grocerystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.questionpro.assignment.grocerystore.model.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	
	List<Orders> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	List<Orders> findByUserId(String userId);

	Boolean existsByOrderId(String orderId);

}
