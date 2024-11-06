package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
	private String bookNo;
	private String dinnerNo;
	private String memberNo;
	private String bookDate;
	private String bookTime;
	private String book_cnt;
}
