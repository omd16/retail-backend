package com.retail.user_service.mapper;

import com.retail.user_service.entity.User;
import com.retail.user_service.entity.UserAddress;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {JsonNullableMappingStrategy.class})
public interface UserAddressMapper {

  com.retail.user_service.dto.common.UserAddress map(UserAddress address);

  @Mapping(target = "phoneNumber", source = "address.phoneNumber")
  @Mapping(target = "user", source = "user")
  @Mapping(target = "id", ignore = true)
  UserAddress map(com.retail.user_service.dto.common.UserAddress address, User user);

  void update(
      @MappingTarget UserAddress addressEntity,
      com.retail.user_service.dto.common.UserAddress address);
}
