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

    // Endpoint combinado para búsqueda y filtros
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "asc") String sort,
            @RequestParam(required = false, defaultValue = "false") boolean inStock
    ) {
        return productService.searchProducts(category, minPrice, maxPrice, sort, inStock);
    }

}
