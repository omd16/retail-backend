package com.retail.user_service.dto.response;


import com.retail.user_service.dto.common.UserAddress;
import com.retail.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private List<UserAddress> addresses = new ArrayList<>();

    private Set<Role> roles = new HashSet<>();

}
