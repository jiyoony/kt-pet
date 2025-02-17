package com.pet.demo.dto;

import java.time.LocalDateTime;

public record ReservationRequestDto(
    String userId,
    String petsitterId,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String status,
    Integer price,
    String location
) {} 