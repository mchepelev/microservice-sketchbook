package com.sketchbook.reactivemultitenantservice.router;

import com.sketchbook.reactivemultitenantservice.filter.TenantFilter;
import com.sketchbook.reactivemultitenantservice.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {
    @Autowired
    private UserHandler handler;

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return RouterFunctions.route(
                RequestPredicates.GET("/user/{id}"), handler::getUser)
                .filter(new TenantFilter());
    }
}
