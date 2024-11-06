package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("saveId : " + request.getParameter("saveId")); <- 체크시 chk,
		// 체크 안하는 경우 null

		// 1. 인코딩 -> 필터

		// 2. 값 추출
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		// 3. 비즈니스 로직 -> 로그인
		MemberService service = new MemberService();
		Member loginMember = service.memberLogin(loginId, loginPw);

		// 4. 결과 처리

		if (loginMember != null) {
			// 정상로그인 !

			// 레벨이 1일때 어드민 페이지로 이동하는 기능 넣어야함

			/*
			 * if (loginMember.getMemberLevel() == 1) {
			 * 
			 * request.getRequestDispatcher("/어드민일때 접근 가능한 페이지 주소? 메인?/").forward(request,
			 * response); }
			 */

			HttpSession session = request.getSession(true);
			session.setAttribute("loginMember", loginMember);
			session.setMaxInactiveInterval(600);
			Cookie cookie = new Cookie("saveId", loginId);

			if (request.getParameter("saveId") != null) {
				// 아이디 저장 체크박스를 체크한 경우
				cookie.setMaxAge(60 * 60 * 24 * 30); // 단위 == 초 -- 60 * 60 * 24 * 30 == 30일
			} else {
				// 아이디 저장 체크박스를 체크하지 않은 경우
				cookie.setMaxAge(0); // 유효시간을 0초로 변경하여, 결론적으로 쿠키를 해제한다.
			}

			// 쿠키를 적용시킬 경로
			cookie.setPath("/member/loginFrm");

			response.addCookie(cookie);

			response.sendRedirect("/");

		} else {
			// 로그인 실패
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/loginFrm"); // 다시 로그인 페이지로 이동할 수 있도록

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			view.forward(request, response);
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
