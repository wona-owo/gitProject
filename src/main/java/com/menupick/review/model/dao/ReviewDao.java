package com.menupick.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.review.model.vo.MemberReport;
import com.menupick.review.model.vo.Recommend;
import com.menupick.review.model.vo.Review;
import com.menupick.review.model.vo.ReviewReport;

public class ReviewDao {

	//admin(경래) 리뷰 조회 (식당이름 조인시킴)
	public List<Review> getReviewsByMemberNo(Connection conn, String memberNo, String orderBy) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviews = new ArrayList<>();
		
		String query = "SELECT r.review_no, r.dinner_no, r.member_no, r.review_con, r.review_img, r.review_date, d.dinner_name FROM tbl_review r JOIN tbl_dinner d ON r.dinner_no = d.dinner_no WHERE r.member_no = ? ORDER BY " + orderBy;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review review = new Review();
                review.setReviewNo(rset.getString("review_no"));
                review.setDinnerNo(rset.getString("dinner_no"));
                review.setMemberNo(rset.getString("member_no"));
                review.setReviewContent(rset.getString("review_con"));
                review.setReviewImage(rset.getBytes("review_img"));
                review.setReviewDate(rset.getDate("review_date"));
                review.setDinnerName(rset.getString("dinner_name"));

                reviews.add(review);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return reviews;
	}

	//admin(경래) 리뷰 선택 삭제
	public int reviewRemoveAll(Connection conn, String reviewNo) {
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

	//리뷰신고(경래)
	public boolean isReviewReported(Connection conn, Recommend recommend) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) FROM tbl_recommend WHERE review_no = ? AND member_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, recommend.getReviewNo());
			pstmt.setString(2, recommend.getMemberNo());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				return rset.getInt(1) > 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//리뷰신고(경래)
	public int addReviewReport(Connection conn, Recommend recommend) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO tbl_recommend (review_no, member_no, report) VALUES (?, ?, 'y')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, recommend.getReviewNo());
			pstmt.setString(2, recommend.getMemberNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// admin 회원 상세 페이지(회원 신고 당한 횟수 조회)
	public MemberReport getMemberReport(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberReport report = null;
		String query = "SELECT COUNT(r.review_no) AS total_reports " + "FROM tbl_review r "
				+ "JOIN tbl_recommend rec ON r.review_no = rec.review_no "
				+ "WHERE rec.report = 'y' AND r.member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				report = new MemberReport(rs.getString("member_no"), // memberNo
						rs.getString("member_id"), // memberId
						rs.getInt("total_reports") // totalReports
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return report;
	}

	// admin 회원 상세 페이지(리뷰별 신고 당한 횟수 조회)
	public List<ReviewReport> getReviewReportsByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewReport> list = new ArrayList<>();
		String query = "SELECT r.review_no, COUNT(rec.member_no) AS report_count " + "FROM tbl_review r "
				+ "LEFT JOIN tbl_recommend rec ON r.review_no = rec.review_no AND rec.report = 'y' "
				+ "WHERE r.member_no = ? " + "GROUP BY r.review_no";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ReviewReport(rs.getString("review_no"), // reviewNo
						rs.getString("dinner_no"), // dinnerNo
						rs.getInt("report_count") // reportCount
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// 리뷰 작성(경래)
	public int insertReview(Connection conn, String dinnerNo, String memberNo, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO tbl_review (review_no, member_no, dinner_no, review_con, review_date) "
                + "VALUES ('r' || to_char(sysdate, 'yymmdd') || lpad (seq_review.nextval, 4, '0'), ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, dinnerNo);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
