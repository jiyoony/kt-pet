package com.pet.demo.service;

import com.pet.demo.domain.Reservation;
import com.pet.demo.domain.User;
import com.pet.demo.domain.Petsitter;
import com.pet.demo.dto.ReservationRequestDto;
import com.pet.demo.dto.ReservationResponseDto;
import com.pet.demo.repository.ReservationRepository;
import com.pet.demo.repository.UserRepository;
import com.pet.demo.repository.PetsitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final PetsitterRepository petsitterRepository;

    @Autowired
    private ReservationRepository reservationRepositoryAutowired;

    public ReservationResponseDto createReservation(ReservationRequestDto requestDto) {
        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        Petsitter petsitter = petsitterRepository.findById(requestDto.petsitterId())
                .orElseThrow(() -> new IllegalArgumentException("펫시터가 존재하지 않습니다."));

        Reservation reservation = Reservation.builder()
                .user(user)
                .petsitter(petsitter)
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
                .user(userRepository.findById(requestDto.userId()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다.")))
                .petsitter(petsitterRepository.findById(requestDto.petsitterId()).orElseThrow(() -> new IllegalArgumentException("펫시터가 존재하지 않습니다.")))
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

    public List<ReservationResponseDto> getReservationsByUserEmail(String userEmail) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getUser().getEmail().equals(userEmail))
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<ReservationResponseDto> getReservationsByPetsitterId(String userEmail) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getPetsitter().getUser().getEmail().equals(userEmail))
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    public void updateReservationStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }
} 
