package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DinnerFoodDetail {
	private Dinner dinner;  // 매장 정보
    private Food food;      // 음식 정보
}
