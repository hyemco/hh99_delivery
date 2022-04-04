package com.hh99.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class OrdersDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
}