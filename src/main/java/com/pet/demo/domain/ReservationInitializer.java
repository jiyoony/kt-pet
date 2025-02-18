package com.pet.demo.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.pet.demo.repository.ReservationRepository;
import com.pet.demo.repository.UserRepository;
import com.pet.demo.repository.PetsitterRepository;
import org.springframework.core.annotation.Order;
import com.pet.demo.util.DummyDataInit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
@DummyDataInit
@RequiredArgsConstructor
public class ReservationInitializer implements ApplicationRunner {

    private final ReservationRepository reservationRepository; // ReservationRepository를 주입받습니다.
    private final UserRepository userRepository; // UserRepository를 주입받습니다.
    private final PetsitterRepository petsitterRepository; // PetsitterRepository를 주입받습니다.

    @Override
    public void run(ApplicationArguments args) {
        if (reservationRepository.count() > 0) {
            System.out.println("[Reservation] 더미 데이터 존재");
        } else {
            List<Reservation> reservationList = new ArrayList<>();

            // User와 Petsitter 객체를 적절히 설정해야 합니다.
            User user = userRepository.findById(1L).orElse(null); // 예시로 ID가 1인 User
            Petsitter petsitter = petsitterRepository.findById(1L).orElse(null); // 예시로 ID가 1인 Petsitter

            Reservation reservation1 = Reservation.builder()
                    .user(user)
                    .petsitter(petsitter)
                    .startAt(LocalDateTime.now().plusDays(1))
                    .endAt(LocalDateTime.now().plusDays(1).plusHours(2))
                    .status("CONFIRMED")
                    .price(50000)
                    .location("Seoul")
                    .build();

            Reservation reservation2 = Reservation.builder()
                    .user(user)
                    .petsitter(petsitter)
                    .startAt(LocalDateTime.now().plusDays(2))
                    .endAt(LocalDateTime.now().plusDays(2).plusHours(3))
                    .status("PENDING")
                    .price(30000)
                    .location("Busan")
                    .build();

            reservationList.add(reservation1);
            reservationList.add(reservation2);

            reservationRepository.saveAll(reservationList);
        }
    }
} 