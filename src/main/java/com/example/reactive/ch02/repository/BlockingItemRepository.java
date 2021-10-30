package com.example.reactive.ch02.repository;

import com.example.reactive.ch02.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
