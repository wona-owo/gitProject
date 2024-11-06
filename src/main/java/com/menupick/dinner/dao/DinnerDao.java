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

	public ArrayList<Dinner> likeDinner(Connection conn, String dinnerNo, String dinnerName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TBL_DINNER";
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Dinner d = new Dinner();
                d.setDinnerNo(rset.getString("DINNER_NO"));
                d.setDinnerName(rset.getString("DINNER_NAME"));
                d.setDinnerAddr(rset.getString("DINNER_ADDR"));
                d.setDinnerOpen(rset.getString("DINNER_OPEN"));
                d.setDinnerClose(rset.getString("DINNER_CLOSE"));
                d.setDinnerPhone(rset.getString("DINNER_PHONE"));
                d.setDinnerParking(rset.getString("DINNER_PARKING"));
                d.setDinnerTimeMax(rset.getString("DINNER_MAX_PERSON"));
                d.setBuisNum(rset.getString("BUSI_NO"));
                d.setDinnerId(rset.getString("DINNER_ID"));
                d.setDinnerPw(rset.getString("DINNER_PW"));
                d.setDinnerAdmit(rset.getString("DINNER_CONFIRM"));
				dinnerList.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
			
			while(rset.next()) {
				food.setFoodNo(rset.getString("FOOD_NO"));
				food.setFoodName(rset.getString("FOOD_NAME"));
				food.setFoodNation(rset.getString("FOOD_NATION"));
				food.setFoodCat(rset.getString("FOOD_CAT"));
				foodList.add(food);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return foodList;
	}
}
