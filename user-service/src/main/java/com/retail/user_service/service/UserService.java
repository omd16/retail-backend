package com.retail.user_service.service;

import com.retail.user_service.dto.request.LoginRequest;
import com.retail.user_service.dto.request.UserRequest;
import com.retail.user_service.dto.response.LoginResponse;
import com.retail.user_service.dto.response.UserResponse;
import com.retail.user_service.exception.CredentialNotValidException;
import com.retail.user_service.exception.NotFoundException;
import com.retail.user_service.mapper.UserMapper;
import com.retail.user_service.repository.RoleRepository;
import com.retail.user_service.repository.UserRepository;
import com.retail.user_service.util.JwtUtility;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtility jwtUtility;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtility jwtUtility, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtility = jwtUtility;
        this.roleRepository = roleRepository;
    }

    public UserResponse getUserDetails(UUID userId){
        return userMapper.map(userRepository.findById(userId).orElseThrow());
    }

    public UserResponse createUser(UserRequest request){
        var user = userMapper.map(request);
        user.setPasswordHash(bCryptPasswordEncoder.encode(request.getPassword()));
        return userMapper.map(userRepository.save(user));
    }

    public UserResponse updateUser(UUID userId, UserRequest request){
        var user = userRepository.findById(userId).orElseThrow();
        userMapper.update(user, request);
        return userMapper.map(userRepository.save(user));
    }


    public LoginResponse loginAndGenerateToken(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

        if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())){
            throw new CredentialNotValidException("Invalid login credentials");
        }
        var token = jwtUtility.generateToken(user);


        return LoginResponse.builder().accessToken(token).build();

    }
}
