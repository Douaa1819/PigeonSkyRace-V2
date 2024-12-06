package com.youcode.pigeonskyracev2.service;
import com.youcode.pigeonskyracev2.entity.Role;

public interface RoleService {

    String updateUserRole(Long userId, Role newRole);
}
