package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;

/**
 * Servlet implementation class AdminDinnerDeleteServlet
 */
@WebServlet("/dinner/deletefrm")
public class DinnerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DinnerDeleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 파라미터 추출
		String dinnerNo = request.getParameter("dinnerNo");

		if (dinnerNo == null || dinnerNo.isEmpty()) {
			request.setAttribute("title", "오류");
			request.setAttribute("msg", "매장 번호가 유효하지 않습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}

		// 2. 비즈니스 로직 실행
		DinnerService service = new DinnerService();
		int result = service.deleteDinner(dinnerNo);

		// 3. 결과 처리
		if (result > 0) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장 회원 삭제가 완료되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/");
		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장 회원 삭제 중 오류가 발생했습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
		}

		// 4. 결과 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}