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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DinnerService service = new DinnerService();
		String action = request.getParameter("action");
		
		List<Dinner> dinners = null;
		
		    if ("sortByName".equals(action)) {
		        String order = request.getParameter("order");
		        dinners = service.getDinnersSortedByName(order);
		    } else if ("filterByApproval".equals(action)) {
		        String approved = request.getParameter("approved");
		        dinners = service.getDinnersByApproval(approved);
		    } else {
		        // 기본 처리
		        int page = 1;
		        int pageSize = 8;
		        if (request.getParameter("page") != null) {
		            page = Integer.parseInt(request.getParameter("page"));
		        }
		        dinners = service.getDinners(page, pageSize);
		    }

		    request.setAttribute("dinners", dinners);
		    request.setAttribute("currentPage", 1); // 현재 페이지 설정
		    request.setAttribute("totalPages", 1); // 총 페이지 설정
		    
		    request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerManage.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
