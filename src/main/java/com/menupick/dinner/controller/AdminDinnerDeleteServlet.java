package com.menupick.dinner.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;

/**
 * Servlet implementation class AdminDinnerDeleteServlet
 */
@WebServlet("/dinner/delete")
public class AdminDinnerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDinnerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1. 인코딩 
	//2.결과추출
		String dinnerNo = request.getParameter("dinnerNo");
	//3.비지니스 로직
		DinnerService service = new DinnerService();
		int result = service.deleteDinner(dinnerNo);
	//4.결과 반환
		if (result > 0) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장 회원 삭제가 완료 되었습니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/");
		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장회원 삭제 오류가 발생하였습니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
