package com.menupick.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteLikeServlet
 */
@WebServlet("/member/delLike")
public class MemberDeleteLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출 -> 클릭한 식당 코드, 회원 번호
		String dinnerNo = request.getParameter("dinnerNo");

		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("loginMember");
		String memberNo = member.getMemberNo();
		
		//비즈니스 로직 -> 식당 삭제
		
		MemberService service = new MemberService();
		int result = service.memberDelLike(dinnerNo, memberNo);
		
		
		response.setContentType("application/json; charset=UTF-8");
		
		if (result > 0) {
		    JSONObject json = new JSONObject();
		    json.put("status", "success");
		    json.put("message", "즐겨찾기 삭제");
		    response.getWriter().print(json.toString());
			
		} else {
			JSONObject json = new JSONObject();
		    json.put("status", "error");
		    json.put("message", "삭제 중 오류 발생");
		    response.getWriter().print(json.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
