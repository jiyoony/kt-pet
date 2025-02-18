package com.pet.demo.dto;

import com.pet.demo.domain.User;
import lombok.Builder;

@Builder
public record UserInfoDto(
    Long id,
    String email,
    String name,
    String phone,
    String role
) {
    public static UserInfoDto from(User user) {
        // 빌더를 사용하여 객체 생성
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
