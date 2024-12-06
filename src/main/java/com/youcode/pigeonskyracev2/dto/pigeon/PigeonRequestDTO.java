package com.youcode.pigeonskyracev2.dto.pigeon;

import com.youcode.pigeonskyracev2.entity.enums.Gender;

public record PigeonRequestDTO(
        String numberBague,
        Gender gender,
        int age,
        String color,
        Long userId,
        Long competitionId
) {
    public Long getCompetitionId() {
        return competitionId;
    }

    public String getNumberBague() {
        return numberBague;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public Long getUserId() {
        return userId;
    }
}