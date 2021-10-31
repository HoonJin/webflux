package com.example.reactive.ch02.config;

import com.example.reactive.ch02.entity.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class MonoDatabaseLoader {

    @Bean
    CommandLineRunner initialize(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.save(new Item("abc", 20.01));
            mongoOperations.save(new Item("def", 21));
        };
    }
}
