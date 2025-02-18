package com.pet.demo.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import com.pet.demo.util.DummyDataInit;
import com.pet.demo.repository.AnimalCodeRepository;


@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class AnimalCodeInitializer implements ApplicationRunner {

    private final AnimalCodeRepository animalCodeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (animalCodeRepository.count() > 0) {
            log.info("[AnimalCode]더미 데이터 존재");
        } else {
            List<AnimalCode> animalCodeList = new ArrayList<>();

            AnimalCode animalCode1 = AnimalCode.builder()
                    .code("CODE1")
                    .mainCategory("Mammal")
                    .subCategory("Dog")
                    .active("Y")
                    .build();
            AnimalCode animalCode2 = AnimalCode.builder()
                    .code("CODE2")
                    .mainCategory("Mammal")
                    .subCategory("Cat")
                    .active("Y")
                    .build();
            AnimalCode animalCode3 = AnimalCode.builder()
                    .code("CODE3")
                    .mainCategory("Bird")
                    .subCategory("Parrot")
                    .active("N")
                    .build();

            animalCodeList.add(animalCode1);
            animalCodeList.add(animalCode2);
            animalCodeList.add(animalCode3);

            animalCodeRepository.saveAll(animalCodeList);
        }
    }
}
