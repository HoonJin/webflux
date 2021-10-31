package com.example.reactive.ch02.repository;

import com.example.reactive.ch02.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
