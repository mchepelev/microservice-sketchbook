package com.sketchbook.reactivemultitenantservice.handler;

import com.sketchbook.reactivemultitenantservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

    @Autowired
    private UserService service;

    public Mono<ServerResponse> getUser(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return service.getUser(id)
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(user)));
    }
}