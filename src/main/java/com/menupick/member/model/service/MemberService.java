package com.menupick.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.dao.MemberDao;
import com.menupick.member.model.vo.Member;
import com.menupick.member.util.EmailUtil;
import com.menupick.review.model.vo.Review;

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

	// 마이페이지 즐겨찾기 관련 메소드

	// 즐겨찾기 DB list 불러오기
	public ArrayList<Dinner> memberLikeList(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> likeList = dao.memberLikeList(conn, memberNo);
		JDBCTemplate.close(conn);
		return likeList;
	}

	// 즐겨찾기 삭제
	public int memberDelLike(String dinnerNo, String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.memberDelLike(conn, dinnerNo, memberNo);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 리뷰 DB 불러오기
	public ArrayList<Review> memberRevList(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> reviewList = dao.memberRevList(conn, memberNo);
		JDBCTemplate.close(conn);
		return reviewList;
	}

	// 예약 DB 불러오기
	public ArrayList<Book> memberBookList(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bookList = dao.memberBookList(conn, memberNo);
		JDBCTemplate.close(conn);
		return bookList;
	}

	// admin(경래) - 회원 별명 검색
	public List<Member> searchMembersByNick(String memberNick) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> members = dao.searchMembersByNick(conn, memberNick);
		JDBCTemplate.close(conn);
		return members;
	}

	public int deleteMember (String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, memberNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int bookingMember(Book book) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.bookingMember(conn, book);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateMember(Member updMember) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, updMember);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	public boolean checkPassword(String memberNo, String memberPw) {
		Connection conn = JDBCTemplate.getConnection();
	    boolean result = dao.checkPassword(conn, memberNo, memberPw);
	    JDBCTemplate.close(conn);
	    return result;
	    }
	
	//식당 검색
	public ArrayList<Dinner> searchDinner(String srchQuery) {
		Connection conn = JDBCTemplate.getConnection();
	    ArrayList<Dinner> dinnerList= dao.searchDinner(conn, srchQuery);
	    JDBCTemplate.close(conn);
	    return dinnerList;
	}

	// 회원 정보 조회 (회원 아이디, 전화번호로 비밀번호 찾기)
	public Member searchMemberPw(String memberId, String memberPhone) {
        Connection conn = JDBCTemplate.getConnection(); 
        Member member = dao.searchMemberPw(conn, memberId, memberPhone);
        JDBCTemplate.close(conn); 
        return member;
    }

    // 비밀번호 업데이트
    public boolean updateMemberPassword(String memberId, String tempPassword) {
        Connection conn = JDBCTemplate.getConnection(); 
        boolean isUpdated = dao.updateMemberPassword(conn, memberId, tempPassword);
        if (isUpdated) {
            JDBCTemplate.commit(conn); 
        } else {
            JDBCTemplate.rollback(conn);
        }
        JDBCTemplate.close(conn); 
        return isUpdated;
    }

    // 임시 비밀번호를 이메일로 전송
    public boolean sendPwByEmail(String recipientEmail, String tempPassword) {
        try {
            String subject = "비밀번호 찾기 결과";
            String message = "회원님의 임시 비밀번호는 다음과 같습니다: " + tempPassword + " 로그인 후 비밀번호를 변경해주세요";
            EmailUtil.sendEmail(recipientEmail, subject, message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 ID 조회
    public String searchMemberId(String memberName, String memberPhone) {
	    Connection conn = JDBCTemplate.getConnection();
	    String result = null;
	    try {
	        result = dao.searchMemberId(conn, memberName, memberPhone);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null && !conn.isClosed()) {
	                conn.close(); // Connection 자원 반환
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result; // 조회된 결과를 반환
	}

	public boolean memberAddLike(String dinnerNo, String memberNo) {
		Connection conn = JDBCTemplate.getConnection();		
		boolean likeDinner = false;		
		likeDinner = dao.memberAddLike(conn, dinnerNo, memberNo);
		JDBCTemplate.close(conn);
		return likeDinner;	
	}

	public boolean memberFindLike(String dinnerNo, String memberNo) {
		Connection conn = JDBCTemplate.getConnection();		
		boolean findLike = false;		
		findLike = dao.memberFindLike(conn, dinnerNo, memberNo);
		JDBCTemplate.close(conn);
		return findLike;	
		}
    }

 
