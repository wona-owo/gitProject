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
import com.menupick.dinner.vo.Food;

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
		String dinnerAddr = request.getParameter("dinner_addr");

		// food 값
		String foodNo = request.getParameter("food_no");

		// 3. 로직
		DinnerService service = new DinnerService();
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();
		ArrayList<Food> foodList = new ArrayList<Food>();
		dinnerList = service.likeDinner(dinnerNo, dinnerName);
		foodList = service.filterNation(foodNo);

		// 4. 값 추출
		request.setAttribute("dinnerList", dinnerList);
		request.setAttribute("foodList", foodList);
		request.getRequestDispatcher("/WEB-INF/views/dinner/like.jsp").forward(request, response);
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
