package com.sketchbook.reactiveservice.example.functional.route;

import com.sketchbook.reactiveservice.example.functional.handler.ExampleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ExampleRoute {

    @Autowired
    private ExampleHandler exampleHandler;

    @Bean
    public RouterFunction<ServerResponse> exampleRoutes() {
        RequestPredicate getExample = RequestPredicates.GET("/example");
        RequestPredicate getExampleType1 = RequestPredicates.GET("/example")
                .and(RequestPredicates.queryParam("type", "1"));
        RequestPredicate getExampleType2 = RequestPredicates.GET("/example")
                .and(RequestPredicates.queryParam("type", "2"));

        RequestPredicate postExample = RequestPredicates.POST("/example")
                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON));

        RequestPredicate putSpecificIdExample = RequestPredicates.methods(HttpMethod.PATCH, HttpMethod.PUT)
                .and(RequestPredicates.path("/example/{id:(?:(?:\\b0\\b)|(?:\\b99\\b))}"))
                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON));
        RequestPredicate putExample = RequestPredicates.methods(HttpMethod.PATCH, HttpMethod.PUT)
                .and(RequestPredicates.path("/example/{id}"))
                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON));

        RouterFunction<ServerResponse> exampleRoutes = RouterFunctions
                .route(postExample, exampleHandler::postExample)

                .andRoute(getExampleType1, exampleHandler::getExampleType1)
                .andRoute(getExampleType2, exampleHandler::getExampleType2)
                .andRoute(getExample, exampleHandler::getExample)

                .andRoute(putSpecificIdExample, exampleHandler::putSpecificIdExample)
                .andRoute(putExample, exampleHandler::putExample);

        return RouterFunctions.nest(RequestPredicates.path("/func"), exampleRoutes);
    }
}
