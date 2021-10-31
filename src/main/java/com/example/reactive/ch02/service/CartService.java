package com.example.reactive.ch02.service;

import com.example.reactive.ch02.entity.Cart;
import com.example.reactive.ch02.entity.CartItem;
import com.example.reactive.ch02.repository.CartRepository;
import com.example.reactive.ch02.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItems().stream().filter(cartItem -> id.equals(cartItem.getItem().getId()))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() ->
                                itemRepository.findById(id)
                                        .map(CartItem::new)
                                        .map(cartItem -> {
                                            cart.getCartItems().add(cartItem);
                                            return cart;
                                        }))
                )
                .flatMap(cartRepository::save);
    }
}
