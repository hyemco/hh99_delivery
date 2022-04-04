package com.hh99.delivery.dto.order;

import com.hh99.delivery.model.FoodOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    private List<FoodOrder> foods;
}