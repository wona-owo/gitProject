package com.menupick.dinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Photo {
	private String photoNo;
	private String dinerNo;
	private String photoName;
	private String photoPath;
}
