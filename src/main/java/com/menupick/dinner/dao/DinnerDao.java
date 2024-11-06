package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Dinner;

public class DinnerDao {

	public ArrayList<Dinner> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM TBL_DINNER";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			
			while(rset.next()){
				//매장 DB가져오기
				Dinner d = new Dinner();
				
				d.setDinnerNo(rset.getString("dinner_no"));//식당코드	
				d.setDinnerName(rset.getString("dinner_name"));//식당이름
				d.setDinnerAddr(rset.getString("dinner_addr"));//주소
				d.setDinnerEmail(rset.getString("dinner_email"));//이메일
				d.setDinnerPhone(rset.getString("dinner_phone"));//매장번호
				d.setDinnerConfirm(rset.getString("dinner_confirm"));//승인여부
				list.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

}
