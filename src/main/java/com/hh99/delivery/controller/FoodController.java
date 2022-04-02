package com.hh99.delivery.controller;

import com.hh99.delivery.dto.FoodRequestDto;
import com.hh99.delivery.model.Food;
import com.hh99.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@RequestBody List<FoodRequestDto> requestDtoList, @PathVariable Long restaurantId) {
        foodService.registerFood(requestDtoList, restaurantId);
    }

    //
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId) {
        return foodService.getMenu(restaurantId);
    }
}