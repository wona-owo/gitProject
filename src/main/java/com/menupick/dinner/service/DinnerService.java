package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

	public Dinner dinnerDetail(String dinnerNo, String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.dinnerDetail(conn, dinnerNo, foodNo);
		JDBCTemplate.close(conn);
		return dinner;
	}

	public Dinner memberLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		return dinner;
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

	public List<Dinner> searchDinnerByName(String dinnerName) {
		Connection conn = JDBCTemplate.getConnection();
	    List<Dinner> dinners = dao.searchDinnerByName(conn, dinnerName);
	    JDBCTemplate.close(conn);
	    return dinners;
	}
	//회원 조회 + 페이징 넘버
	public List<Dinner> getDinners(int page, int pageSize) {
		Connection conn = JDBCTemplate.getConnection();
		List<Dinner> dinners = dao.getDinners(conn, page, pageSize);

		JDBCTemplate.close(conn);
		return dinners;
	}
	//페이징 넘버
	public int getTotalDinnerCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalDinner = dao.getTotalDinnerCount(conn);
		
		JDBCTemplate.close(conn);
		return totalDinner;
	}
	
	//매장 조회
	public Dinner getDinnerNo(String dinnerNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.getDinnerNo(conn, dinnerNo);

		JDBCTemplate.close(conn);
		return dinner;
	}

	public List<Dinner> getDinnersSortedByName(String order) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<Dinner> dinners = dao.getDinnersSortedByName(conn,order);
		 
		 JDBCTemplate.close(conn);
		 return dinners;

	}

	public List<Dinner> getDinnersByApproval(String approved) {
Connection conn = JDBCTemplate.getConnection();
		
		List<Dinner> dinners = dao.getDinnersByApproval(conn,approved);
		 
		 JDBCTemplate.close(conn);
		 return dinners;
	}




}
