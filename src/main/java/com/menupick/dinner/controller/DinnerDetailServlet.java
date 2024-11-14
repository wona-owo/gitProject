package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;



/**
 * Servlet implementation class DinnerDetailServlet
 */
@WebServlet("/dinner/dinnerDetail")
public class DinnerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinner_no");
		String foodNo = request.getParameter("food_no");
		String memberNo = request.getParameter("member_no");
		
		System.out.println(dinnerNo);
		System.out.println(foodNo);
		System.out.println(memberNo);

		DinnerService service = new DinnerService();
		MemberService mservice = new MemberService();
		Dinner dinner = service.dinnerDetail(dinnerNo);
		Member member = mservice.getMemberNo(memberNo);
		
		request.setAttribute("dinner", dinner);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/common/dinnerDetail.jsp").forward(request, response);
		

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
