package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Food;
import com.menupick.member.model.vo.Member;

public class DinnerService {
	DinnerDao dao;

	public DinnerService() {
		dao = new DinnerDao();
	}
	//인기식당 페이지
	public ArrayList<Dinner> likeDinner(String dinnerNo, String dinnerName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> dinnerList = null;
		dinnerList = dao.likeDinner(conn, dinnerNo, dinnerName);
		JDBCTemplate.close(conn);
		return dinnerList;
	}

	public ArrayList<Book> checkReservation(String dinnerNo, String justMonth, String displayYear) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bookList = null;
		bookList = dao.checkReservation(conn, dinnerNo, justMonth, displayYear);
		JDBCTemplate.close(conn);
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

	public ArrayList<Dinner> selectAllDinner() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllDinner(conn);
		JDBCTemplate.close(conn);
		return list;
	}


	//식당 상세페이지
	public Dinner dinnerDetail(String dinnerNo) {

		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = new Dinner();
		dinner = dao.dinnerDetail(conn, dinnerNo);
		return dinner;
	}

	public Dinner memberLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		return dinner;
	}
	//음식 상세페이지 출력
	public Food foodDetail(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Food food = new Food();
		food = dao.foodDetail(conn, foodNo);
		JDBCTemplate.close(conn);
		return food;
	}


	public ArrayList<Book> getReservationData(String dinnerNo, String date) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> book = dao.getReservationData(conn, dinnerNo, date);
		JDBCTemplate.close(conn);
		return book;
	}

	public ArrayList<Dinner> selectAllAdminDinner() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllAdminDinner(conn);
		JDBCTemplate.close(conn);
		return list;
	}


}
