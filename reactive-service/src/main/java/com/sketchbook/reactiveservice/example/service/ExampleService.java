package com.sketchbook.reactiveservice.example.service;

import com.sketchbook.reactiveservice.example.dto.ExamplePostRequest;
import com.sketchbook.reactiveservice.example.dto.ExamplePostResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExampleService {

    public Mono<ExamplePostResponse> create(Mono<ExamplePostRequest> request) {
        return request.map(examplePostRequest -> new ExamplePostResponse()
                .setId(1)
                .setName(examplePostRequest.getName() + " [modified]")
                .setNumber(examplePostRequest.getNumber() + 1)
                .setText("[new text]"));
    }

    public Mono<ExamplePostResponse> update(Integer id, Mono<ExamplePostRequest> requestMono) {
        return requestMono.map(examplePostRequest -> new ExamplePostResponse()
                .setId(id)
                .setName(examplePostRequest.getName() + " [modified]")
                .setNumber(examplePostRequest.getNumber() + 1)
                .setText("[new text]"));
    }
}
