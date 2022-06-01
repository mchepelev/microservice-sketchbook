package com.sketchbook.reactiveservice.r2dbc.repository;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
class OfficeRepositoryTest {

    @Autowired
    private OfficeRepository repository;

    @Test
    void findOfficesByName() {
        Flux<Office> offices = repository.findOfficesByNameIn(Arrays.asList("Animal Farm", "Barn", "FAKE NAME"));

        offices.as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void findOfficesByCompanyId() {
        Pageable pageable = PageRequest.of(0,1);

        Flux<Office> offices = repository.findOfficesByCompanyId(1, pageable);

        offices.as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findOfficesByCompanyName() {
        Flux<Office> offices = repository.findOfficesByCompanyName("Manor Farm");

        offices.as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();
    }
}