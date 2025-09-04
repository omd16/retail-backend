package com.retail.cart_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemRequest {

    @NotNull
    private UUID productId;

    @NotNull
    private Integer quantity;
}
