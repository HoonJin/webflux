package com.example.reactive.ch02.repository;

import com.example.reactive.ch02.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
