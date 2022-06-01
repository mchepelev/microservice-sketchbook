package com.sketchbook.reactivemultitenantservice.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {

//    usual way to config. not work with multitenant
/*    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "mssk")
                        .option(PASSWORD, "mssk")
                        .option(DATABASE, "mssk")
                        .build());
    }*/

    @Bean
    public PostgresqlConnectionConfiguration.Builder postgresqlConnectionConfiguration() {
        return PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .applicationName("reactive-multitenant-service")
                .database("mssk")
                .username("mssk")
                .password("mssk");
    }

    @Bean
    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(postgresqlConnectionConfiguration()
                .build());
    }
}
