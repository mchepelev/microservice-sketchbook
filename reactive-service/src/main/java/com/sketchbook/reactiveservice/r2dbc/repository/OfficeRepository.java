package com.sketchbook.reactiveservice.r2dbc.repository;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;

public interface OfficeRepository extends ReactiveSortingRepository<Office, Integer> {

    Flux<Office> findOfficesByNameIn(Collection<String> names);

    Flux<Office> findOfficesByCompanyId(Integer companyId, Pageable pageable);

    @Query("select * from team_management.office left join team_management.company on company.id = office.company_id where company.name = :companyName")
    Flux<Office> findOfficesByCompanyName(String companyName);
}
