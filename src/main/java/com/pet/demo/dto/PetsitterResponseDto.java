package com.pet.demo.dto;

import com.pet.demo.domain.Petsitter;
import java.time.LocalDateTime;

public record PetsitterResponseDto(
    Long id,
    Long userId,
    Integer price,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String availablePetTypes,
    String location
) {
    public static PetsitterResponseDto from(Petsitter petsitter) {
        return new PetsitterResponseDto(
            petsitter.getId(),
            petsitter.getUser().getId(),
            petsitter.getPrice(),
            petsitter.getStartAt(),
            petsitter.getEndAt(),
            petsitter.getAvailablePetTypes(),
            petsitter.getLocation()
        );
    }
}