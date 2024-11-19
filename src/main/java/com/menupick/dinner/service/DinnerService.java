package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.BookInfo;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.vo.Member;

public class DinnerService {
	DinnerDao dao;

	public DinnerService() {
		dao = new DinnerDao();
	}

	// 인기식당 페이지
	public ArrayList<Dinner> likeDinner(String dinnerNo, String dinnerName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> dinnerList = null;
		dinnerList = dao.likeDinner(conn, dinnerNo, dinnerName);
		JDBCTemplate.close(conn);
		return dinnerList;
	}

	// daniel
	public ArrayList<Book> checkReservation(String dinnerNo, String justMonth, String displayYear) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bookList = dao.checkReservation(conn, dinnerNo, justMonth, displayYear);
		JDBCTemplate.close(conn);
		return bookList;
	}

	// daniel
	public ArrayList<BookInfo> getReservationData(String dinnerNo, String date) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<BookInfo> bookInfoList = dao.getReservationData(conn, dinnerNo, date);
		JDBCTemplate.close(conn);
		return bookInfoList;
	}

	// daniel
	public int dinnerCancelReservaion(String bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.dinnerCancelReservaion(conn, bookNo);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public ArrayList<Dinner> filterNation(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> foodList = dao.filterNation(conn, foodNo);
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

	// 식당 상세페이지
	public Dinner dinnerDetail(String dinnerNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.dinnerDetail(conn, dinnerNo);
		JDBCTemplate.close(conn);
		System.out.println(dinnerNo);
		return dinner;
	}

	public Dinner memberLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		return dinner;
	}

	// 음식 상세페이지 출력
	public Dinner foodDetail(String foodNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner food = dao.foodDetail(conn, foodNo);
		JDBCTemplate.close(conn);
		return food;
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

	// 회원 조회 + 페이징 넘버
	public List<Dinner> getDinners(int page, int pageSize) {
		Connection conn = JDBCTemplate.getConnection();
		List<Dinner> dinners = dao.getDinners(conn, page, pageSize);
		JDBCTemplate.close(conn);
		return dinners;
	}

	// 페이징 넘버
	public int getTotalDinnerCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalDinner = dao.getTotalDinnerCount(conn);
		JDBCTemplate.close(conn);
		return totalDinner;
	}

	// 매장 조회
	public Dinner getDinnerNo(String dinnerNo) {
		Connection conn = JDBCTemplate.getConnection();
		Dinner dinner = dao.getDinnerNo(conn, dinnerNo);
		JDBCTemplate.close(conn);
		return dinner;
	}

	public List<Dinner> getDinnersSortedByName(String order) {
		Connection conn = JDBCTemplate.getConnection();
		List<Dinner> dinners = dao.getDinnersSortedByName(conn, order);
		JDBCTemplate.close(conn);
		return dinners;
	}

	public List<Dinner> getDinnersByApproval(String approved) {
		Connection conn = JDBCTemplate.getConnection();
		List<Dinner> dinners = dao.getDinnersByApproval(conn, approved);
		JDBCTemplate.close(conn);
		return dinners;
	}

	// daniel
	public Member getMemberDataForCancelingReservation(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = dao.getMemberDataForCancelingReservation(conn, memberNo);
		JDBCTemplate.close(conn);
		return member;
	}

	// 식당등록 (경래)
	public boolean insertDinner(Dinner dinner) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = false;

		result = dao.insertDinner(conn, dinner);

		if (result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 식당등록 아이디 중복체크 (경래)
	public int idDuplChk(String dinnerId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.idDuplChk(conn, dinnerId);
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateDinner(Dinner updDinner) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateDinner(conn, updDinner);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public int deleteDinner(String dinnerNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteDinner(conn, dinnerNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
}
