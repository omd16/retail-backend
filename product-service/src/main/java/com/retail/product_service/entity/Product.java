package com.retail.product_service.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "salePrice")
  private BigDecimal salePrice;

  @Column(name = "originalPrice")
  private BigDecimal originalPrice;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "url_slug")
  private String urlSlug;

  @ManyToMany
  @JoinTable(
      name = "product_categories",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories = new ArrayList<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductMedia> media = new ArrayList<>();
}
