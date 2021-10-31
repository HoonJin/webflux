package com.example.reactive.ch02.controller;

import com.example.reactive.ch02.entity.Cart;
import com.example.reactive.ch02.entity.CartItem;
import com.example.reactive.ch02.repository.CartRepository;
import com.example.reactive.ch02.repository.ItemRepository;
import com.example.reactive.ch02.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ch02")
public class Ch02HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    private final CartService cartService;

    @GetMapping
    public Mono<Rendering> home() {
        return Mono.just(
                Rendering.view("home.html")
                        .modelAttribute("items", itemRepository.findAll())
                        .modelAttribute("cart", cartRepository.findById("My Cart")
                                .defaultIfEmpty(new Cart("My Cart"))
                        ).build()
        );
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

}
