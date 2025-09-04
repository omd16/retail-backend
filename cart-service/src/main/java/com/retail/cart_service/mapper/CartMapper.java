package com.retail.cart_service.mapper;

import com.retail.cart_service.dto.request.CartItemRequest;
import com.retail.cart_service.dto.response.CartResponse;
import com.retail.cart_service.entity.Cart;
import com.retail.cart_service.entity.CartItem;
import org.aspectj.lang.annotation.After;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {JsonNullableMappingStrategy.class})
public interface CartMapper {

    CartResponse map(Cart cart);

    Cart map(UUID userId, CartItemRequest cartItemRequest);

    CartItem map(CartItemRequest request);

    @AfterMapping
    default void map(@MappingTarget Cart cart, CartItemRequest cartItemRequest){
        cart.addItem(map(cartItemRequest));
    }

}
