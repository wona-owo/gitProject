package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Book;

/**
 * Servlet implementation class DinnerCalendarReservaitionServlet
 */
@WebServlet("/dinner/reservation")
public class DinnerCalendarReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerCalendarReservationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinnerNo");
		String displayMonth = request.getParameter("displayMonth");
		String justMonth = displayMonth.substring(0, displayMonth.length() - 1);
		String displayYear = request.getParameter("displayYear");

		DinnerService service = new DinnerService();
		ArrayList<Book> bookList = service.checkReservation(dinnerNo, justMonth, displayYear);

		Map<String, Integer> dateCountMap = new HashMap<>();

		for (Book book : bookList) {
			String justDate = book.getBookDate().substring(8, 10);
			dateCountMap.put(justDate, dateCountMap.getOrDefault(justDate, 0) + 1);
		}

		Gson gson = new Gson();
		String jsonStr = gson.toJson(dateCountMap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonStr);
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
