package com.pet.demo.dto;

import com.pet.demo.domain.Reservation;
import java.time.LocalDateTime;

public record ReservationResponseDto(
    Long id,
    String userId,
    String petsitterId,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String status,
    Integer price,
    String location
) {
    public static ReservationResponseDto from(Reservation reservation) {
        return new ReservationResponseDto(
            reservation.getId(),
            reservation.getUser_id(),
            reservation.getPetsitter_id(),
            reservation.getStartAt(),
            reservation.getEndAt(),
            reservation.getStatus(),
            reservation.getPrice(),
            reservation.getLocation()
        );
    }
} 