package com.hh99.delivery.controller;

import com.hh99.delivery.dto.order.OrderRequestDto;
import com.hh99.delivery.model.Orders;
import com.hh99.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문하기
    @PostMapping("/order/request")
    public Orders registerNerOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.registerNewOrder(requestDto);
    }

    // 주문 조회하기
}