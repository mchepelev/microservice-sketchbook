package com.sketchbook.reactiveservice.r2dbc.repository;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class OfficeTemplateRepositoryTest {

    @Autowired
    private OfficeTemplateRepository repository;

    @Test
    void findAllByCompanyName() {
        Flux<Office> offices = repository.findAllByCompanyName("Manor Farm");

        offices.as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();
    }
}