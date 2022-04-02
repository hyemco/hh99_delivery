package com.hh99.delivery.service;

import com.hh99.delivery.dto.FoodRequestDto;
import com.hh99.delivery.model.Food;
import com.hh99.delivery.model.Restaurant;
import com.hh99.delivery.repository.FoodRepository;
import com.hh99.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    // 음식 등록
    @Transactional
    public void registerFood(List<FoodRequestDto> requestDtoList, Long restaurantId) {
        // id로 음식점 찾기
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow( () -> new IllegalArgumentException("해당 레스토랑을 찾을 수 없습니다."));

        // 음식점 id로 음식점에 등록된 음식 찾기
        List<Food> existFoods = foodRepository.findAllByRestaurantId(restaurant);
        // 음식 이름 리스트에 저장
        List<String> existFoodList = new ArrayList<>();
        for (Food existFood : existFoods) {
            existFoodList.add(existFood.getName());
        }

        // food 리스트 DB 저장
        List<Food> registerFoodList = new ArrayList<>();
        for (FoodRequestDto requestDto : requestDtoList) {
            requestDto.setRestaurant(restaurant);
            Food food = new Food(requestDto);
            registerFoodList.add(food);
        }
        foodRepository.saveAll(registerFoodList);

    }

    // 메뉴판 조회
    public List<Food> getMenu(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑을 찾을 수 없습니다."));
        return foodRepository.findAllByRestaurantId(restaurant);
    }
}