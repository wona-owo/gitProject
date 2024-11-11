package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Food;

public class DinnerDao {

	public ArrayList<Dinner> selectAllDinner(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM tbl_dinner ORDER BY dinner_name ASC";

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
				d.setBusiNo(rset.getString("BUSI_NO"));
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

	public ArrayList<Book> checkReservation(Connection conn, String dinnerNo, String justMonth, String displayYear) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<Book> bookList = new ArrayList<>();
		String query = "select * from tbl_book where dinner_no = ? and extract(month from book_date) = ? and extract(year from book_date) = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setInt(2, Integer.parseInt(justMonth));
			pt.setInt(3, Integer.parseInt(displayYear));

			rt = pt.executeQuery();

			while (rt.next()) {
				Book b = new Book();
				b.setBookNo(rt.getString("book_no"));
				b.setDinnerNo(rt.getString("dinner_no"));
				b.setMemberNo(rt.getString("member_no"));
				b.setBookDate(rt.getString("book_date"));
				b.setBookTime(rt.getString("book_time"));
				b.setBookCnt(rt.getInt("book_cnt"));
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

	public Dinner dinnerDetail(Connection conn, String dinnerNo, String foodNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Dinner d = null;
		String query = "select dinner_name, dinner_addr, dinner_open, dinner_close, dinner_phone, dinner_parking from tbl_dinner where dinner_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				d = new Dinner();
				d.setDinnerName(rset.getString("DINNER_NAME"));
				d.setDinnerAddr(rset.getString("DINNER_ADDR"));
				d.setDinnerOpen(rset.getString("DINNER_OPEN"));
				d.setDinnerClose(rset.getString("DINNER_CLOSE"));
				d.setDinnerPhone(rset.getString("DINNER_PHONE"));
				d.setDinnerParking(rset.getString("DINNER_PARKING"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return d;
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
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return d;
	}

	public String convertDateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
		return formatter.format(date);
	}

	public ArrayList<Book> getReservationData(Connection conn, String dinnerNo, String date) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<Book> book = new ArrayList<Book>();
		Book b = null;
		String query = "select * from tbl_book where dinner_no = ? and book_date = ?";

		try {
			System.out.println(dinnerNo);
			System.out.println(date);
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setString(2, date);
			rt = pt.executeQuery();

			while (rt.next()) {
				b = new Book();
				b.setBookNo(rt.getString("book_no"));
				b.setDinnerNo(rt.getString("dinner_no"));
				b.setMemberNo(rt.getString("member_no"));

				// Fetch `DATE` and convert to `String`
				Date bookDate = rt.getDate("book_date");
				b.setBookDate(convertDateToString(bookDate)); // Format the date to string

				b.setBookTime(rt.getString("book_time"));
				b.setBookCnt(rt.getInt("book_cnt"));
				book.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return book;
	}

	public ArrayList<Dinner> selectAllAdminDinner(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM tbl_dinner";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
