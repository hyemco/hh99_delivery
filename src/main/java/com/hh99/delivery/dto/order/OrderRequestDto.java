package com.hh99.delivery.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderRequestDto {
    private final Long restaurantId;
    private final List<OrderItemRequestDto> foods;
}