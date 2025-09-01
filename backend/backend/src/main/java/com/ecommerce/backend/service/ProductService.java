package com.ecommerce.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Listar todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Buscar producto por ID
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Buscar productos por categoría
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Buscar productos por precio menor a X
    public List<Product> getProductsByPriceLessThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    // Crear un producto nuevo
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Fitro combinado
    public List<Product> getProductsByCategoryAndPrice(String category, double min, double max) {
        return productRepository.findByCategoryAndPriceBetween(category, min, max);
    }

    // Ordenar productos por categoría de forma ascendente
    public List<Product> getProductsByCategoryOrderByPriceAsc(String category) {
        return productRepository.findByCategoryOrderByPriceAsc(category);
    }

    // Ordenar productos por categoría de forma descendente
    public List<Product> getProductsByCategoryOrderByPriceDesc(String category) {
        return productRepository.findByCategoryOrderByPriceDesc(category);
    }

    // Actualizar un producto existente
    public Product updateProduct(String id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        return productRepository.save(product);
    }

    // Eliminar un producto
    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
