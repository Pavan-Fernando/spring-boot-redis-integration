package com.spring.boot.redis.redis.service;

import com.spring.boot.redis.redis.dto.ProductDto;
import com.spring.boot.redis.redis.entity.Product;
import com.spring.boot.redis.redis.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto) {
        var product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product savedProduct = productRepository.save(product);
        return new ProductDto(savedProduct.getId(), savedProduct.getName(),
                savedProduct.getPrice());
    }

    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + productId));
        return new ProductDto(product.getId(), product.getName(),
                product.getPrice());
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Long productId = productDto.id();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + productId));

        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product updatedProduct = productRepository.save(product);
        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getPrice());
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
