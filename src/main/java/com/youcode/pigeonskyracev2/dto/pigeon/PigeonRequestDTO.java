package com.youcode.pigeonskyracev2.dto.pigeon;

import com.youcode.pigeonskyracev2.entity.enums.Gender;

import jakarta.validation.constraints.*;

public record PigeonRequestDTO(
        @NotNull(message = "NumberBague cannot be null")
        @Size(min = 3, max = 20, message = "NumberBague must be between 3 and 20 characters")
        String numberBague,

        @NotNull(message = "Gender cannot be null")
        Gender gender,

        @Min(value = 0, message = "Age must be positive")
        int age,

        @NotBlank(message = "Color cannot be blank")
        String color
) {}
