package com.hh99.delivery.validator;

import com.hh99.delivery.dto.order.OrderItemRequestDto;
import com.hh99.delivery.dto.order.OrderRequestDto;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public static void validateOrderInput(OrderRequestDto requestDto) {
        for(OrderItemRequestDto eachItem : requestDto.getFoods()){
            if(eachItem.getQuantity() < 1 || eachItem.getQuantity() > 100){
                throw new IllegalArgumentException("주문 수량은 1 ~ 100 사이여야 합니다.");
            }
        }
    }

    // 최소 주문 가격 확인
    public static void validateOrderPrice(int minOrderPrice, int totalPrice){
        if (minOrderPrice > totalPrice){
            throw new IllegalArgumentException("최소 주문 가격 미만입니다. 메뉴를 추가해주세요.");
        }
    }
}