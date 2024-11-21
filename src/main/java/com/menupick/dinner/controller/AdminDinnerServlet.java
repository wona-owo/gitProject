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
 * Servlet implementation class AdminDinnerDetailServlet
 */
@WebServlet("/admin/dinner")
public class AdminDinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDinnerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DinnerService service = new DinnerService();
		String action = request.getParameter("action");

		if ("search".equals(action)) {
			// 검색 처리
			String dinnerName = request.getParameter("dinnerName");
			List<Dinner> dinners = service.searchDinnerByName(dinnerName);

			request.setAttribute("dinners", dinners);
			request.setAttribute("currentPage", 1); // 검색 시 페이지 번호 고정
			request.setAttribute("totalPages", 1); // 검색 결과는 한 페이지로 처리

			request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerManage.jsp").forward(request, response);
		} else {
			// 기존 회원 목록 처리
			int page = 1;
			int pageSize = 8;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			List<Dinner> dinners = service.getDinners(page, pageSize);
			int totalDinners = service.getTotalDinnerCount();
			int totalPages = (int) Math.ceil((double) totalDinners / pageSize);

			request.setAttribute("dinners", dinners);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);

			request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerManage.jsp").forward(request, response);
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
