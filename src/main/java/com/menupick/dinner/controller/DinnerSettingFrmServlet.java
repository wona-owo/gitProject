package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class DinnerSettingServletFrm
 */
@WebServlet("/dinner/settingFrm")
public class DinnerSettingFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerSettingFrmServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Dinner loginDinner = (Dinner) session.getAttribute("loginMember");

		if (loginDinner == null) {
			response.sendRedirect("/member/loginFrm");
			return;
		}
		
		request.setAttribute("dinner", loginDinner);
		request.setAttribute("photoPath", new DinnerService().dinnerPhotoPath(loginDinner.getDinnerNo()));
		request.getRequestDispatcher("/WEB-INF/views/dinner/dinnerSetting.jsp").forward(request, response);
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
