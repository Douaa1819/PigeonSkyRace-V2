package com.youcode.pigeonskyracev2.service.Impl;

import com.youcode.pigeonskyracev2.dto.User.request.UserRegisterRequest;
import com.youcode.pigeonskyracev2.dto.User.response.UserResponse;
import com.youcode.pigeonskyracev2.entity.Role;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.exception.UsernameAlreadyExistsException;
import com.youcode.pigeonskyracev2.mapper.UserMapper;
import com.youcode.pigeonskyracev2.repository.UserRepository;
import com.youcode.pigeonskyracev2.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
    public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final UserMapper userMapper;

        public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.userMapper = userMapper;
        }

        private Role getDefaultRole() {
        return Role.ROLE_USER;
          }

        @Override
        public User createUser(UserRegisterRequest userRequest) {
            if (userRepository.existsByUsername(userRequest.username())) {
                throw new UsernameAlreadyExistsException("Username already exists");
            }


            User user = userMapper.toUser(userRequest);


            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(getDefaultRole());
            user.setCreatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }

        @Override
        public UserResponse getUserById(Long userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameAlreadyExistsException("User not found"));
            return userMapper.toUserResponse(user);
        }

        @Override
        public User findUserByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameAlreadyExistsException("User not found"));
        }
}
