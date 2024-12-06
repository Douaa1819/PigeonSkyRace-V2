package com.youcode.pigeonskyracev2.dto.User.request;

import com.youcode.pigeonskyracev2.entity.Role;
import jakarta.validation.constraints.NotNull;

public record RoleUpdateRequest (
    @NotNull(message = "New role is required")
    Role newRole){

}
