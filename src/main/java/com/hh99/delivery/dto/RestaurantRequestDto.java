package com.hh99.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
