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
	private String dinnerParking;
	private String dinnerTimeMax;
	private String buisNum;
	private String dinnerId;
	private String dinnerPw;
	private String dinnerAdmit;
	
	//종속 데이터 변수 추가
	private ArrayList<Dinner> dinnerList;
}
