package com.questionpro.assignment.grocerystore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.questionpro.assignment.grocerystore.util.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name =  "user_details")
@Data
@NoArgsConstructor
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", columnDefinition = "ENUM('ADMIN','USER')")
	private Role role;
	
	@Column(name = "username", unique = true, updatable = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_id", unique = true, updatable = true)
	private String emailId;
	
}
