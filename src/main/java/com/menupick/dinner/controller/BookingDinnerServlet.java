package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingDinnerServlet
 */
@WebServlet("/dinner/bookDinner")
public class BookingDinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingDinnerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberNo = request.getParameter("member_no");
		String dinnerNo = request.getParameter("dinner_no");
		String bookDate = request.getParameter("book_date");
		String bookTime = request.getParameter("book_time");
		String bookCnt = request.getParameter("book_cnt");
		
		System.out.println("from BookingDinnerServlet memberNo : " + memberNo);
		System.out.println("from BookingDinnerServlet dinnerNo : " + dinnerNo);
		System.out.println("from BookingDinnerServlet bookDate : " + bookDate);
		System.out.println("from BookingDinnerServlet bookTime : " + bookTime);
		System.out.println("from BookingDinnerServlet bookCnt : " + bookCnt);

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
