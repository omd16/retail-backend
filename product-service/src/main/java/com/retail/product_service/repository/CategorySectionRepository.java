package com.retail.product_service.repository;

import com.retail.product_service.entity.CategorySection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySectionRepository extends JpaRepository<CategorySection, Long> {
}
