/**
 * From : dinnerCanlendar.jsp
 * To : dinnerReservation.jsp
 * 
 * 달력에서 예약이 있는 날짜를 클릭하면 보여지고 있는 연, 월, 일 정보를 가지고 
 * 해당 날에 있는 예약 정보를 가져온다
 * 
 * @author 김찬희
 */
package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.BookInfo;

/**
 * Servlet implementation class DinnerCheckReservationServlet
 */
@WebServlet("/dinner/checkReservation")
public class DinnerCheckReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerCheckReservationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dinnerNo = request.getParameter("dinnerNo");

		String year = request.getParameter("year");
		String justYear = year.substring(2);
		
		String ajaxRequest =  request.getParameter("check");
		
		System.out.println("from DinnerCheckReservationServlet ajaxRequest : " + ajaxRequest);

		// Parse the month as an integer, add 1, and format it as a 2-digit string
		int month = Integer.parseInt(request.getParameter("month")) + 1;
		String monthPadded = String.format("%02d", month);

		// Parse the day as an integer and format it as a 2-digit string
		int day = Integer.parseInt(request.getParameter("day"));
		String dayPadded = String.format("%02d", day);
		String date = (justYear + "/" + monthPadded + "/" + dayPadded);

		DinnerService service = new DinnerService();
		ArrayList<BookInfo> bookInfoList = service.getReservationData(dinnerNo, date);

		request.setAttribute("bookInfo", bookInfoList);
		request.setAttribute("bookYear", year);
		request.setAttribute("bookMonth", month);
		request.setAttribute("bookDay", day);
 		request.getRequestDispatcher("/WEB-INF/views/dinner/dinnerReservation.jsp").forward(request, response);
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
