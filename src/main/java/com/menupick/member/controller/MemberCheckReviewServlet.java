package com.menupick.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;
import com.menupick.review.model.vo.Review;;

/**
 * Servlet implementation class MemberCheckReviewServlet
 */
@WebServlet("/member/ckReview")
public class MemberCheckReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberCheckReviewServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 값 추출 - 회원 번호를 받아와서 쿼리에 전달
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("loginMember");
		String memberNo = member.getMemberNo();

		// 비즈니스 로직 - 회원 번호와 일치하는 리뷰 list 추출
		MemberService service = new MemberService();
		ArrayList<Review> reviewList = service.memberRevList(memberNo);

		// 결과처리
		request.setAttribute("reviewList", reviewList);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/checkReview.jsp");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
