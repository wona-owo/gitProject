package com.menupick.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.review.model.vo.Review;

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
			// TODO Auto-generated catch block
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
}
