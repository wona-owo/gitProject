package com.menupick.dinner.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Food {
	private String foodNo;
	private String foodName;
	private String foodNation;
	private String foodCat;

	private ArrayList<Food> foodList;

}
