package com.hh99.delivery.service;

import com.hh99.delivery.dto.RestaurantRequestDto;
import com.hh99.delivery.model.Restaurant;
import com.hh99.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant registerRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Transactional
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
