package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
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

	public ArrayList<Book> checkReservation(String dinnerNo, String displayMonth, String displayYear) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bookList = null;
		bookList = dao.checkReservation(conn, dinnerNo, displayMonth, displayYear);
		JDBCTemplate.close(conn);

		System.out.println(bookList);

		return bookList;
	}

	public ArrayList<Food> filterNation(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Food> foodList = null;
		foodList = dao.filterNation(conn, foodNo);
		JDBCTemplate.close(conn);
		return foodList;
	}

	public ArrayList<Address> getDinnerAddress() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Address> addList = dao.getDinnerAddress(conn);

		JDBCTemplate.close(conn);
		return addList;
	}

	public ArrayList<Dinner> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public Dinner dinnerDetail(String dinnerNo, String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.dinnerDetail(conn, dinnerNo, foodNo);
		JDBCTemplate.close(conn);
		return dinner;
	}

}
