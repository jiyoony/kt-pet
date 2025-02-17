package com.pet.demo.controller;

import com.pet.demo.dto.AnimalCodeRequestDto;
import com.pet.demo.dto.AnimalCodeResponseDto;
import com.pet.demo.service.AnimalCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animal-codes")
public class AnimalCodeController {

    private final AnimalCodeService animalCodeService;

    @PostMapping
    public ResponseEntity<AnimalCodeResponseDto> createAnimalCode(@RequestBody AnimalCodeRequestDto requestDto, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("email");
        AnimalCodeResponseDto createdAnimalCode = animalCodeService.registerAnimalCode(requestDto, userEmail);
        return ResponseEntity.ok(createdAnimalCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalCodeResponseDto> getAnimalCode(@PathVariable Long id) {
        return ResponseEntity.ok(animalCodeService.getAnimalCodeByCode(id));
    }

    @GetMapping
    public ResponseEntity<List<AnimalCodeResponseDto>> getAllAnimalCodes() {
        return ResponseEntity.ok(animalCodeService.getAllAnimalCodes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalCodeResponseDto> updateAnimalCode(@PathVariable Long id, @RequestBody AnimalCodeRequestDto requestDto, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("email");
        AnimalCodeResponseDto updatedAnimalCode = animalCodeService.updateAnimalCode(id, requestDto, userEmail);
        return ResponseEntity.ok(updatedAnimalCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimalCode(@PathVariable Long id, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("email");
        animalCodeService.deleteAnimalCode(id, userEmail);
        return ResponseEntity.ok().build();
    }
} 