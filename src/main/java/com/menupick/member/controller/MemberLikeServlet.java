package com.menupick.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLikeServlet
 */
@WebServlet("/member/like")
public class MemberLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLikeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩

		// 2. 값 추출 - 회원 번호를 받아와서 쿼리에 전달.
		String memberNo = request.getParameter("memberNo");

		System.out.println(memberNo);
		// 3. 비즈니스 로직 - 회원 번호와 일치하는 식당 정보 list 추출
		MemberService service = new MemberService();
		ArrayList<Dinner> likeList = service.memberLikeList(memberNo);

		// 4. 결과처리
		request.setAttribute("likeList", likeList);
		request.getRequestDispatcher("/WEB-INF/views/checkLike.jsp").forward(request, response);
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
