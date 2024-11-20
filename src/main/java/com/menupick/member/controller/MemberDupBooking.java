package com.menupick.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.vo.Book;
import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class MemberDupBooking
 */
@WebServlet("/member/dupBookChk")
public class MemberDupBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDupBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberNo = loginMember.getMemberNo();
		String bookNo = request.getParameter("book_no");
		String bookDate = request.getParameter("book_date");
		String bookTime = request.getParameter("book_time");
		
		MemberService service = new MemberService();
		
		boolean bookDupChk = service.getDupBookChk(memberNo, bookNo,bookDate,bookTime);
		
		
		request.setAttribute("bookDupChk", bookDupChk);
		request.setAttribute("bookNo", bookNo);
		request.setAttribute("bookDate", bookDate);
		request.setAttribute("bookTime", bookTime);
		
		System.out.println(bookTime);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberReservation.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
