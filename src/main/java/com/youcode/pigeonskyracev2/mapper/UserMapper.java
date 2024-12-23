package com.youcode.pigeonskyracev2.mapper;

import com.youcode.pigeonskyracev2.dto.User.request.UserRegisterRequest;
import com.youcode.pigeonskyracev2.dto.User.response.UserResponse;
import com.youcode.pigeonskyracev2.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegisterRequest userRequest);

    UserResponse toUserResponse(User user);
}
