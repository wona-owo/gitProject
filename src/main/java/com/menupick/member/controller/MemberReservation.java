package com.menupick.member.controller;

import java.io.IOException;
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
import com.menupick.member.model.vo.Member;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dinnerNo = request.getParameter("dinner_no");
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("loginMember");
		String memberNo = member.getMemberNo();
		String bookDate = request.getParameter("book_date");
		String bookTime = request.getParameter("book_time");
		String bookCnt = request.getParameter("book_cnt");
		MemberService service = new MemberService();
		Book book = service.bookingMember(dinnerNo, memberNo);
		
		request.setAttribute("dinner", dinner);
		request.setAttribute("memberNo", memberNo);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberReservation.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
