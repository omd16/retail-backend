package com.retail.product_service.repository.specs;

import com.retail.product_service.entity.Category;
import com.retail.product_service.entity.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  public static Specification<Product> category(String name) {
    return (root, query, cb) -> {
      Join<Product, Category> categoryJoin = root.join("categories");
      return cb.equal(categoryJoin.get("urlSlug"), name);
    };
  }
}
