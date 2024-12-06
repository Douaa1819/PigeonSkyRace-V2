package com.youcode.pigeonskyracev2.service.Impl;

import com.youcode.pigeonskyracev2.entity.Role;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.repository.UserRepository;
import com.youcode.pigeonskyracev2.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;

    public RoleServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setRole(newRole);
        return userRepository.save(user);
    }
}