package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRemoveServlet
 */
@WebServlet("/admin/memberRemove")
public class AdminMemberRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMemberRemoveServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberNo = request.getParameter("memberNo");

		MemberService service = new MemberService();
		int result = service.selectRemove(memberNo);

		if (result > 0) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "회원 탈퇴가 완료 되었습니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/admin/member");

		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "회원 탈퇴 중 오류가 발생하였습니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/admin/member");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
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
