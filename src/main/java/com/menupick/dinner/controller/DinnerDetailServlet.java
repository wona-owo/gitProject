package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Food;

/**
 * Servlet implementation class DinnerDetailServlet
 */
@WebServlet("/dinner/detail")
public class DinnerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DinnerDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinner_no");
		String dinnerName = request.getParameter("dinner_name");
		
		String foodNo = request.getParameter("food_no");
		
		DinnerService service = new DinnerService();
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();
		dinnerList = service.likeDinner(dinnerNo, dinnerName);
		ArrayList<Food> foodList = new ArrayList<Food>();
		foodList = service.filterNation(foodNo);
		
		request.setAttribute("dinnerList", dinnerList);
		request.setAttribute("foodList", foodList);
		//request.getRequestDispatcher();
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
