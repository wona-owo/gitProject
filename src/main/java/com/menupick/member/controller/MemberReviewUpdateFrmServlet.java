package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberReviewUpdateServlet
 */
@WebServlet("/member/reviewUpdate")
public class MemberReviewUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberReviewUpdateFrmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 리뷰 번호를 요청에서 받아옴
		String reviewNo = request.getParameter("reviewNo");

		// 리뷰 번호가 null일 경우 처리
		if (reviewNo == null || reviewNo.isEmpty()) {
			request.setAttribute("message", "리뷰 번호가 유효하지 않습니다.");
		}

		// 리뷰 번호를 JSP 페이지에 전달
		request.setAttribute("reviewNo", reviewNo);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/reviewUpdate.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
