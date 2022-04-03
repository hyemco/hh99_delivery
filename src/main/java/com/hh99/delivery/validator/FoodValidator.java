package com.hh99.delivery.validator;

import com.hh99.delivery.dto.FoodRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodValidator {
    // 음식 등록 시 조건 확인
    public static void validateFoodInput(List<FoodRequestDto> requestDtoList) {
        for (FoodRequestDto requestDto : requestDtoList) {
            // 음식 등록 시 가격 조건 확인
            int inputPrice = requestDto.getPrice();
            if (inputPrice < 100 || inputPrice > 1000000 || (inputPrice % 100 != 0)) {
                throw new IllegalArgumentException("음식 가격이 조건에 맞지 않습니다.");
            }
        }

        // 음식 등록 시 중복 입력 확인
        for (int i = 0; i < requestDtoList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (requestDtoList.get(i).getName().equals(requestDtoList.get(j).getName())) {
                    throw new IllegalArgumentException("입력한 음식명 중 동일한 음식명이 존재합니다.");
                }
            }
        }
    }
}