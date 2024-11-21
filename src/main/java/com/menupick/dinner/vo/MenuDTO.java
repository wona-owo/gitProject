package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuDTO {
	private String foodCat;
	private String foodNo;
	private String foodName;
	private int price;
}
