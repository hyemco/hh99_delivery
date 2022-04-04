package com.hh99.delivery.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FoodOrderRequestDto {
    private Long id;
    private int quantity;
}