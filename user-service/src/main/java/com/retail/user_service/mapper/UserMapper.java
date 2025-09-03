package com.retail.user_service.mapper;

import com.retail.user_service.dto.request.UserRequest;
import com.retail.user_service.dto.response.UserResponse;
import com.retail.user_service.entity.User;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {JsonNullableMappingStrategy.class})
public interface UserMapper {

  UserResponse map(User user);

  User map(UserRequest request);

  void update(@MappingTarget User user, UserRequest request);
}
