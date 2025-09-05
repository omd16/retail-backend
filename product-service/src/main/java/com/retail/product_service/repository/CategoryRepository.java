package com.retail.product_service.repository;

import com.retail.product_service.entity.Category;
import com.retail.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
