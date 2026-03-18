package com.aadiandjava.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aadiandjava.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
