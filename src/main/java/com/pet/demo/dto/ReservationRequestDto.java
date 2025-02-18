package com.pet.demo.dto;

import java.time.LocalDateTime;

public record ReservationRequestDto(
    Long userId,
    Long petsitterId,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String status,
    Integer price,
    String location
) {} 