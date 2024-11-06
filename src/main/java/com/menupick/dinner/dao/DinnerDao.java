package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
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
			
			while(rset.next()) {
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
                d.setDinnerAdmit(rset.getString("DINNER_ADMIT"));
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
}
