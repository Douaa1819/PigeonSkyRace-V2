package com.youcode.pigeonskyracev2.dto.pigeon;


import com.youcode.pigeonskyracev2.entity.enums.Gender;

public record PigeonResponseDTO(
        Long id,
        String numberBague,
        Gender gender,
        int age,
        String color
) {}
