package com.youcode.pigeonskyracev2.controller;

import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RoleController {


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }

}
