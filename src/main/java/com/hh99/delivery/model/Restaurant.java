package com.hh99.delivery.model;

import com.hh99.delivery.dto.RestaurantRequestDto;
import com.hh99.delivery.validator.RestaurantValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    // 음식점 등록 시 사용
    public Restaurant(RestaurantRequestDto requestDto) {
        // 입력값 유효성 검사(Validation)
        RestaurantValidator.validateRestaurantInput(requestDto);

        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}