package com.menupick.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

/**
 * Servlet implementation class MemberLikeAdd
 */
@WebServlet("/member/addLike")
public class MemberAddLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddLikeServlet() {
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
		
		//비즈니스 로직 -> 식당 추가 (성공 여부에 따라 참/거짓 반환)
		
		MemberService service = new MemberService();
		boolean likeDinner = service.memberAddLike(dinnerNo,memberNo);
		
		//결과처리 - JSON 형식으로 응답
		response.getWriter().print("{\"add\": " + likeDinner + "}");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
