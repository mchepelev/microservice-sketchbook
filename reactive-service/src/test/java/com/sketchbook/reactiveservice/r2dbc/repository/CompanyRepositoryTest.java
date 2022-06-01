package com.sketchbook.reactiveservice.r2dbc.repository;

import com.sketchbook.reactiveservice.r2dbc.entity.Company;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository repository;

    @Test
    void findById() {
        Mono<Company> companyMono = repository.findById(1);

        companyMono.as(StepVerifier::create)
                .expectNextMatches(r2Company -> Objects.equals("Manor Farm", r2Company.getName()))
                .verifyComplete();
    }

    @Test
    void findAll() {
        Flux<Company> companyFlux = repository.findAll();

        companyFlux.as(StepVerifier::create)
                .expectNextMatches(r2Company -> Objects.equals("Manor Farm", r2Company.getName()))
                .expectNextCount(0)
                .verifyComplete();
    }

}