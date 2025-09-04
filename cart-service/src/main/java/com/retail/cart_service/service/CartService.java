package com.retail.cart_service.service;

import com.retail.cart_service.dto.request.CartItemRequest;
import com.retail.cart_service.dto.response.CartResponse;
import com.retail.cart_service.entity.Cart;
import com.retail.cart_service.entity.CartItem;
import com.retail.cart_service.mapper.CartMapper;
import com.retail.cart_service.repository.CartRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  private final CartRepository cartRepository;
  private final CartMapper mapper;

  public CartService(CartRepository cartRepository, CartMapper mapper) {
    this.cartRepository = cartRepository;
    this.mapper = mapper;
  }

  public CartResponse getByUserId(UUID id) {
    return cartRepository.findByUserId(id).map(mapper::map).orElseThrow();
  }

  public CartResponse updateCartProduct(UUID userId, CartItemRequest cartItemRequest) {
    var userCart =
        cartRepository
            .findByUserId(userId)
            .orElseGet(() -> createNewUserCart(userId, cartItemRequest));

    userCart.getItems().stream()
        .filter(item -> item.getProductId().equals(cartItemRequest.getProductId()))
        .findAny()
        .ifPresentOrElse(
            item -> updateItem(item, userCart, cartItemRequest),
            () -> addItem(userCart, cartItemRequest));

    return mapper.map(cartRepository.save(userCart));
  }

  // managed in backend itself, if quantity <= 0 remove that item.
  private void updateItem(CartItem item, Cart userCart, CartItemRequest cartItemRequest) {
    if (cartItemRequest.getQuantity() > 0) {
      item.setQuantity(cartItemRequest.getQuantity());
    } else {
      userCart.getItems().remove(item);
    }
  }

  private Cart createNewUserCart(UUID userId, CartItemRequest cartItemRequest) {
    return cartRepository.save(mapper.map(userId, cartItemRequest));
  }

  private void addItem(Cart userCart, CartItemRequest cartItemRequest) {
    userCart.addItem(mapper.map(cartItemRequest));
  }
}
