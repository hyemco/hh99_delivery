package com.hh99.delivery.service;

import com.hh99.delivery.dto.order.*;
import com.hh99.delivery.model.*;
import com.hh99.delivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    // 주문하기
    @Transactional
    public OrderResponseDto registerNewOrder(OrderRequestDto requestDto) {
        //레스토랑 ID로 주문 음식점 정보 가져오기
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("음식점 정보를 찾을 수 없습니다."));

        String restaurantName = restaurant.getName();
        int minOrderPrice = restaurant.getMinOrderPrice();
        int deliveryFee = restaurant.getDeliveryFee();

        // 주문 음식 저장
        Orders orders = new Orders(restaurant);
        orderRepository.save(orders);

        List<OrderResponseFoodsDto> foods = new ArrayList<>();
        int totalPrice = 0;
        for (OrderItemRequestDto eachItem : requestDto.getFoods()) {
            Food food = foodRepository.findById(eachItem.getId())
                    .orElseThrow(() -> new IllegalArgumentException("음식점에 해당 메뉴가 존재하지 않습니다."));

            int quantity = eachItem.getQuantity();
            int price = food.getPrice() * quantity;

            OrderItem orderItem = new OrderItem(orders, food, quantity, price);
            orderItemRepository.save(orderItem);

            OrderResponseFoodsDto eachFoodResponse = new OrderResponseFoodsDto(food.getName(), quantity, price);
            foods.add(eachFoodResponse);
            totalPrice += price;
        }

        totalPrice += deliveryFee;
        return new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);
    }

    // 주문 조회하기
    public List<OrderResponseDto> getOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderResponseDto> orderList = new ArrayList<>();

        for (Orders eachOrder : orders) {
            String restaurantName = eachOrder.getRestaurant().getName();
            int deliveryFee = eachOrder.getRestaurant().getDeliveryFee();

            List<OrderResponseFoodsDto> foods = new ArrayList<>();
            List<OrderItem> orderFoods = orderItemRepository.findAllByOrdersId(eachOrder.getId());
            int totalPrice = 0;
            for (OrderItem eachItem : orderFoods) {
                String name = eachItem.getFood().getName();
                int quantity = eachItem.getQuantity();
                int price = eachItem.getPrice();

                OrderResponseFoodsDto eachItems = new OrderResponseFoodsDto(name, quantity, price);
                foods.add(eachItems);
                totalPrice += price;
            }
            totalPrice += deliveryFee;
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);
            orderList.add(orderResponseDto);
        }
        return orderList;
    }
}