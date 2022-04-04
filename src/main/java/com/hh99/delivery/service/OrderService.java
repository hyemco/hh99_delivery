package com.hh99.delivery.service;

import com.hh99.delivery.dto.order.FoodOrderDto;
import com.hh99.delivery.dto.order.FoodOrderRequestDto;
import com.hh99.delivery.dto.order.OrderRequestDto;
import com.hh99.delivery.dto.order.OrdersDto;
import com.hh99.delivery.model.Food;
import com.hh99.delivery.model.FoodOrder;
import com.hh99.delivery.model.Orders;
import com.hh99.delivery.model.Restaurant;
import com.hh99.delivery.repository.FoodRepository;
import com.hh99.delivery.repository.FoodOrderRepository;
import com.hh99.delivery.repository.OrderRepository;
import com.hh99.delivery.repository.RestaurantRepository;
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
    private final FoodOrderRepository foodOrderRepository;
    private final OrderRepository orderRepository;

    // 주문하기
    @Transactional
    public Orders registerNewOrder(OrderRequestDto requestDto) {
        // restaurantId로 음식점 찾기
        Long restaurantId = requestDto.getRestaurnatId();
        Restaurant isRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("음식점 정보를 찾을 수 없습니다."));

        // 주문 음식
        List<FoodOrderRequestDto> foodOrderRequestDtoList = requestDto.getFoods();
        List<Long> foodsId = getFoodsId(foodOrderRequestDtoList);
        List<Food> isFoodList = foodRepository.findAllById(foodsId);

        int foodTotalPrice = isRestaurant.getMinOrderPrice();

        // 주문 음식 저장
        List<FoodOrder> foodOrderList = saveFoodOrder(isFoodList, foodOrderRequestDtoList);
        return registerOrders(isRestaurant, foodTotalPrice, foodOrderList);
    }

    // 음식 아이디 생성 메소드
    private List<Long> getFoodsId(List<FoodOrderRequestDto> foodOrderRequestDtoList) {
        List<Long> foodsId = new ArrayList<>();
        for (FoodOrderRequestDto requestDto : foodOrderRequestDtoList) {
            foodsId.add(requestDto.getId());
        }
        return foodsId;
    }

    // List<FoodOrder> 저장 메소드
    private List<FoodOrder> saveFoodOrder(List<Food> isFoodList, List<FoodOrderRequestDto> foodOrderRequestDtoList) {
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (int i = 0; i < isFoodList.size(); i++) {
            String name = isFoodList.get(i).getName();
            int quantity = foodOrderRequestDtoList.get(i).getQuantity();
            int price = isFoodList.get(i).getPrice();
            FoodOrderDto foodOrderDto = new FoodOrderDto(name, quantity, price);
            FoodOrder foodOrder = new FoodOrder(foodOrderDto);
            foodOrderList.add(foodOrder);
        }
        return foodOrderRepository.saveAll(foodOrderList);
    }

    // 주문 저장 메소드
    private Orders registerOrders(Restaurant isRestaurant, int foodTotalPrice, List<FoodOrder> foodOrderList) {
        String restaurantName = isRestaurant.getName();
        int deliveryFee = isRestaurant.getDeliveryFee();
        int totalPrice = foodTotalPrice + deliveryFee;

        OrdersDto ordersDto = new OrdersDto(restaurantName, deliveryFee, totalPrice);
        Orders orders = new Orders(ordersDto);
        orders.addFoodOrderList(foodOrderList);
        return orderRepository.save(orders);
    }
}