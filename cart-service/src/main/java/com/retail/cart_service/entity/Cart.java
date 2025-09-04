package com.retail.cart_service.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart extends Auditable<String> {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "user_id")
  private UUID userId;

  @OneToMany(
      mappedBy = "cart",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<CartItem> items = new ArrayList<>();

  public void addItem(CartItem item) {
    item.setCart(this); // Back reference
    items.add(item);
  }
}
