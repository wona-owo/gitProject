package com.menupick.member.model.service;

import java.sql.Connection;

import com.menupick.common.JDBCTemplate;
import com.menupick.member.model.dao.MemberDao;
import com.menupick.member.model.vo.Member;


public class MemberService {
	MemberDao dao;
	public MemberService() {
		
		dao = new MemberDao();
	}

	public Member memberLogin(String loginId, String loginPw) {

		Connection conn = JDBCTemplate.getConnection();// 오라클과 연결
		Member member = dao.memberLogin(conn, loginId, loginPw);
		JDBCTemplate.close(conn);
		
		return member;

	}

}
