package com.menupick.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.vo.Member;

public class MemberDao {

	public Member memberLogin(Connection conn, String loginId, String loginPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from tbl_member where member_id =? and member_pw =?";
		Member m = null;

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
				m.setAdultConfirm(rset.getString("adult_confirm"));
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

	// 회원 관리 페이지(회원 조회)
	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset = null;

		String query = "select * from tbl_member order by member_no";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
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
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setAdultConfirm(rset.getString("adult_confirm"));
				m.setMemberLevel(rset.getInt("member_level"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	// 회원 관리 페이지(회원 삭제)
	public int selectRemove(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		// TODO GPT가 짜준 memberno가 문자열이 들어가 '?' 로 해놓음 추후에 보고 수정할 것.
		String query = "delete from tbl_member where member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);

			result = pstmt.executeUpdate();

			System.out.println("Deleting member with memberNo: " + memberNo);
			System.out.println("result=" + result);

		} catch (SQLException e) {
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
		String query = "select * from tbl_member where member_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
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
				m.setAdultConfirm(rset.getString("adult_confirm"));
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
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into tbl_member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, N, 2)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberNick());
			pstmt.setString(5, member.getMemberPhone());
			pstmt.setString(6, member.getMemberAddr());
			pstmt.setString(7, member.getMemberGender());
			pstmt.setString(8, member.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//아이디 중복 체크
			public int idDuplChk(Connection conn, String memberId) {
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				String query = "select count(*) as cnt from tbl_member where member_id = ?";
				int cnt = 0;
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, memberId);
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						cnt = rset.getInt("cnt");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
				return 0;
			}

			public int nickDuplChk(Connection conn, String memberNick) {
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				String query = "select count(*) as cnt from tbl_member where member_nick = ?";
				int cnt = 0;
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, memberNick);
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						cnt = rset.getInt("cnt");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
				return 0;
			}

	public ArrayList<Dinner> memberLikeList(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ArrayList<Dinner> likeList = new ArrayList<>();
		ResultSet rset = null;
		
		String query = "Select * From tbl_dinner D left join tbl_like L on (d.dinner_no= l.dinner_no) where l.member_no ='?'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();
			ArrayList<Member> likelist = new ArrayList<>();
			
			while (rset.next()) {
			Dinner d = new Dinner();
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
			likeList.add(d);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		return likeList;
	}

}
