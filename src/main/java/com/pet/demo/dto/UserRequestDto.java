package com.pet.demo.dto;

import lombok.Builder;

@Builder
public record UserRequestDto(
    String email,
    String name,
    String phone,
    String password
) {
}