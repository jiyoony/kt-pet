package com.pet.demo.repository;

import com.pet.demo.domain.Payment; // 추가된 import
import org.springframework.data.jpa.repository.JpaRepository; // 추가된 import

public interface PaymentRepository extends JpaRepository<Payment, Long> { // 추가된 인터페이스
    // ... 기존 코드 ...
} 