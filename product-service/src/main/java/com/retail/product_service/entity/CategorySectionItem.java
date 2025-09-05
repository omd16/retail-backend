package com.retail.product_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "category_section_item")
@Data
public class CategorySectionItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(name = "icon_url")
  private String iconUrl;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "created_on", updatable = false)
  private LocalDateTime createdOn = LocalDateTime.now();

  @Column(name = "updated_on")
  private LocalDateTime updatedOn = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_section_id")
  private CategorySection categorySection;

  @Column(name = "sort_order")
  private Integer sortOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
}
