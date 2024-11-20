package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class MemberReservationFrm
 */
@WebServlet("/member/reservationFrm")
public class MemberReservationFrm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberReservationFrm() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinnerNo");
		
		DinnerService service = new DinnerService();
		Dinner dinner = service.getDinnerNo(dinnerNo);
		request.setAttribute("dinner", dinner);
		
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberReservation.jsp").forward(request, response);
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
