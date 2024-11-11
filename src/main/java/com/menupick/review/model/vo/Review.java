package com.menupick.review.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // NoArgsConstructor == 기본생성자(매개변수 없는 생성자)
@AllArgsConstructor // AllArgsConstructor == 일반생성자(매개변수 있는 생성자)
@Data // @Data == getter & setter

public class Review {
	private String reviewNo;      // 리뷰 번호 (Primary Key)
    private String dinnerNo;      // 식당 번호 (Foreign Key)
    private String memberNo;      // 회원 번호 (Foreign Key)
    private String reviewContent; // 리뷰 내용
    private byte[] reviewImage;   // 리뷰 이미지 (Blob)
    private Date reviewDate;      // 리뷰 작성일
    private String dinnerName;      // 식당 이름(조인)
}
