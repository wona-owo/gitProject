package com.menupick.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberChkBook
 */
@WebServlet("/member/checkReservedTimes")
public class MemberChkBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberChkBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookDate = request.getParameter("bookDate");
        String dinnerNo = request.getParameter("dinnerNo");

        response.setContentType("application/json; charset=UTF-8");

        if (bookDate == null || dinnerNo == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"필수 파라미터가 누락되었습니다.\"}");
            return;
        }

        // 예약된 시간대 조회
        MemberService memberService = new MemberService();
        List<String> reservedTimes = memberService.getReservedTimes(dinnerNo, bookDate);

        // JSON 형식으로 응답 반환
        response.getWriter().write(new Gson().toJson(reservedTimes));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
