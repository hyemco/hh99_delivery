package com.hh99.delivery.validator;

import com.hh99.delivery.dto.RestaurantRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public static void validateRestaurantInput(RestaurantRequestDto restaurantRequestDto) {
        int inputMinOrderPrice = restaurantRequestDto.getMinOrderPrice();
        if (inputMinOrderPrice < 1000 || inputMinOrderPrice > 100000 || (inputMinOrderPrice % 100 != 0)) {
            throw new IllegalArgumentException("최소 주문 금액이 조건에 맞지 않습니다.");
        }

        int inputDeliveryFee = restaurantRequestDto.getDeliveryFee();
        if (inputDeliveryFee < 0 || inputDeliveryFee > 10000 || (inputDeliveryFee % 500 != 0)) {
            throw new IllegalArgumentException("기본 배달비가 조건에 맞지 않습니다.");
        }
    }
}