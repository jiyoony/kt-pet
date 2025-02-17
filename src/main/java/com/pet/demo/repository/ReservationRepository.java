package com.pet.demo.repository;

import com.pet.demo.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 추가적인 쿼리 메서드가 필요하면 여기에 정의
} 