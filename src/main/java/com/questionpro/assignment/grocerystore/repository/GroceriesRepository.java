package com.questionpro.assignment.grocerystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.questionpro.assignment.grocerystore.model.entity.Groceries;

@Repository
public interface GroceriesRepository extends JpaRepository<Groceries, Long> {

	@Query("SELECT g FROM Groceries g WHERE g.itemName LIKE %?1%")
	List<Groceries> search(String searchString);

}
