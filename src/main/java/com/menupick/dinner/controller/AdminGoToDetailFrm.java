package com.menupick.dinner.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.DinnerFoodDetail;


/**
 * Servlet implementation class AdminGoToDetailFrm
 */
@WebServlet("/admin/goToDetailFrm")
public class AdminGoToDetailFrm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGoToDetailFrm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		String dinnerNo = request.getParameter("dinnerNo");

        DinnerService dinnerService = new DinnerService();
        DinnerFoodDetail dinnerDetail = dinnerService.getDinnerDetailByNo(dinnerNo);

        // DinnerDetail 객체를 JSP에 전달
        request.setAttribute("dinnerDetail", dinnerDetail);
        request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerPage.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
