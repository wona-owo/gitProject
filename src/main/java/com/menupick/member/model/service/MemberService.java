package com.menupick.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Dinner;
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

	// admin(경래) - 회원 탈퇴
	public int selectRemove(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectRemove(conn, memberNo);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// admin(경래) - 회원 조회
	public Member getMemberNo(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = dao.getMemberNo(conn, memberNo);

		JDBCTemplate.close(conn);
		return member;
	}

	
	public int insertMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, member);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	
	public int idDuplChk(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.idDuplChk(conn, memberId);
		JDBCTemplate.close(conn);
		return result;
	}

	
	public int nickDuplChk(String memberNick) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.nickDuplChk(conn, memberNick);
		JDBCTemplate.close(conn);
		return result;
	}

	// admin(경래) - 선택한 회원 등급 변경
	public int updChgLevel(String memberNoArr, String memberLevelArr) {
		Connection conn = JDBCTemplate.getConnection();

		StringTokenizer st1 = new StringTokenizer(memberNoArr, "/");
		StringTokenizer st2 = new StringTokenizer(memberLevelArr, "/");

		boolean resultChk = true;

		while (st1.hasMoreTokens()) {
			String memberNo = st1.nextToken();
			String memberLevel = st2.nextToken();

			int result = dao.updChgLevel(conn, memberNo, memberLevel);

			if (result < 1) {
				resultChk = false;
				break;
			}
		}

		if (resultChk) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		if (resultChk) {
			return 1;
		} else {
			return 0;
		}
	}

	// admin(경래) - 선택한 회원 전체 삭제
	public int memberRemoveAll(String memberNoArr) {
		Connection conn = JDBCTemplate.getConnection();

		StringTokenizer st = new StringTokenizer(memberNoArr, "/");

		boolean resultChk = true;

		while (st.hasMoreTokens()) {
			String memberNo = st.nextToken();

			int result = dao.memberRemoveAll(conn, memberNo);

			if (result < 1) {
				resultChk = false;
				break;
			}
		}

		if (resultChk) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		if (resultChk) {
			return 1;
		} else {
			return 0;
		}
	}

	// admin(경래) - 회원 조회 + 페이징 넘버
	public List<Member> getMembers(int page, int pageSize) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> members = dao.getMembers(conn, page, pageSize);

		JDBCTemplate.close(conn);
		return members;
	}

	// admin(경래) - 페이징 넘버
	public int getTotalMemberCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalMembers = dao.getTotalMemberCount(conn);

		JDBCTemplate.close(conn);
		return totalMembers;
	}

	// 즐겨찾기 관련 메소드
	public ArrayList<Dinner> memberLikeList(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> likeList = dao.memberLikeList(conn, memberNo);
		System.out.println("LikeList" + likeList);
		JDBCTemplate.close(conn);
		return likeList;
	}

	// admin(경래) - 회원 별명 검색
	public List<Member> searchMembersByNick(String memberNick) {
		Connection conn = JDBCTemplate.getConnection();
	    List<Member> members = dao.searchMembersByNick(conn, memberNick);
	    JDBCTemplate.close(conn);
	    return members;
	}

}
