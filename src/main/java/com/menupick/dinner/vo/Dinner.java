package com.menupick.dinner.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dinner {
	private String dinnerNo;
	private String dinnerName;
	private String dinnerAddr;
	private String dinnerOpen;
	private String dinnerClose;
	private String dinnerPhone;
	private String dinnerEmail;
	private String dinnerParking;
	private String dinnerMaxPerson;
	private String busiNo;
	private String dinnerId;
	private String dinnerPw;
	private String dinnerConfirm;
	
	private String memberNo;

	// 종속 데이터 변수 추가
	private ArrayList<Dinner> dinnerList;
	//food
	private String foodNo;
	private String foodName;
	private String foodNation;
	private String foodCat;

	private ArrayList<Photo> photoList;
}
