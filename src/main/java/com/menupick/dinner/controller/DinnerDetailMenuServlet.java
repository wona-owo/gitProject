package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.MenuDTO;

/**
 * Servlet implementation class DinnerDetailMenuServlet
 */
@WebServlet("/menuData")
public class DinnerDetailMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinnerNo");

		if (dinnerNo == null || dinnerNo.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Invalid dinnerNo");
			return;
		}

		// 메뉴 데이터 조회
		DinnerService service = new DinnerService();
		List<MenuDTO> menuList = service.getMenuDetailsByDinnerNo(dinnerNo);

		// JSON 응답 반환 (인코딩 명시)
		response.setContentType("application/json; charset=UTF-8"); // 인코딩 설정
		response.setCharacterEncoding("UTF-8"); // 명시적으로 UTF-8 지정
		new Gson().toJson(menuList, response.getWriter());
	}
}
