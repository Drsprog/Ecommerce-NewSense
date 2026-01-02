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

    // Crear un producto nuevo
    public Product createProduct(Product product) {
        return productRepository.save(product);
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

    public List<Product> searchProducts(String category, Double minPrice, Double maxPrice, String sort, boolean inStock) {
    // Obtener todos los productos
    List<Product> products = productRepository.findAll();

    // Filtrar por categoría si existe
    if (category != null && !category.isEmpty()) {
        products = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // Filtrar por precio mínimo
    if (minPrice != null) {
        products = products.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .toList();
    }

    // Filtrar por precio máximo
    if (maxPrice != null) {
        products = products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }

    // Filtrar por stock disponible
    if (inStock) {
        products = products.stream()
                .filter(p -> p.getStock() > 0)
                .toList();
    }

    // Ordenar por precio
    if ("desc".equalsIgnoreCase(sort)) {
        products = products.stream()
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .toList();
    } else { // asc por defecto
        products = products.stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .toList();
    }

    return products;
}
}
