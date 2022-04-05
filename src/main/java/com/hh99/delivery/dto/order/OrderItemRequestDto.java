package com.hh99.delivery.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderItemRequestDto {
    private final Long id;
    private final int quantity;
}