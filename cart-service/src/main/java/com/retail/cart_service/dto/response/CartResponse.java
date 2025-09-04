package com.retail.cart_service.dto.response;

import com.retail.cart_service.dto.common.Cart;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartResponse {

    private final UUID id;

    private final List<Cart> items;
}
