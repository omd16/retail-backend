package com.retail.user_service.mapper;

import com.retail.user_service.dto.request.UserRequest;
import com.retail.user_service.dto.response.UserResponse;
import com.retail.user_service.entity.Role;
import com.retail.user_service.entity.User;
import com.retail.user_service.entity.UserAddress;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {JsonNullableMappingStrategy.class})
public interface UserMapper {

    UserResponse map(User user);

    User map(UserRequest request);

    void update(@MappingTarget User user, UserRequest request);
}
