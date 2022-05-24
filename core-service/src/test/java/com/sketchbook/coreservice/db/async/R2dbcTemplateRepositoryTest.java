package com.sketchbook.coreservice.db.async;

import com.sketchbook.coreservice.db.async.entity.R2Company;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

@SpringBootTest
@RunWith(SpringRunner.class)
class R2dbcTemplateRepositoryTest {

    @Autowired
    private R2dbcTemplateRepository repository;

    @Test
    void findById() {
        Mono<R2Company> companyMono = repository.findCompanyById(1);

        companyMono.as(StepVerifier::create)
                .expectNextMatches(r2Company -> Objects.equals("Manor Farm", r2Company.getName()))
                .verifyComplete();
    }
}