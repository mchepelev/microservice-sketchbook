package com.sketchbook.reactivemultitenantservice.repository;

import com.sketchbook.reactivemultitenantservice.entity.User;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
public class UserTemplateRepository{
    @Autowired
    private PostgresqlConnectionConfiguration.Builder postgresqlConnectionConfiguration;

    public Mono<User> findById(Integer id) {
        return Mono.deferContextual(contextView -> {
            String schema = contextView.get("schema");
            PostgresqlConnectionFactory cf = new PostgresqlConnectionFactory(postgresqlConnectionConfiguration
                    .schema(schema)
                    .build());
            DatabaseClient client = DatabaseClient.create(cf);


//            String query = "select * from user where id = :id"; // not working. WHY?!
            String query = "select * from \""+ schema +"\".user where id = :id";
            return client.sql(query)
                    .bind("id", id)
                    .map(row -> new User()
                            .setId(row.get("id", Integer.class))
                            .setName(row.get("name", String.class)))
                    .first();
        });
    }
}
