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
		int result = service.dinnerCancelReservaion(bookNo);
		
		if (result != 0) {

		} else {

		}

		/*
		 * 취소할건지 메세지를 보여주고 새로고침
		 */

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
