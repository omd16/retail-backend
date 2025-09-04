package com.retail.cart_service.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class CartItemRequest {

  @NotNull private UUID productId;

  @NotNull private Integer quantity;
}
