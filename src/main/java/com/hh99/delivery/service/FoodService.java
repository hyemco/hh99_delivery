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
    public List<Food> registerFood(List<FoodRequestDto> requestDtoList, Long restaurantId) {
        // id로 음식점 찾기
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow( () -> new IllegalArgumentException("해당 레스토랑을 찾을 수 없습니다."));

        // 음식점 id로 음식점에 등록된 음식 찾기
        List<Food> existFoods = foodRepository.findAllByRestaurant(restaurant);
        // 음식 이름 리스트에 저장
        List<String> existFoodName = new ArrayList<>();
        for (Food existFood : existFoods) {
            existFoodName.add(existFood.getName());
        }

        // 기존 등록 음식과 중복 확인
        for(FoodRequestDto requestDto : requestDtoList){
            //넣으려는 음식 이름
            String inputFoodName = requestDto.getName();
            for(String eachFoodName : existFoodName){
                if(inputFoodName.equals(eachFoodName)) {
                    throw new IllegalArgumentException("이미 저장된 음식명과 동일한 음식명입니다.");
                }
            }
        }

        // food 리스트 DB 저장
        List<Food> registerFoodList = new ArrayList<>();
        for (FoodRequestDto requestDto : requestDtoList) {
            String name = requestDto.getName();
            int price = requestDto.getPrice();
            Food food = new Food(name, price, restaurant);
            foodRepository.save(food);
            registerFoodList.add(food);
        }
        return registerFoodList;
    }

    // 메뉴판 조회
    public List<Food> getMenu(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑을 찾을 수 없습니다."));
        return foodRepository.findAllByRestaurant(restaurant);
    }
}