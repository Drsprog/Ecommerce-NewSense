package com.ecommerce.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ecommerce.backend.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

List<Product> findByNameContainingIgnoreCase(String name);
List<Product> findByStockGreaterThan(int quantity);

} 