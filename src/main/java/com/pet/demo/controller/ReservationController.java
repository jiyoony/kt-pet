package com.pet.demo.controller;

import com.pet.demo.dto.ReservationRequestDto;
import com.pet.demo.dto.ReservationResponseDto;
import com.pet.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto requestDto) {
        ReservationResponseDto createdReservation = reservationService.createReservation(requestDto);
        return ResponseEntity.ok(createdReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDto requestDto) {
        ReservationResponseDto updatedReservation = reservationService.updateReservation(id, requestDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponseDto>> getMyReservations(HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        List<ReservationResponseDto> myReservations = reservationService.getReservationsByUserEmail(userEmail);
        return ResponseEntity.ok(myReservations);
    }

    @GetMapping("/petsitter")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByPetsitterId(HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(reservationService.getReservationsByPetsitterId(userEmail));
    }
} 