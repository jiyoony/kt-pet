package com.pet.demo.service;

import com.pet.demo.domain.Petsitter;
import com.pet.demo.domain.User;
import com.pet.demo.dto.PetsitterRequestDto;
import com.pet.demo.dto.PetsitterResponseDto;
import com.pet.demo.repository.PetsitterRepository;
import com.pet.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetsitterService {

    private final PetsitterRepository petsitterRepository;
    private final UserRepository userRepository;

    @Transactional
    public PetsitterResponseDto createPetsitter(String userEmail, PetsitterRequestDto requestDto) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Petsitter petsitter = Petsitter.builder()
            .user(user)
            .price(requestDto.price())
            .startAt(requestDto.startAt())
            .endAt(requestDto.endAt())
            .availablePetTypes(requestDto.availablePetTypes())
            .location(requestDto.location())
            .build();

        return PetsitterResponseDto.from(petsitterRepository.save(petsitter));
    }

    public PetsitterResponseDto getPetsitter(Long id, String userEmail) {
        Petsitter petsitter = petsitterRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));
            
        // 본인의 펫시터 정보만 조회 가능
        if (!petsitter.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("접근 권한이 없습니다.");
        }
        
        return PetsitterResponseDto.from(petsitter);
    }

    public List<PetsitterResponseDto> getAllPetsitters() {
        return petsitterRepository.findAll().stream()
            .map(PetsitterResponseDto::from)
            .collect(Collectors.toList());
    }

    public List<PetsitterResponseDto> getPetsittersByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return petsitterRepository.findByUserId(user.getId()).stream()
            .map(PetsitterResponseDto::from)
            .collect(Collectors.toList());
    }

    @Transactional
    public PetsitterResponseDto updatePetsitter(Long id, PetsitterRequestDto requestDto, String userEmail) {
        Petsitter petsitter = petsitterRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));
            
        // 본인의 펫시터 정보만 수정 가능
        if (!petsitter.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("접근 권한이 없습니다.");
        }

        Petsitter updatedPetsitter = Petsitter.builder()
            .id(petsitter.getId())
            .user(petsitter.getUser())
            .price(requestDto.price())
            .startAt(requestDto.startAt())
            .endAt(requestDto.endAt())
            .availablePetTypes(requestDto.availablePetTypes())
            .location(requestDto.location())
            .build();

        return PetsitterResponseDto.from(petsitterRepository.save(updatedPetsitter));
    }

    @Transactional
    public void deletePetsitter(Long id, String userEmail) {
        Petsitter petsitter = petsitterRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));
            
        // 본인의 펫시터 정보만 삭제 가능
        if (!petsitter.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("접근 권한이 없습니다.");
        }
        
        petsitterRepository.deleteById(id);
    }

    public PetsitterResponseDto getPetsitterByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
            
        return petsitterRepository.findByUserId(user.getId()).stream()
            .findFirst()
            .map(PetsitterResponseDto::from)
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));
    }

    @Transactional
    public PetsitterResponseDto updatePetsitterByEmail(String userEmail, PetsitterRequestDto requestDto) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
            
        Petsitter petsitter = petsitterRepository.findByUserId(user.getId()).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));

        Petsitter updatedPetsitter = Petsitter.builder()
            .id(petsitter.getId())
            .user(petsitter.getUser())
            .price(requestDto.price())
            .startAt(requestDto.startAt())
            .endAt(requestDto.endAt())
            .availablePetTypes(requestDto.availablePetTypes())
            .location(requestDto.location())
            .build();

        return PetsitterResponseDto.from(petsitterRepository.save(updatedPetsitter));
    }

    @Transactional
    public void deletePetsitterByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
            
        Petsitter petsitter = petsitterRepository.findByUserId(user.getId()).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Petsitter not found"));
            
        petsitterRepository.deleteById(petsitter.getId());
    }
} 