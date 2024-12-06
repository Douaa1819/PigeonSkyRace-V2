package com.youcode.pigeonskyracev2.service;

import com.youcode.pigeonskyracev2.entity.Role;
import com.youcode.pigeonskyracev2.entity.User;

public interface RoleService {

    User updateUserRole(Long userId, Role newRole);
}
