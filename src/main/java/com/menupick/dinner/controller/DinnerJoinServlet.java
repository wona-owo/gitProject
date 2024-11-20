package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class DinerJoinServlet
 */
@WebServlet("/dinnerJoin")
public class DinnerJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DinnerJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinnerNo"); // 히든 필드
        String dinnerName = request.getParameter("dinnerName");
        String dinnerAddr = request.getParameter("dinnerAddr"); // 우편번호
        String dinnerOpen = request.getParameter("dinnerOpen");
        String dinnerClose = request.getParameter("dinnerClose");
        String dinnerPhone = request.getParameter("dinnerPhone");
        String dinnerEmail = request.getParameter("dinnerEmail");
        String dinnerParking = request.getParameter("dinnerParking");
        String dinnerMaxPerson = request.getParameter("dinnerMaxPerson");
        String busiNo = request.getParameter("busiNo");
        String dinnerId = request.getParameter("dinnerId");
        String dinnerPw = request.getParameter("dinnerPw");
        String dinnerConfirm = request.getParameter("dinnerConfirm"); // 히든 필드
        
        
        Dinner dinner = new Dinner(dinnerNo, dinnerName, dinnerAddr, dinnerOpen, dinnerClose,
                dinnerPhone, dinnerEmail, dinnerParking, dinnerMaxPerson, 
                busiNo, dinnerId, dinnerPw, dinnerConfirm, null, null, null, null, null);
        
        DinnerService service = new DinnerService();
        boolean isInserted = service.insertDinner(dinner);
        
        System.out.println("dinnerName : " + dinnerName);
        System.out.println("dinnerAddr : " + dinnerAddr);
        System.out.println("dinnerOpen : " + dinnerOpen);
        System.out.println("dinnerClose : " + dinnerClose);
        System.out.println("dinnerPhone : " + dinnerPhone);
        System.out.println("dinnerEmail : " + dinnerEmail);
        System.out.println("dinnerParking : " + dinnerParking);
        System.out.println("dinnerMaxPerson : " + dinnerMaxPerson);
        System.out.println("busiNo : " + busiNo);
        System.out.println("dinnerId : " + dinnerId);
        System.out.println("dinnerPw : " + dinnerPw);
        System.out.println("dinnerConfirm : " + dinnerConfirm);
        
        if (isInserted) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "식당 등록에 성공 하였습니다 로그인 페이지로 이동합니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/loginFrm");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "식당 등록에 실패하였습니다 메인 메이지로 이동합니다");
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
