package com.menupick.review.model.service;

import java.sql.Connection;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.review.model.dao.ReviewDao;
import com.menupick.review.model.vo.Review;

public class ReviewService {
	ReviewDao dao;
	
	public ReviewService() {
		dao = new ReviewDao();
	}

	public List<Review> getReviewsByMemberNo(String memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		List<Review> reviews = dao.getReviewsByMemberNo(conn, memberNo);
		
		JDBCTemplate.close(conn);
		return reviews;
	}
	

	
	
}

