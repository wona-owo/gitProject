package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class DinnerSettingServletFrm
 */
@WebServlet("/dinner/setting")
public class DinnerSettingServletFrm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DinnerSettingServletFrm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 // 세션에서 로그인된 식당 계정 정보를 가져옴
	        Dinner loginDinner = (Dinner) session.getAttribute("loginMember");

	        if (loginDinner == null) {
	            // 로그인되지 않았으면 로그인 페이지로 이동
	            response.sendRedirect("/member/loginFrm");
	            return;
	        }

	        // 로그인 정보를 JSP로 전달
	        request.setAttribute("dinner", loginDinner);

	        // JSP 페이지로 포워드
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dinner/dinnerSetting.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
