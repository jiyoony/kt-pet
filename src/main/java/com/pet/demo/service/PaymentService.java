package com.pet.demo.service;

import com.pet.demo.domain.Payment; // 추가된 import
import com.pet.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired; // 추가된 import
import org.springframework.stereotype.Service; // 추가된 import

@Service // 추가된 어노테이션
public class PaymentService {

    @Autowired // 추가된 어노테이션
    private PaymentRepository paymentRepository; // 추가된 필드

    @Autowired // 추가된 어노테이션
    private ReservationService reservationService; // 추가된 필드

    public Payment createPayment(Payment payment) {
        Payment createdPayment = paymentRepository.save(payment); // 결제 저장
        reservationService.updateReservationStatus(payment.getReservationId(), "PAID"); // 예약 상태 업데이트
        return createdPayment; // 수정된 반환값
    }

    // ... 기존 코드 ...
} 