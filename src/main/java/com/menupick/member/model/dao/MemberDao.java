package com.menupick.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.vo.Member;
import com.menupick.review.model.vo.Review;

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

	// admin(경래) - 회원 탈퇴
	public int selectRemove(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from tbl_member where member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// admin(경래) - 회원 조회
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

		String query = "insert into tbl_member (member_no, member_id, member_pw, member_name, member_nick, member_phone, member_addr, member_gender, member_email, enroll_date, adult_confirm, member_level) "
				+ "values ('m' || to_char(sysdate, 'yymmdd') || lpad(seq_member.nextval, 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 'n', 2)";
		// String query = "insert into tbl_member values (to_char(seq_member.nextval),
		// ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 'N', 2)";

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
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 아이디 중복 체크
	public int idDuplChk(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from tbl_member where member_id = ?";
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cnt;
	}

	// admin(경래) - 선택한 회원 등급 변경
	public int updChgLevel(Connection conn, String memberNo, String memberLevel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TBL_MEMBER SET MEMBER_LEVEL = ? WHERE MEMBER_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberLevel);
			pstmt.setString(2, memberNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// admin(경래) - 선택한 회원 전체 삭제
	public int memberRemoveAll(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from tbl_member where member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// admin(경래) - 회원 조회 + 페이징 넘버
	public List<Member> getMembers(Connection conn, int page, int pageSize) {
		PreparedStatement pstmt = null;
		List<Member> members = new ArrayList<>();
		ResultSet rset = null;

		// Oracle에서의 페이징 쿼리
		String query = "SELECT * FROM (" + "    SELECT a.*, ROWNUM AS rnum FROM ("
				+ "        SELECT * FROM tbl_member ORDER BY member_no" + "    ) a " + "    WHERE ROWNUM <= ?" + ") "
				+ "WHERE rnum > ? and member_level = 2";

		try {
			pstmt = conn.prepareStatement(query);

			// 페이지 끝과 시작을 설정
			int endRow = page * pageSize; // 페이지의 마지막 레코드 위치
			int startRow = (page - 1) * pageSize; // 페이지의 시작 레코드 위치

			pstmt.setInt(1, endRow); // 마지막 레코드 위치
			pstmt.setInt(2, startRow); // 시작 레코드 위치

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
				members.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}

	// admin(경래) - 페이징 넘버
	public int getTotalMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from tbl_member";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return rset.getInt(1);
			}
		} catch (SQLException e) {
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

			if (rset.next()) {
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cnt;
	}

	// 마이페이지 즐겨찾기 관련 메소드

	// 즐겨찾기
	// DB list 불러오기
	public ArrayList<Dinner> memberLikeList(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ArrayList<Dinner> likeList = new ArrayList<>();
		ResultSet rset = null;

		String query = "Select * From tbl_dinner D left join tbl_like L on (d.dinner_no= l.dinner_no) where l.member_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();

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
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return likeList;
	}

	// 즐겨찾기 삭제
	public int memberDelLike(Connection conn, String dinnerNo, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "delete from tbl_like where member_no = ? and dinner_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, dinnerNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 리뷰 조회
	public ArrayList<Review> memberRevList(Connection conn, String memberNo) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> revList = new ArrayList<Review>();

		String query = "select * from tbl_review R join tbl_dinner D on (R.dinner_no = D.dinner_no) where member_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getString("review_no"));
				r.setDinnerNo(rset.getString("dinner_no"));
				r.setMemberNo(rset.getString("member_no"));
				r.setReviewContent(rset.getString("review_con"));
				r.setReviewImage(rset.getBytes("review_img"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setDinnerName(rset.getString("dinner_name"));

				revList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return revList;
	}

	// 예약 확인
	public ArrayList<Book> memberBookList(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		String query = "select * from tbl_book R join tbl_dinner D on (R.dinner_no = D.dinner_no) where member_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book b = new Book();

				b.setBookNo(rset.getString("book_no"));
				b.setDinnerNo(rset.getString("dinner_no"));
				b.setMemberNo(rset.getString("member_no"));
				b.setBookDate(rset.getString("book_date"));
				b.setBookTime(rset.getString("book_time"));
				b.setBookCnt(rset.getInt("book_cnt"));
				b.setDinnerName(rset.getString("dinner_name"));

				bookList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return bookList;
	}

	// admin(경래) - 회원 별명 검색
	public List<Member> searchMembersByNick(Connection conn, String memberNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<>();

		String query = "SELECT * FROM tbl_member WHERE member_nick LIKE ? ORDER BY member_no";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + memberNick + "%");
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
				members.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}

	public int deleteMember(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from tbl_member where member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int bookingMember(Connection conn, Book book) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "insert into tbl_book values ( 'b' || to_char(sysdate, 'yymmdd') || lpad(seq_book.nextval, 4, '0'), ?, ?, to_date (?, 'yyyy/mm/dd'), ?, ?)";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, book.getDinnerNo());
			pstmt.setString(2, book.getMemberNo());
			pstmt.setString(3, book.getBookDate());
			pstmt.setString(4, book.getBookTime());
			pstmt.setInt(5, book.getBookCnt());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection conn, Member updMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_member set member_id = ? , member_pw = ? , member_name = ? , member_nick = ? , member_phone = ? , member_addr = ? , member_email = ? where member_no = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updMember.getMemberId());
			pstmt.setString(2, updMember.getMemberPw());
			pstmt.setString(3, updMember.getMemberName());
			pstmt.setString(4, updMember.getMemberNick());
			pstmt.setString(5, updMember.getMemberPhone());
			pstmt.setString(6, updMember.getMemberAddr());
			pstmt.setString(7, updMember.getMemberEmail());
			pstmt.setString(8, updMember.getMemberNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public boolean checkPassword(Connection conn, String memberNo, String memberPw) {
		boolean isMatch = false;
		String sql = "select member_pw from tbl_member where member_no = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPassword = rs.getString("member_pw");
				isMatch = dbPassword.equals(memberPw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isMatch;
	}

	public String searchMemberId(Connection conn, String memberName, String memberPhone) throws SQLException {
		String sql = "SELECT member_Id FROM tbl_member WHERE member_name = ? AND member_phone = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("member_id"); // 아이디 반환
				}
			}
		}
		return null; // 일치하는 결과가 없을 경우
	}

	// 식당 검색
	public ArrayList<Dinner> searchDinner(Connection conn, String srchQuery) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> srchList = new ArrayList<Dinner>();

		String sql = "select d.dinner_name, d.dinner_addr, f.food_nation, f.food_cat from (tbl_dinner d join tbl_menu m on (d.dinner_no = m.dinner_no)) join tbl_food f on (m.food_no = f.food_no) where d.dinner_name Like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + srchQuery + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setFoodNation(rset.getString("food_nation"));
				d.setFoodCat(rset.getString("food_cat"));
				srchList.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return srchList;
	}

	public String getEmailByMemberId(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		String sql = "SELECT member_email FROM tbl_member WHERE member_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("member_email");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 좋아요 추가 -> 성공시 true 반환:
	public boolean memberAddLike(Connection conn, String dinnerNo, String memberNo) {
		PreparedStatement pstmt = null;
		String query = "insert into tbl_like values (?, ?)";
		boolean addLike = false;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			pstmt.setString(2, memberNo);

			result = pstmt.executeUpdate();

			if (result > 0) {
				addLike = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return addLike;
	}

	// count 값이 1 이상일 경우에 true 반환(즐겨찾기 일치 식당이 있는지 서치)
	public boolean memberFindLike(Connection conn, String dinnerNo, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean findLike = false;

		String query = "select count(*) from tbl_like where member_no =? and dinner_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, dinnerNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int count = rset.getInt(1); // 첫번째 컬럼값 가져오기
				findLike = count > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return findLike;
	}

	public Member searchMemberPw(Connection conn, String memberId, String memberPhone) {
		Member member = null;
		String sql = "SELECT * FROM tbl_member WHERE member_id = ? AND member_phone = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPhone);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					member = new Member();
					member.setMemberId(rs.getString("member_id"));
					member.setMemberPhone(rs.getString("member_phone"));
					member.setMemberEmail(rs.getString("member_email"));
					member.setMemberPw(rs.getString("member_pw"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public boolean updateMemberPassword(Connection conn, String memberId, String tempPassword) {
		String sql = "UPDATE tbl_member SET member_pw = ? WHERE member_id = ?";
		boolean isUpdated = false;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, tempPassword);
			pstmt.setString(2, memberId);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	// 예약 취소 - 마이페이지
	public int memberDelBook(Connection conn, String bookNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "delete from tbl_book where book_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public List<String> getReservedTimes(Connection conn, String dinnerNo, String bookDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> reservedTimes = new ArrayList<>();
		String query = "SELECT BOOK_TIME FROM TBL_BOOK WHERE DINNER_NO = ? AND BOOK_DATE = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			pstmt.setString(2, bookDate);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				reservedTimes.add(rset.getString("BOOK_TIME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return reservedTimes;
	}

	// 리뷰삭제 - 마이페이지
	public int memberDelReview(Connection conn, String reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "delete from tbl_review where review_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reviewNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int memberUpdateReview(Connection conn, String reviewNo, String reviewCon) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE tbl_review SET review_con = ? WHERE review_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reviewCon);
			pstmt.setString(2, reviewNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;

	}

	// 식당 상세페이지 멤버값 가져오기(경래)
	public Member memberDetail(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = new Member();
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

	public List<Member> getReviewsBymemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<>();

		String query = "SELECT * FROM tbl_member WHERE member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
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

				members.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}
}
