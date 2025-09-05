package com.retail.product_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "category_section")
@Data
public class CategorySection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "created_on", updatable = false)
  private LocalDateTime createdOn = LocalDateTime.now();

  @Column(name = "updated_on")
  private LocalDateTime updatedOn = LocalDateTime.now();

  @Column(name = "sort_order")
  private Integer sortOrder;

  @OneToMany(mappedBy = "categorySection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CategorySectionItem> items;
}
