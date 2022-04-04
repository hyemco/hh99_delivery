package com.hh99.delivery.model;

import com.hh99.delivery.dto.order.FoodOrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable = false)
    private Orders orders;

//    @ManyToOne
//    @JoinColumn(name="FOOD_ID", nullable = false)
//    private Food food;

    public FoodOrder(FoodOrderDto foodOrderDto) {
        this.name = foodOrderDto.getName();
        this.quantity = foodOrderDto.getQuantity();
        this.price = foodOrderDto.getPrice();
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}