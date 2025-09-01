package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{category}/asc")
    public List<Product> getProductsByCategoryAsc(@PathVariable String category) {
        return productService.getProductsByCategoryOrderByPriceAsc(category);
    }

    @GetMapping("/category/{category}/desc")
    public List<Product> getProductsByCategoryDesc(@PathVariable String category) {
        return productService.getProductsByCategoryOrderByPriceDesc(category);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price/under/{price}")
    public List<Product> getByPriceLessThan(@PathVariable double price) {
        return productService.getProductsByPriceLessThan(price);
    }
}
