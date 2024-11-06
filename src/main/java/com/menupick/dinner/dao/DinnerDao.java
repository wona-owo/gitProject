package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;

public class DinnerDao {

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

	public ArrayList<Book> likeDinner(Connection conn, String dinnerNo, String displayMonth, String displayYear) {
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
}
