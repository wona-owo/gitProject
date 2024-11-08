package com.menupick.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLikeServlet
 */
@WebServlet("/member/like")
public class MemberLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		
		//2. 값 추출 - 회원 번호를 받아와서 쿼리에 전달.
		String memberNo = request.getParameter("member_no");
		
		//3. 비즈니스 로직 - 회원 번호와 일치하는 식당 정보 list 추출
		MemberService service = new MemberService();
		ArrayList<Dinner> likeList = service.memberLikeList(memberNo);
		
		//4. 결과처리 - ajax 방식으로 계속 리스트 송출, gson 활용
		Gson gson = new Gson();
		String jsonStr = gson.toJson(likeList);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // 응답 데이터 형식 지정
		response.getWriter().print(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
