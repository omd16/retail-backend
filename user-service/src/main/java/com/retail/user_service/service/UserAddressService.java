package com.retail.user_service.service;

import com.retail.user_service.dto.common.UserAddress;
import com.retail.user_service.mapper.UserAddressMapper;
import com.retail.user_service.repository.UserAddressRepository;
import com.retail.user_service.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

  private final UserRepository userRepository;
  private final UserAddressRepository addressRepository;
  private final UserAddressMapper addressMapper;

  public UserAddressService(
      UserRepository userRepository,
      UserAddressRepository addressRepository,
      UserAddressMapper addressMapper) {
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
    this.addressMapper = addressMapper;
  }

  public List<UserAddress> getUserAddressDetails(UUID userId) {
    return Optional.ofNullable(addressRepository.findByUserId(userId)).orElseGet(List::of).stream()
        .map(addressMapper::map)
        .toList();
  }

  public UserAddress createUserAddress(UUID userId, UserAddress address) {
    var user = userRepository.findById(userId).orElseThrow();
    var addressEntity = addressMapper.map(address, user);
    return addressMapper.map(addressRepository.save(addressEntity));
  }

  public UserAddress updateUserAddress(UUID addressId, UserAddress address) {
    var addressEntity = addressRepository.findById(addressId).orElseThrow();
    addressMapper.update(addressEntity, address);
    return addressMapper.map(addressRepository.save(addressEntity));
  }
}
