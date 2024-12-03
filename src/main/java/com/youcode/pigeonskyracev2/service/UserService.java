package com.youcode.pigeonskyracev2.service;

import com.youcode.pigeonskyracev2.dto.User.request.UserRegisterRequest;
import com.youcode.pigeonskyracev2.dto.User.response.UserResponse;
import com.youcode.pigeonskyracev2.entity.User;

public interface UserService{
    User createUser(UserRegisterRequest user);
    User findUserByUsername(String username);
    UserResponse getUserById(Long userId);
}
