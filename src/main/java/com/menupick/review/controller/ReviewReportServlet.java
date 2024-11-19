package com.menupick.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.review.model.service.ReviewService;
import com.menupick.review.model.vo.Recommend;

/**
 * Servlet implementation class ReviewReportServlet
 */
@WebServlet("/review/report")
public class ReviewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewNo = request.getParameter("review_no");
		String memberNo = request.getParameter("member_no");
		
		if(reviewNo != null && memberNo != null) {
			Recommend recommend = new Recommend(reviewNo, memberNo, "y");
			ReviewService service = new ReviewService();
			
			boolean isReported = service.reportReview(recommend);
			
	    } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
