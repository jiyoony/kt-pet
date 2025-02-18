package com.pet.demo.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.pet.demo.repository.PetsitterRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.annotation.Order;
import com.pet.demo.util.DummyDataInit;
import com.pet.demo.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class PetsitterInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PetsitterRepository petsitterRepository; // PetsitterRepository를 주입받습니다.

    @Override
    public void run(ApplicationArguments args) {
        if (petsitterRepository.count() > 0) {
            System.out.println("[Petsitter] 더미 데이터 존재");
        } else {
            List<Petsitter> petsitterList = new ArrayList<>();

            User user2 = userRepository.findByEmail("alice.johnson@example.com").orElseThrow(() -> new RuntimeException("User not found"));


            Petsitter petsitter1 = Petsitter.builder()
                    .user(user2) // User 객체를 적절히 설정해야 합니다.
                    .price(30000)
                    .startAt(LocalDateTime.now())
                    .endAt(LocalDateTime.now().plusHours(5))
                    .availablePetTypes("Dog, Cat")
                    .location("Seoul")
                    .build();


            petsitterList.add(petsitter1);

            petsitterRepository.saveAll(petsitterList);
        }
    }
} 