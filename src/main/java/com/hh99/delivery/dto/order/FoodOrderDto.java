package com.hh99.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;
}