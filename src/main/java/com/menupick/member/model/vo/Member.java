package com.menupick.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // NoArgsConstructor == 기본생성자(매개변수 없는 생성자)
@AllArgsConstructor // AllArgsConstructor == 일반생성자(매개변수 있는 생성자)
@Data // @Data == getter & setter
public class Member {
	private String memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNick;
	private String memberPhone;
	private String memberAddr;
	private String memberGender;
	private String memberEmail;
	private String enrollDate;
	private String adultConfirm;
	private int memberLevel;
	
	private String dinnerNo; //즐겨찾기 식당
}
