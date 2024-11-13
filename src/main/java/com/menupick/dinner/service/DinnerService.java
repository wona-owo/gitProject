package com.menupick.dinner.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
<<<<<<< HEAD

=======
import com.menupick.dinner.vo.DinnerFoodDetail;
import com.menupick.dinner.vo.Food;
import com.menupick.member.model.vo.Member;
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d

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

<<<<<<< HEAD
	// daniel
	public ArrayList<BookInfo> getReservationData(String dinnerNo, String date) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<BookInfo> bookInfoList = dao.getReservationData(conn, dinnerNo, date);
		JDBCTemplate.close(conn);
		return bookInfoList;
	}

	public ArrayList<Dinner> filterNation(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> foodList = dao.filterNation(conn, foodNo);
=======
	public ArrayList<Food> filterNation(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Food> foodList = null;
		foodList = dao.filterNation(conn, foodNo);
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
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
<<<<<<< HEAD
		Dinner dinner = dao.dinnerDetail(conn, dinnerNo);
		JDBCTemplate.close(conn);
		System.out.println(dinnerNo);
=======
		Dinner dinner = new Dinner();
		dinner = dao.dinnerDetail(conn, dinnerNo);
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
		return dinner;
	}

	public Dinner memberLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		return dinner;
	}
<<<<<<< HEAD

	// 음식 상세페이지 출력
	public Dinner foodDetail(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner food = dao.foodDetail(conn, foodNo);
=======
	//음식 상세페이지 출력
	public Food foodDetail(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Food food = new Food();
		food = dao.foodDetail(conn, foodNo);
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
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

	 public DinnerFoodDetail getDinnerDetailByNo(String dinnerNo) {
	        Connection conn = null;
	        DinnerFoodDetail dinnerDetail = null;


	        try {
	            conn = JDBCTemplate.getConnection();

	            // Dinner와 Food 정보를 함께 포함하는 DinnerDetail 객체를 가져옴
	            dinnerDetail = dao.getDinnerDetailByNo(conn, dinnerNo);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCTemplate.close(conn);
	        }

	        return dinnerDetail;
	    }




}
