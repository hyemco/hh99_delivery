package com.hh99.delivery.controller;

import com.hh99.delivery.dto.order.OrderRequestDto;
import com.hh99.delivery.dto.order.OrderResponseDto;
import com.hh99.delivery.service.OrderService;
import com.hh99.delivery.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문하기
    @PostMapping("/order/request")
    public OrderResponseDto registerNewOrder(@RequestBody OrderRequestDto requestDto){
        // 주문 수량 유효성 검사
        OrderValidator.validateOrderInput(requestDto);
        return orderService.registerNewOrder(requestDto);
    }

    // 주문 조회하기
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }
}