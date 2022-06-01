package com.sketchbook.reactiveservice.example.functional.handler;

import com.sketchbook.reactiveservice.example.dto.ExamplePostRequest;
import com.sketchbook.reactiveservice.example.dto.ExamplePostResponse;
import com.sketchbook.reactiveservice.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class ExampleHandler {

    @Autowired
    private ExampleService service;

    public Mono<ServerResponse> getExample(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("example"));
    }

    public Mono<ServerResponse> getExampleType1(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("example type 1"));
    }

    public Mono<ServerResponse> getExampleType2(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("example type 2"));
    }

    public Mono<ServerResponse> postExample(ServerRequest request) {
        Mono<ExamplePostRequest> examplePostRequestMono = request.bodyToMono(ExamplePostRequest.class);
        Mono<ExamplePostResponse> examplePostResponseMono = service.create(examplePostRequestMono);
        return examplePostResponseMono.flatMap(res -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(res)));
    }

    public Mono<ServerResponse> putExample(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<ExamplePostRequest> examplePostRequestMono = request.bodyToMono(ExamplePostRequest.class);
        Mono<ExamplePostResponse> examplePostResponseMono = service.update(id, examplePostRequestMono);
        return examplePostResponseMono.flatMap(res -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(res)));
    }

    public Mono<ServerResponse> putSpecificIdExample(ServerRequest request) {
        return ServerResponse
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("not allow to edit this example"));
    }
}
