package com.sketchbook.reactiveservice.example.controller;

import com.sketchbook.reactiveservice.example.dto.ExamplePostRequest;
import com.sketchbook.reactiveservice.example.dto.ExamplePostResponse;
import com.sketchbook.reactiveservice.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class ExampleController {
    @Autowired
    private ExampleService service;

//    not working. but using router/handler you can do it
/*    @RequestMapping(path = "/example/{id:(?:(?:\\b0\\b)|(?:\\b99\\b))}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Mono<ResponseEntity<String>> putSpecificIdExample(@PathVariable Integer id,
                                                             @RequestBody ExamplePostRequest request) {
        return Mono.just(ResponseEntity
                .badRequest()
                .body("not allow to edit this example"));
    }*/

    @RequestMapping(path = "/example/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Mono<ResponseEntity<ExamplePostResponse>> putExample(@PathVariable Integer id,
                                                                @RequestBody ExamplePostRequest request) {
        Mono<ExamplePostRequest> examplePostRequestMono = Mono.just(request);
        Mono<ExamplePostResponse> examplePostResponseMono = service.update(id, examplePostRequestMono);
        return examplePostResponseMono.map(ResponseEntity::ok);
    }

//    how webflux works with not mono?
    @GetMapping("/not-mono")
    public ResponseEntity<String> getNotMono() {
        return ResponseEntity
                .ok("not mono");
    }
}
