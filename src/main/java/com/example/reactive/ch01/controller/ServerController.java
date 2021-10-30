package com.example.reactive.ch01.controller;

import com.example.reactive.ch01.domain.Dish;
import com.example.reactive.ch01.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequiredArgsConstructor
public class ServerController {

    private final KitchenService kitchenService;

    @GetMapping(value = "/server", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serveDishes() {
        return kitchenService.getDishes();
    }

    @GetMapping(value = "/served", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> served() {
        return kitchenService.getDishes()
                .map(Dish::deliver);
    }
}
