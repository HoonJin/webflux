package com.example.reactive.ch02.config;

import com.example.reactive.ch02.entity.Item;
import com.example.reactive.ch02.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository blockingItemRepository) {
        return args -> {
            blockingItemRepository.save(new Item("abc", 20.01));
            blockingItemRepository.save(new Item("def", 21));
        };
    }
}
