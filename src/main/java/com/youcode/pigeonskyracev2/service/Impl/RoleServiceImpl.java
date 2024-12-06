package com.youcode.pigeonskyracev2.service.Impl;

import com.youcode.pigeonskyracev2.dto.User.response.UserResponse;
import com.youcode.pigeonskyracev2.entity.Role;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.mapper.UserMapper;
import com.youcode.pigeonskyracev2.repository.UserRepository;
import com.youcode.pigeonskyracev2.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public RoleServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public String updateUserRole(Long userId, Role newRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        user.setRole(newRole);

        return "User " + user.getUsername() + " updated to " + newRole;

    }
}