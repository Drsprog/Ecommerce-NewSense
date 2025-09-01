package com.ecommerce.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ecommerce.backend.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

  List<Product> findByNameContainingIgnoreCase(String name);
  List<Product> findByCategory(String category);
  List<Product> findByPriceBetween(double minPrice, double maxPrice);
  List<Product> findByCategoryOrderByPriceAsc(String category);
List<Product> findByCategoryOrderByPriceDesc(String category);
List<Product> findByStockGreaterThan(int quantity);
List<Product> findByCategoryAndPriceBetween(String category, double min, double max);
List<Product> findByPriceLessThan(double price);

} 