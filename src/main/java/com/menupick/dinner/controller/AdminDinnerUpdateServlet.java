package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class AdminDinnerUpdateServlet
 */
@WebServlet("/dinner/update")
public class AdminDinnerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDinnerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String dinnerNo = request.getParameter("dinnerNo");
	String dinnerName = request.getParameter("dinnerName");
	String dinnerAddr = request.getParameter("dinnerAddr");
	String dinnerOpen = request.getParameter("dinnerOpen");
	String dinnerClose = request.getParameter("dinnerClose");
	String dinnerPhone = request.getParameter("dinnerPhone");
	String dinnerEmail = request.getParameter("dinnerEmail");
	String dinnerParking = request.getParameter("dinnerParking");
	String busiNo = request.getParameter("busiNo");
	String dinnerMaxPerson = request.getParameter("dinnerMaxPerson");
	String dinnerConfirm = request.getParameter("dinnerConfirm");
	
	//로직 - 정보수정
	
	Dinner updDinner = new Dinner();
	updDinner.setDinnerNo(dinnerNo);
	updDinner.setDinnerNo(dinnerName);
	updDinner.setDinnerNo(dinnerAddr);
	updDinner.setDinnerNo(dinnerOpen);
	updDinner.setDinnerNo(dinnerClose);
	updDinner.setDinnerNo(dinnerPhone);
	updDinner.setDinnerNo(dinnerEmail);
	updDinner.setDinnerNo(dinnerParking);
	updDinner.setDinnerNo(busiNo);
	updDinner.setDinnerNo(dinnerMaxPerson);
	updDinner.setDinnerNo(dinnerConfirm);
	
	DinnerService service = new DinnerService();
	int result = service.updateDinner(updDinner);
	
	
	if (result > 0) {
        // 성공 시 페이지 리로드를 위해 dinnerDetail 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/admin/dinnerDetail");
    } else {
        // 실패 시 오류 메시지 또는 다른 처리 로직 추가 가능
        request.setAttribute("title", "오류");
        request.setAttribute("msg", "정보 수정에 실패하였습니다.");
        request.setAttribute("icon", "error");
        request.setAttribute("loc", "/admin/update");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/adminDinnerDetail.jsp");
        dispatcher.forward(request, response);
    }
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
