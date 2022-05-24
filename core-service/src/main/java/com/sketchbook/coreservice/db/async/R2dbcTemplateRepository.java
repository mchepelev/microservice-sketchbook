package com.sketchbook.coreservice.db.async;

import com.sketchbook.coreservice.db.async.entity.R2Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcTemplateRepository extends ReactiveCrudRepository<R2Company, Integer> {

    Mono<R2Company> findCompanyById(Integer id);
}
