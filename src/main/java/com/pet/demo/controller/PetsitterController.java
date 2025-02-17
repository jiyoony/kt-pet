package com.pet.demo.controller;

import com.pet.demo.dto.PetsitterRequestDto;
import com.pet.demo.dto.PetsitterResponseDto;
import com.pet.demo.service.PetsitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/petsitters")
public class PetsitterController {

    private final PetsitterService petsitterService;

    @PostMapping
    public ResponseEntity<PetsitterResponseDto> createPetsitter(
            @RequestBody PetsitterRequestDto requestDto,
            HttpSession session) {
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(petsitterService.createPetsitter(userEmail, requestDto));
    }

    @GetMapping("/me")
    public ResponseEntity<PetsitterResponseDto> getMyPetsitterInfo(HttpSession session) {
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(petsitterService.getPetsitterByEmail(userEmail));
    }

    @GetMapping
    public ResponseEntity<List<PetsitterResponseDto>> getAllPetsitters() {
        return ResponseEntity.ok(petsitterService.getAllPetsitters());
    }

    @PutMapping
    public ResponseEntity<PetsitterResponseDto> updatePetsitter(
            @RequestBody PetsitterRequestDto requestDto,
            HttpSession session) {
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(petsitterService.updatePetsitterByEmail(userEmail, requestDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePetsitter(HttpSession session) {
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        petsitterService.deletePetsitterByEmail(userEmail);
        return ResponseEntity.ok().build();
    }
} 