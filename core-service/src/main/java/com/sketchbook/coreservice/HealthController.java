package com.sketchbook.coreservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/ping")
    public String ping() {
        System.out.println("ping-pong");
        return "pong";
    }
}
