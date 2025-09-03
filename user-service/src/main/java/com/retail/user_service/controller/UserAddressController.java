package com.retail.user_service.controller;


import com.retail.user_service.context.UserContext;
import com.retail.user_service.dto.common.UserAddress;
import com.retail.user_service.service.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/user/address")
public class UserAddressController {

    private final UserAddressService addressService;

    public UserAddressController(UserAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<UserAddress>> getAllAddresses() {
        UUID userId = UserContext.getUserId();
        List<UserAddress> addresses = addressService.getUserAddressDetails(userId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<UserAddress> createAddress(@Valid @RequestBody UserAddress address) {
        UUID userId = UserContext.getUserId();
        UserAddress savedAddress = addressService.createUserAddress(userId, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddress> updateAddress(@PathVariable("id") UUID addressId,
                                                     @Valid @RequestBody UserAddress address) {
        UUID userId = UserContext.getUserId();
        UserAddress updatedAddress = addressService.updateUserAddress(addressId, address);
        return ResponseEntity.ok(updatedAddress);
    }
}
