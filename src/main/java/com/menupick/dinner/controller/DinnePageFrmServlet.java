package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class DinnepageFrmServlet
 */
@WebServlet("/dinner/dinnerPageFrm")
public class DinnePageFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnePageFrmServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 매장 번호 파라미터 받기
		String dinnerNo = request.getParameter("dinnerNo");

		// 2. 매장 번호가 없으면 에러 페이지로 리다이렉트
		if (dinnerNo == null || dinnerNo.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			return;
		}

		// 3. DinnerService를 통해 매장 정보 가져오기
		DinnerService dinnerService = new DinnerService();
		Dinner dinner = dinnerService.getDinnerByNo(dinnerNo);

		// 4. 매장 정보가 없으면 에러 페이지로 리다이렉트
		if (dinner == null) {
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			return;
		}

		// 5. 가져온 매장 정보를 request에 설정
		request.setAttribute("dinner", dinner);

		// 6. JSP로 포워딩
		request.getRequestDispatcher("/WEB-INF/views/common/dinnerDetail.jsp").forward(request, response);
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
