package com.menupick.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewRomoveAllServlet
 */
@WebServlet("/reviewRemoveAll")
public class ReviewRomoveAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewRomoveAllServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 상세페이지 선택한 리뷰 삭제
		String reviewNoArr = request.getParameter("reviewNoArr");

		System.out.println("reviewNoArr : " + reviewNoArr);

		ReviewService service = new ReviewService();
		int result = service.reviewRemoveAll(reviewNoArr);

		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
