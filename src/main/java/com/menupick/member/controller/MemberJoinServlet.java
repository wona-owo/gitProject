package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/member/join")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberJoinServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberId = request.getParameter("member_id");
		String memberPw = request.getParameter("member_pw");
		String memberName = request.getParameter("member_name");
		String memberNick = request.getParameter("member_nick");
		String memberPhone = request.getParameter("memberPhone");
		String memberAddr = request.getParameter("memberAddr");
		String memberGender = request.getParameter("member_gender");
		String memberEmail = request.getParameter("member_email");
		String adultConfirm = request.getParameter("adult_confirm");

		MemberService service = new MemberService();
		Member member = new Member();
		int result = service.insertMember(member);

		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberNick(memberNick);
		member.setMemberPhone(memberPhone);
		member.setMemberAddr(memberAddr);
		member.setMemberGender(memberGender);
		member.setMemberEmail(memberEmail);
		member.setAdultConfirm(adultConfirm);

		if (result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "회원가입이 완료 되었습니다 로그인 페이지로 이동합니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/loginFrm");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "회원가입에 실패 하였습니다 메인 페이지로 이동합니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
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