package com.menupick.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.menupick.common.JDBCTemplate;
import com.menupick.member.model.dao.MemberDao;
import com.menupick.member.model.vo.Member;

public class MemberService {
	MemberDao dao;

	public MemberService() {
		dao = new MemberDao();
	}

	public Member memberLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection(); // 오라클과 연결
		Member member = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		return member;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = dao.selectAllMember(conn);
		return list;
	}

	public int selectRemove(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectRemove(conn, memberNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member getMemberNo(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = dao.getMemberNo(conn, memberNo);
		
		JDBCTemplate.close(conn);
		return member;
	}
	
	public int insertMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, member);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
				
		return result;
	}
	
	public int idDuplChk(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.idDuplChk(conn,memberId);
		JDBCTemplate.close(conn);
		return result;
	}


}
