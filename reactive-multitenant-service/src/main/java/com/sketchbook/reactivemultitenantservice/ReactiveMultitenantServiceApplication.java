package com.sketchbook.reactivemultitenantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableEurekaClient
public class ReactiveMultitenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMultitenantServiceApplication.class, args);
    }

}
