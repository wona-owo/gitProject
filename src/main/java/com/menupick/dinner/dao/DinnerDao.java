package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.DinnerMember;
import com.menupick.dinner.vo.Food;
import com.menupick.member.model.vo.Member;

public class DinnerDao {

	public ArrayList<Dinner> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM TBL_DINNER";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 매장 DB가져오기
				Dinner d = new Dinner();

				d.setDinnerNo(rset.getString("dinner_no")); // 식당코드
				d.setDinnerName(rset.getString("dinner_name")); // 식당이름
				d.setDinnerAddr(rset.getString("dinner_addr")); // 주소
				d.setDinnerEmail(rset.getString("dinner_email")); // 이메일
				d.setDinnerPhone(rset.getString("dinner_phone")); // 매장번호
				d.setDinnerConfirm(rset.getString("dinner_confirm")); // 승인여부
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Dinner> likeDinner(Connection conn, String dinnerNo, String dinnerName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TBL_DINNER";
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("DINNER_NO"));
				d.setDinnerName(rset.getString("DINNER_NAME"));
				d.setDinnerAddr(rset.getString("DINNER_ADDR"));
				d.setDinnerOpen(rset.getString("DINNER_OPEN"));
				d.setDinnerClose(rset.getString("DINNER_CLOSE"));
				d.setDinnerPhone(rset.getString("DINNER_PHONE"));
				d.setDinnerEmail(rset.getString("DINNER_EMAIL"));
				d.setDinnerParking(rset.getString("DINNER_PARKING"));
				d.setDinnerMaxPerson(rset.getString("DINNER_MAX_PERSON"));
				d.setBusiNum(rset.getString("BUSI_NUM"));
				d.setDinnerId(rset.getString("DINNER_ID"));
				d.setDinnerPw(rset.getString("DINNER_PW"));
				d.setDinnerConfirm(rset.getString("DINNER_CONFIRM"));
				dinnerList.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinnerList;
	}

	public ArrayList<Book> checkReservation(Connection conn, String dinnerNo, String displayMonth, String displayYear) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<Book> bookList = new ArrayList<>();
		String query = "select book_no, book_date from tbl_book where book_no = ? and extract(month from book_date) = ? and extract(year from book_date) = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setString(2, displayMonth);
			pt.setString(3, displayYear);

			rt = pt.executeQuery();

			while (rt.next()) {
				Book b = new Book();
				b.setBookNo(rt.getString("book_no"));
				b.setBookDate(rt.getString("book_date"));
				bookList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return bookList;
	}

	public ArrayList<Food> filterNation(Connection conn, String foodNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TBL_FOOD";
		ArrayList<Food> foodList = new ArrayList<Food>();
		Food food = new Food();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				food.setFoodNo(rset.getString("FOOD_NO"));
				food.setFoodName(rset.getString("FOOD_NAME"));
				food.setFoodNation(rset.getString("FOOD_NATION"));
				food.setFoodCat(rset.getString("FOOD_CAT"));
				foodList.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return foodList;
	}

	// DB에서 식당 주소 리스트 및 이름 반환
	public ArrayList<Address> getDinnerAddress(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select dinner_name, dinner_addr from tbl_dinner where dinner_confirm='y'";
		ArrayList<Address> addressList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String name = rset.getString("dinner_name");
				String addr = rset.getString("dinner_addr");

				// Address 객체 생성 후 리스트에 추가
				addressList.add(new Address(name, addr));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return addressList;
	}

	public Dinner memberLogin(Connection conn, String loginId, String loginPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from tbl_dinner where dinner_id =? and dinner_pw =?";
		Dinner d = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			rset = pstmt.executeQuery();
			

			if (rset.next()) {
				d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinnerOpen"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNum(rset.getString("busi_num"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return d;
	}
}
