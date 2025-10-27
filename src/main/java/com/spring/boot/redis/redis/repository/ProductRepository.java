package com.spring.boot.redis.redis.repository;

import com.spring.boot.redis.redis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
