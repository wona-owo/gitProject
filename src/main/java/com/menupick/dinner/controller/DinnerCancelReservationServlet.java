/**
 * AJAX with : dinnerRservation.jsp
 * 
 * 취소 버튼을 누른 예약건에서 bookNo 를 가져와서 DB 에서 삭제
 * 그리고 새로고침
 * 
 * @author 김찬희
 */
package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;

/**
 * Servlet implementation class DinnerCancelReservationServlet
 */
@WebServlet("/dinner/cancelReservation")
public class DinnerCancelReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerCancelReservationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookNo = request.getParameter("bookNo");
		
		DinnerService service = new DinnerService();

		response.getWriter().print(service.dinnerCancelReservaion(bookNo));
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
