package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;

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
		System.out.println(dinnerList);
		return dinnerList;
	}

	public ArrayList<Book> checkReservation(String dinnerNo, String displayMonth, String displayYear) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bookList = null;
		bookList = dao.likeDinner(conn, dinnerNo, displayMonth, displayYear);
		JDBCTemplate.close(conn);

		System.out.println(bookList);

		return bookList;
	}

}
