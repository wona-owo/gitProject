package com.menupick.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchFrmServlet
 */
@WebServlet("/search")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberService service;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
        service = new MemberService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// 검색어 파라미터 가져오기
        String query = request.getParameter("query");
        	
        // 검색어 비즈니스 로직
        ArrayList<Dinner> dinnerList = service.searchDinner(query);
        System.out.println("검색 디버깅 테스트: " + dinnerList);
        
        // 값 전달
        request.setAttribute("dinnerList",dinnerList);
        request.setAttribute("query", query);

        // search.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dinner/search.jsp");
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
