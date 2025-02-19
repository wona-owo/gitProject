package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		// book 등록값
		String dinnerNo = request.getParameter("dinnerNo");
		String bookTime = request.getParameter("bookTime");
		String bookCntStr = request.getParameter("bookCnt");
		String bookDate = request.getParameter("bookDate");

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberNo = loginMember.getMemberNo();

		MemberService mservice = new MemberService();

		// bookDate 값 substring로 'yyyy/mm/dd'로 전달
		String year = bookDate.substring(0, 4);
		String month = bookDate.substring(5, 7);
		String day = bookDate.substring(8, 10);
		String date = (year + "/" + month + "/" + day);

		Book book = new Book();
		book.setDinnerNo(dinnerNo);
		book.setMemberNo(memberNo);
		book.setBookDate(date);
		book.setBookTime(bookTime);
		book.setBookCnt(Integer.parseInt(bookCntStr));

		int result = mservice.bookingMember(book);

		if (result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "예약 성공 했습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("callback", "window.close()");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "예약 실패했습니다. 날짜, 시간 똑바로 입력하세요");
			request.setAttribute("icon", "error");
			request.setAttribute("callback", "window.close()");
		}

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		view.forward(request, response);
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
