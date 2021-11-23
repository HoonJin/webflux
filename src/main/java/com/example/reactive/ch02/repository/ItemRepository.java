package com.example.reactive.ch02.repository;

import com.example.reactive.ch02.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

    Flux<Item> findByNameContaining(String name);

    Flux<Item> findByDescriptionContainingIgnoreCase(String description);

    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String name, String description);

    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String name, String description);
}
