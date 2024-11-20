package com.menupick.review.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // NoArgsConstructor == 기본생성자(매개변수 없는 생성자)
@AllArgsConstructor // AllArgsConstructor == 일반생성자(매개변수 있는 생성자)
@Data // @Data == getter & setter

public class ReviewReport {
	private String reviewNo;
    private String dinnerNo;
    private int reportCount;
}
