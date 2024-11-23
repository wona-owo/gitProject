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
		String dinnerNo = request.getParameter("dinnerNo");
		String memberNo = request.getParameter("memberNo");

		DinnerService service = new DinnerService();
		MemberService mbService = new MemberService();

		Dinner dinner = service.dinnerDetail(dinnerNo);
		String photoPath = service.dinnerPhotoPath(dinnerNo);

		Member member = mbService.dinnerDetail(memberNo);

		request.setAttribute("dinner", dinner);
		request.setAttribute("member", member);
		request.setAttribute("photoPath", photoPath);
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
