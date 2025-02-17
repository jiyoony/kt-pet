package com.pet.demo.repository;

import com.pet.demo.domain.AnimalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalCodeRepository extends JpaRepository<AnimalCode, Long> {
    Optional<AnimalCode> findByCode(String code);
    Optional<AnimalCode> findById(Long id);
} 