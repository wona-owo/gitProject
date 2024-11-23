package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Photo {
	public String photoNo;
	public String dinnerNo;
	public String reviewNo;
	public String photoName;
	public String photoPath;
}
