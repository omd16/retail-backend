package com.retail.cart_service.dto.common;

import lombok.Data;

import java.util.UUID;

@Data
public class Cart {

    private final UUID productId;

    private final Integer quantity;
}
