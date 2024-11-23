package com.menupick.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberServlet
 */
@WebServlet("/admin/member")
public class AdminMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMemberServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		MemberService service = new MemberService();
	    String action = request.getParameter("action");

	    if ("search".equals(action)) {
	        // 검색 처리
	        String memberNick = request.getParameter("memberNick");
	        List<Member> members = service.searchMembersByNick(memberNick);

	        request.setAttribute("members", members);
	        request.setAttribute("currentPage", 1); // 검색 시 페이지 번호 고정
	        request.setAttribute("totalPages", 1); // 검색 결과는 한 페이지로 처리

	        request.getRequestDispatcher("/WEB-INF/views/admin/adminMember.jsp").forward(request, response);
	    } else {
	        // 기존 회원 목록 처리
	        int page = 1;
	        int pageSize = 15;

	        if (request.getParameter("page") != null) {
	            page = Integer.parseInt(request.getParameter("page"));
	        }

	        List<Member> members = service.getMembers(page, pageSize);
	        int totalMembers = service.getTotalMemberCount();
	        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);

	        request.setAttribute("members", members);
	        request.setAttribute("currentPage", page);
	        request.setAttribute("totalPages", totalPages);

	        request.getRequestDispatcher("/WEB-INF/views/admin/adminMember.jsp").forward(request, response);
	    }
		
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
