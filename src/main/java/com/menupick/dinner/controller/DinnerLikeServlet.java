package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;


/**
 * Servlet implementation class DinnerLike
 */
@WebServlet("/dinner/likeFrm")
public class DinnerLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerLikeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 값 추출
		// dinner 값
		String dinnerNo = request.getParameter("dinner_no");
		String dinnerName = request.getParameter("dinner_name");
		// food 값
		String foodNo = request.getParameter("food_no");
		
		System.out.println("from DinnerLikeServlet");
		System.out.println(dinnerNo);
		System.out.println(dinnerName);
		System.out.println(foodNo);
		
		// 3. 로직
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();
		DinnerService service = new DinnerService();
		dinnerList = service.likeDinner(dinnerNo, dinnerName);
		
		// 4. 결과 처리
		request.setAttribute("dinnerList", dinnerList);
		request.getRequestDispatcher("/WEB-INF/views/dinner/search.jsp").forward(request, response);
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
