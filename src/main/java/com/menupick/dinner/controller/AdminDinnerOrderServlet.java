package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class AdminDinnerOrderServlet
 */
@WebServlet("/AdminDinnerOrderServlet")
public class AdminDinnerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDinnerOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DinnerService service = new DinnerService();
		String action = request.getParameter("action");
		
		List<Dinner> dinners = null;
		
		   
		    request.setAttribute("dinners", dinners);
		    request.setAttribute("currentPage", 1); // 현재 페이지 설정
		    request.setAttribute("totalPages", 1); // 총 페이지 설정
		    
		    request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerManage.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
