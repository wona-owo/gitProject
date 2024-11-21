package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteBookServlet
 */
@WebServlet("/member/delBook")
public class MemberDeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//값 추출 -> 클릭한 회원 번호, 예약번호
				String bookNo = request.getParameter("bookNo");
				
				//비즈니스 로직 -> 예약 취소
				
				MemberService service = new MemberService();
				int result = service.memberDelBook(bookNo);
				
				response.setContentType("application/json; charset=UTF-8");
				
				if (result > 0) {
				    JSONObject json = new JSONObject();
				    json.put("status", "success");
				    json.put("message", "예약 취소");
				    response.getWriter().print(json.toString());
					
				} else {
					JSONObject json = new JSONObject();
				    json.put("status", "error");
				    json.put("message", "예약 취소 중 오류 발생");
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
