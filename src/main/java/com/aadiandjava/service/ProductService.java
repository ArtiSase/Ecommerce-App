package com.aadiandjava.service;

import java.util.List;
import java.util.Optional;

import com.aadiandjava.entity.Product;

public interface ProductService {

	void saveProduct(Product product);

	List<Product> getAllProducts();

	Product getById(int id);

	void delete(int id);
}
