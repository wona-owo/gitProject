package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Food;

public class DinnerService {
	DinnerDao dao;

	public DinnerService() {
		dao = new DinnerDao();
	}

	public ArrayList<Dinner> likeDinner(String dinnerNo, String dinnerName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> dinnerList = null;
		dinnerList = dao.likeDinner(conn, dinnerNo, dinnerName);
		JDBCTemplate.close(conn);
		return dinnerList;
	}

	public ArrayList<Food> filterNation(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Food> foodList = null;
		foodList = dao.filterNation(conn, foodNo);
		JDBCTemplate.close(conn);
		return foodList;
	}

	public ArrayList<Dinner> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);

		return list;
	}

}
