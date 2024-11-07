package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println(bookList);

		int foo = 0;
		response.getWriter().print(foo);
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
