package com.menupick.review.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.StringTokenizer;

import com.menupick.common.JDBCTemplate;
import com.menupick.review.model.dao.ReviewDao;
import com.menupick.review.model.vo.MemberReport;
import com.menupick.review.model.vo.Recommend;
import com.menupick.review.model.vo.Review;
import com.menupick.review.model.vo.ReviewReport;

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
	
	
	// 식당 상세페이지(경래) 리뷰 조회 + 정렬 (식당이름 조인시킴)
		public List<Review> getReviewsBydinnerNo(String dinnerNo, String sortOption) {
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

			List<Review> reviews = dao.getReviewsBydinnerNo(conn, dinnerNo, orderBy);

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

	// 리뷰신고(경래)
	public boolean reportReview(Recommend recommend) {
		Connection conn = JDBCTemplate.getConnection();
		boolean isReported = false;
		
		isReported = dao.isReviewReported(conn, recommend);
		
		if(!isReported) {
			//신고 등록
			int result = dao.addReviewReport(conn, recommend);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
		}
		return !isReported;
	}

	// admin 회원 상세 페이지(회원 신고 당한 횟수 조회)
	public MemberReport getMemberReport(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberReport report = dao.getMemberReport(conn, memberNo);
		if (report == null) {
			report = new MemberReport(memberNo, "N/A", 0); // 기본값 설정
		}
		JDBCTemplate.close(conn);
		return report;
	}

	// admin 회원 상세 페이지(리뷰별 신고 당한 횟수 조회)
	public List<ReviewReport> getReviewReportsByMemberNo(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		List<ReviewReport> reports = dao.getReviewReportsByMemberNo(conn, memberNo);
		JDBCTemplate.close(conn);
		return reports;
	}

	//리뷰 작성(경래)
	public boolean insertReview(String dinnerNo, String memberNo, String content) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertReview(conn, dinnerNo, memberNo, content);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result > 0;
	}
}
