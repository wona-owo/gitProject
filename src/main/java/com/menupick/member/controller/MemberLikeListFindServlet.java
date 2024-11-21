package com.menupick.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class MemberLikeListFindServlet
 */
@WebServlet("/member/findLike")
public class MemberLikeListFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLikeListFindServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 값 추출 -> 클릭한 식당 코드
		String dinnerNo = request.getParameter("dinnerNo");

		HttpSession session = request.getSession(false);

		// 로그인 상태 확인
		if (session == null || session.getAttribute("loginMember") == null) {
			// 로그인이 안된 경우 기본 응답 처리
			response.setContentType("application/json");
			response.getWriter().print("{\"isFavorited\": false, \"message\": \"로그인이 필요합니다.\"}");
			return;
		}

		// 로그인 상태라면 memberNo 추출
		Member member = (Member) session.getAttribute("loginMember");
		String memberNo = member.getMemberNo();
		System.out.println(memberNo);
		System.out.println(dinnerNo);

		// 비즈니스 로직 -> DB에서 찾기 (성공 여부에 따라 참/거짓 반환)
		MemberService service = new MemberService();
		boolean findLike = service.memberFindLike(dinnerNo, memberNo);

		// 결과처리 : 있는지 없는지 boolean 데이터 전달.
		response.setContentType("application/json");
		if (findLike) {
			response.getWriter().print("{\"isFavorited\": true}");
		} else {
			response.getWriter().print("{\"isFavorited\": false}");
		}
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
