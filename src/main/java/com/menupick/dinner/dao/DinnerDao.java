package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Food;

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

				d.setDinnerNo(rset.getString("dinner_no"));// 식당코드
				d.setDinnerName(rset.getString("dinner_name"));// 식당이름
				d.setDinnerAddr(rset.getString("dinner_addr"));// 주소
				d.setDinnerEmail(rset.getString("dinner_email"));// 이메일
				d.setDinnerPhone(rset.getString("dinner_phone"));// 매장번호
				d.setDinnerConfirm(rset.getString("dinner_confirm"));// 승인여부
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
				d.setDinnerParking(rset.getString("DINNER_PARKING"));
				d.setDinnerTimeMax(rset.getString("DINNER_TIME_MAX"));
				d.setBuisNum(rset.getString("BUIS_NUM"));
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return foodList;
	}
}
