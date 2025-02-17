package com.pet.demo.service;

import com.pet.demo.domain.Reservation;
import com.pet.demo.dto.ReservationRequestDto;
import com.pet.demo.dto.ReservationResponseDto;
import com.pet.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationResponseDto createReservation(ReservationRequestDto requestDto) {
        Reservation reservation = Reservation.builder()
                .user_id(requestDto.userId())
                .petsitter_id(requestDto.petsitterId())
                .startAt(requestDto.startAt())
                .endAt(requestDto.endAt())
                .status(requestDto.status())
                .price(requestDto.price())
                .location(requestDto.location())
                .build();

        return ReservationResponseDto.from(reservationRepository.save(reservation));
    }

    public ReservationResponseDto getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약이 존재하지 않습니다."));
        return ReservationResponseDto.from(reservation);
    }

    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    public ReservationResponseDto updateReservation(Long id, ReservationRequestDto requestDto) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약이 존재하지 않습니다."));

        reservation = Reservation.builder()
                .id(reservation.getId())
                .user_id(requestDto.userId())
                .petsitter_id(requestDto.petsitterId())
                .startAt(requestDto.startAt())
                .endAt(requestDto.endAt())
                .status(requestDto.status())
                .price(requestDto.price())
                .location(requestDto.location())
                .build();

        return ReservationResponseDto.from(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약이 존재하지 않습니다."));
        reservationRepository.delete(reservation);
    }
} 