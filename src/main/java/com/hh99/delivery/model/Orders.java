package com.hh99.delivery.model;

import com.hh99.delivery.dto.order.OrdersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany(mappedBy = "orders")
    private List<FoodOrder> foods = new ArrayList<>();

    public Orders(OrdersDto ordersDto) {
        this.restaurantName = ordersDto.getRestaurantName();
        this.deliveryFee = ordersDto.getDeliveryFee();
        this.totalPrice = ordersDto.getTotalPrice();
    }

    public void addFoodOrderList(List<FoodOrder> foods) {
        for (FoodOrder foodOrder : foods) {
            foodOrder.setOrders(this);
        }
        this.foods = foods;
    }
}