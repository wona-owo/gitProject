package com.menupick.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menupick.common.JDBCTemplate;
import com.menupick.member.model.vo.Member;

public class MemberDao {

	public Member memberLogin(Connection conn, String loginId, String loginPw) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;

		String query = "select * from tbl_member where member_id =? and member_pw =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getString("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNick(rset.getString("member_nick"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setAdultValid(rset.getString("adult_confirm"));
				m.setMemberLevel(rset.getInt("member_level"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return m;
	}

}
