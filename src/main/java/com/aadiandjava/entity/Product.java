package com.aadiandjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "products")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 5 , max = 10 , message = "product name should be atlest 5 char and max 10 char")
	private String name;
	
	@NotNull(message = "price is required")
	@Positive(message = "price should be postive")
	private Double price;
	
	@NotBlank(message = "category is required")
	private String category;
	
	@Positive(message = "qty should be postive")
	@NotNull(message = "Qty is required")
	private Integer qty;
	
	

	//Username 
	//password   
}
