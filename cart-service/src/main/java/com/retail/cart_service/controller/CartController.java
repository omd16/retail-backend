package com.retail.cart_service.controller;

import com.retail.cart_service.dto.request.CartItemRequest;
import com.retail.cart_service.dto.response.CartResponse;
import com.retail.cart_service.service.CartService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("")
  public ResponseEntity<CartResponse> getUserKart(@RequestHeader("X-USER-ID") UUID userId) {
    return ResponseEntity.ok(cartService.getByUserId(userId));
  }

  @PostMapping("/item")
  public ResponseEntity<CartResponse> addCartItem(
      @Validated @RequestBody CartItemRequest cartItemRequest,
      @RequestHeader("X-USER-ID") UUID userId) {
    return ResponseEntity.ok(cartService.updateCartProduct(userId, cartItemRequest));
  }
}
