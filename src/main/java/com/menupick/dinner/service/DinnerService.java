package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;



import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Dinner;

public class DinnerService {

	DinnerDao dao;

	public DinnerService() {
		dao = new DinnerDao();
	}
	
	public ArrayList<Dinner> likeDinner(ArrayList<Dinner> dinner) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = new ArrayList<Dinner>();
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Dinner> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

}
