package com.menupick.review.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.StringTokenizer;

import com.menupick.common.JDBCTemplate;
import com.menupick.review.model.dao.ReviewDao;
import com.menupick.review.model.vo.Review;

public class ReviewService {
	ReviewDao dao;

	public ReviewService() {
		dao = new ReviewDao();
	}

	// admin(경래) 리뷰 조회 + 정렬 (식당이름 조인시킴)
	public List<Review> getReviewsByMemberNo(String memberNo, String sortOption) {
		Connection conn = JDBCTemplate.getConnection();

		// 정렬 기준 설정
		String orderBy;
		switch (sortOption) {
		case "latest":
			orderBy = "review_date DESC"; // 최신순
			break;
		case "oldest":
			orderBy = "review_date ASC"; // 오래된순
			break;
		case "report":
			orderBy = "report_count DESC"; // 신고순
			break;
		default:
			orderBy = "review_date DESC"; // 기본값: 최신순
		}

		List<Review> reviews = dao.getReviewsByMemberNo(conn, memberNo, orderBy);

		JDBCTemplate.close(conn);
		return reviews;
	}

	// admin(경래) 리뷰 선택 삭제
	public int reviewRemoveAll(String reviewNoArr) {
		Connection conn = JDBCTemplate.getConnection();

		StringTokenizer st = new StringTokenizer(reviewNoArr, "/");

		boolean resultChk = true;

		while (st.hasMoreTokens()) {
			String reivewNo = st.nextToken();

			int result = dao.reviewRemoveAll(conn, reivewNo);

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
}
