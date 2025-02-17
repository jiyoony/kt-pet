package com.pet.demo.dto;

import com.pet.demo.domain.AnimalCode;

public record AnimalCodeResponseDto(
    Long id,
    String code,
    String mainCategory,
    String subCategory
) {
    public static AnimalCodeResponseDto from(AnimalCode animalCode) {
        return new AnimalCodeResponseDto(
            animalCode.getId(),
            animalCode.getCode(),
            animalCode.getMainCategory(),
            animalCode.getSubCategory()
        );
    }
} 