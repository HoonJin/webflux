package com.example.reactive.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/ch01")
public class Ch01HomeController {

    @GetMapping
    public Mono<String> home() {
        return Mono.just("home");
    }
}
