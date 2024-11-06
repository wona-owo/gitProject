package com.menupick.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.menupick.common.JDBCTemplate;
import com.menupick.member.model.vo.Member;

public class MemberDao {

	public Member memberLogin(Connection conn, String loginId, String loginPw) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		
		String query ="select * from tbl_member where member_id =? and member_pw =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
				m.setAdultValid(rset.getString("adult_valid"));
				m.setMemberLevel(rset.getInt("member_level"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
	
		return m;
	}

	//회원 관리 페이지(회원 조회)
	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset =null;
		
		String query = "select * from tbl_member order by member_no";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getString("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNick(rset.getString("member_nick"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setEnrollDate(rset.getString("enroll_date")); 					//테스트라 enrol인데 나중에 enroll로 수정해야함.(수정완)
				m.setAdultValid(rset.getString("adult_valid"));
				m.setMemberLevel(rset.getInt("member_level"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	//회원 관리 페이지(회원 삭제)
	public int selectRemove(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from tbl_member where member_no = ?";		 //GPT가 짜준 memberno가 문자열이 들어가 '?' 로 해놓음 추후에 보고 수정할 것.
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			
			result = pstmt.executeUpdate();
			
			System.out.println("Deleting member with memberNo: " + memberNo);
			System.out.println("result=" + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Member getMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		
		String query ="select * from tbl_member where member_no =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
				m.setEnrollDate(rset.getString("enroll_date"));     					//enroll 수정해야함. (수정완)
				m.setAdultValid(rset.getString("adult_valid"));
				m.setMemberLevel(rset.getInt("member_level"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
	
		return m;
	}

}
