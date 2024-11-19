package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInfo {
	private String bookNo;
	private String bookDate;
	private String bookTime;
	private int bookCnt;

	private String memberNo;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	
	private String dinnerName;
}
