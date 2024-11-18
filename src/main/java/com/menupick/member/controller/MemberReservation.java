package com.menupick.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.service.MemberService;


/**
 * Servlet implementation class MemberReservation
 */
@WebServlet("/member/reservation")
public class MemberReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberReservation() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//book 등록값
		String dinnerNo = request.getParameter("dinnerNo");
		String memberNo = request.getParameter("memberId");
		String bookDate = request.getParameter("bookDate");
		String bookTime = request.getParameter("bookTime");
		String bookCnt = request.getParameter("bookCnt");
		MemberService mservice = new MemberService();
		DinnerService dservice = new DinnerService();
		Book book = new Book();
		Dinner dinner = dservice.getDinnerNo(dinnerNo);
		
		int result = mservice.bookingMember(book);
		
		
		request.setAttribute("dinner", dinner);

		System.out.println("dinnerNo: " + dinnerNo);
        System.out.println("memberNo: " + memberNo);
        System.out.println("bookDate: " + bookDate);
        System.out.println("bookCnt: " + bookCnt);
        System.out.println("bookTime: " + bookTime);

        
		request.getRequestDispatcher("/WEB-INF/views/member/memberReservation.jsp").forward(request, response);
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
