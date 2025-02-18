package com.pet.demo.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.pet.demo.repository.UserRepository;
import org.springframework.core.annotation.Order;
import java.util.ArrayList;
import java.util.List;
import com.pet.demo.util.DummyDataInit;

@Component
@Order(1)
@DummyDataInit
@RequiredArgsConstructor
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository; // UserRepository를 주입받습니다.

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            System.out.println("[User] 더미 데이터 존재");
        } else {
            List<User> userList = new ArrayList<>();

            User user1 = User.builder()
                    .name("John Doe")
                    .email("john.doe@example.com")
                    .password("123")
                    .phone("010-1234-5678")
                    .active("Y")
                    .role("user")
                    .build();

            User user2 = User.builder()
                    .name("Jane Smith")
                    .email("jane.smith@example.com")
                    .password("123")
                    .phone("010-9876-5432")
                    .active("Y")
                    .role("admin")
                    .build();

            User user3 = User.builder()
                    .name("Alice Johnson")
                    .email("alice.johnson@example.com")
                    .password("123")
                    .phone("010-5555-6666")
                    .active("N")
                    .role("petsitter")
                    .build();

            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            userRepository.saveAll(userList);
        }
    }
} 