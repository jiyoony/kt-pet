package com.pet.demo.service;

import com.pet.demo.domain.AnimalCode;
import com.pet.demo.domain.User;
import com.pet.demo.dto.AnimalCodeRequestDto;
import com.pet.demo.dto.AnimalCodeResponseDto;
import com.pet.demo.repository.AnimalCodeRepository;
import com.pet.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@RequiredArgsConstructor //  final 붙은 아이들 생성자 만들어주는 annotation
@Service
public class AnimalCodeService {

    private final AnimalCodeRepository animalCodeRepository;
    private final UserRepository userRepository;

    @Transactional
    public AnimalCodeResponseDto registerAnimalCode(AnimalCodeRequestDto requestDto, String userEmail) {
        // Check if the user is an admin
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!user.getRole().equals("admin")) {
            throw new IllegalStateException("권한이 없습니다. 관리자만 동물 코드를 등록할 수 있습니다.");
        }

        animalCodeRepository.findByCode(requestDto.code()).ifPresent(animalCode -> {
            throw new IllegalArgumentException("이미 존재하는 동물 코드입니다.");
        });

        AnimalCode animalCode = AnimalCode.builder()
                .code(requestDto.code())
                .mainCategory(requestDto.mainCategory())
                .subCategory(requestDto.subCategory())
                .active("Y")
                .build();

        animalCodeRepository.save(animalCode);
        return AnimalCodeResponseDto.from(animalCode);
    }

    public AnimalCodeResponseDto getAnimalCodeByCode(Long id) {
        AnimalCode animalCode = animalCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("동물 코드가 존재하지 않습니다."));
        return AnimalCodeResponseDto.from(animalCode);
    }

    public List<AnimalCodeResponseDto> getAllAnimalCodes() {
        return animalCodeRepository.findAll().stream()
                .map(AnimalCodeResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnimalCodeResponseDto updateAnimalCode(Long id, AnimalCodeRequestDto requestDto, String userEmail) {
        // Check if the user is an admin
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!user.getRole().equals("admin")) {
            throw new IllegalStateException("권한이 없습니다. 관리자만 동물 코드를 수정할 수 있습니다.");
        }

        AnimalCode animalCode = animalCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("동물 코드가 존재하지 않습니다."));

        // Update the fields of the existing entity
        animalCode.update(requestDto.mainCategory(), requestDto.subCategory(), requestDto.code());

        // Save the updated entity
        animalCodeRepository.save(animalCode);
        return AnimalCodeResponseDto.from(animalCode);
    }

    @Transactional
    public void deleteAnimalCode(Long id, String userEmail) {
        // Check if the user is an admin
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!user.getRole().equals("admin")) {
            throw new IllegalStateException("권한이 없습니다. 관리자만 동물 코드를 삭제할 수 있습니다.");
        }

        AnimalCode animalCode = animalCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("동물 코드가 존재하지 않습니다."));
        animalCodeRepository.delete(animalCode);
    }
}
