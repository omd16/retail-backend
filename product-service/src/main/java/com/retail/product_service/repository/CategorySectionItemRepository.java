package com.retail.product_service.repository;

import com.retail.product_service.entity.CategorySectionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySectionItemRepository extends JpaRepository<CategorySectionItem, Long> {}
