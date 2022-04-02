package com.hh99.delivery.dto;

import com.hh99.delivery.model.Restaurant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class FoodRequestDto {
    private Restaurant restaurant;
    private String name;
    private int price;
}