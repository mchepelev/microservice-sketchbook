package com.sketchbook.reactiveservice.r2dbc.repository;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class OfficeTemplateRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public Flux<Office> findAllByCompanyName(String companyName) {
        DatabaseClient client = DatabaseClient.create(connectionFactory);
        return client.sql("select * from team_management.office left join team_management.company on company.id = office.company_id where company.name = :companyNameArg")
                .bind("companyNameArg", companyName)
                .map(row -> new Office()
                        .setId(row.get("id", Integer.class))
                        .setName(row.get("name", String.class))
                        .setCompanyId(row.get("company_id", Integer.class)))
                .all();
    }
}
