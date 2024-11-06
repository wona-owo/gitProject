package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;



import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Dinner;

public class DinnerService {

	public ArrayList<Dinner> likeDinner(ArrayList<Dinner> dinner) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = new ArrayList<Dinner>();
		return null;
	}

}
