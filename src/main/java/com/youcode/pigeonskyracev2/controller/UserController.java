package com.youcode.pigeonskyracev2.controller;

import com.youcode.pigeonskyracev2.dto.User.request.RoleUpdateRequest;
import com.youcode.pigeonskyracev2.dto.User.request.UserRegisterRequest;
import com.youcode.pigeonskyracev2.dto.User.response.LoginResponse;
import com.youcode.pigeonskyracev2.dto.User.response.UserResponse;
import com.youcode.pigeonskyracev2.entity.Role;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.mapper.UserMapper;
import com.youcode.pigeonskyracev2.service.RoleService;
import com.youcode.pigeonskyracev2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public UserController(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRegisterRequest userRequest) {

        User user = userService.createUser(userRequest);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser() {
        return new ResponseEntity<>(new LoginResponse("Login successful"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}/role")
    public ResponseEntity<String> updateUserRole(
            @PathVariable Long userId,
            @RequestBody @Valid RoleUpdateRequest roleUpdateRequest) {

        String updateMessage = roleService.updateUserRole(userId, roleUpdateRequest.newRole());
        return new ResponseEntity<>(updateMessage, HttpStatus.OK);
    }
}