package com.pet.demo.dto;

public record AnimalCodeRequestDto(
    String code,
    String mainCategory,
    String subCategory
) {

}