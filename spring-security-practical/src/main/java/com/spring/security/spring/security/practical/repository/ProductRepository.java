package com.spring.security.spring.security.practical.repository;

import com.spring.security.spring.security.practical.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
