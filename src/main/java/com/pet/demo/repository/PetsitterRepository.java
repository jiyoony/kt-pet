package com.pet.demo.repository;

import com.pet.demo.domain.Petsitter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetsitterRepository extends JpaRepository<Petsitter, Long> {
    List<Petsitter> findByUserId(Long userId);
} 