package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;


/**
 * Servlet implementation class AdminMemberDetailServlet
 */
@WebServlet("/admin/dinnerDetail")
public class AdminDinnerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDinnerDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩

		// 2. 값 추출
		String dinnerNo = request.getParameter("dinnerNo");

		// 3. 로직
		//회원 정보 가져오기
		DinnerService service = new DinnerService();
		Dinner dinner = service.getDinnerNo(dinnerNo);

		
		
		//4. 결과 처리
		request.setAttribute("dinner", dinner);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerDetail.jsp").forward(request, response);

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
