package com.retail.cart_service.dto.common;

import java.util.UUID;
import lombok.Data;

@Data
public class Cart {

    private final UUID productId;

    private final Integer quantity;
}
