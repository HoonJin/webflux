package com.example.reactive.ch02.entity;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartItem {

    private Item item;

    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increment() {
        this.quantity += 1;
    }
}
