package com.menupick.member.controller;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/searchId")
public class MemberSearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService Service = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터 가져오기
        String memberName = request.getParameter("memberName");
        String memberPhone = request.getParameter("memberPhone");

        // 서비스 호출해서 아이디 조회
        String memberId = Service.searchMemberId(memberName, memberPhone);

        // 응답 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(memberId != null ? memberId : "");

    }
}