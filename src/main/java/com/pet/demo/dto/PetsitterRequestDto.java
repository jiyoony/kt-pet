package com.pet.demo.dto;

import java.time.LocalDateTime;

public record PetsitterRequestDto(
    Integer price,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String availablePetTypes,
    String location
) {} 