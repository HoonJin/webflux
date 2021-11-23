package com.example.reactive.ch02.service;

import com.example.reactive.ch02.entity.Item;
import com.example.reactive.ch02.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return itemRepository.findByNameContainingAndDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                } else {
                    return itemRepository.findByNameContainingOrDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                }
            } else {
                return itemRepository.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return itemRepository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return itemRepository.findAll();
            }
        }
    }

    public Flux<Item> searchByExample(String partialName, String partialDescription, boolean useAnd) {
        Item item = new Item(partialName, partialDescription, 0.0);

        ExampleMatcher matcher = (useAnd ? ExampleMatcher.matchingAll() : ExampleMatcher.matchingAny())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        Example<Item> exampleQuery = Example.of(item, matcher);
        return itemRepository.findAll(exampleQuery);
    }
}
