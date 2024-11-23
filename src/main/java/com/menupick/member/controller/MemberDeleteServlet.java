package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/views/member/delete.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				///인코딩 - 필터
				//값 추출
				String memberNo = request.getParameter("memberNo"); //삭제시 필요한 값
				
				//로직 -회원탈퇴
				MemberService service = new MemberService();
				int result = service.deleteMember(memberNo);

				//결과처리
				if(result>0) {
					HttpSession session = request.getSession(false);
					if(session != null) {
						session.invalidate();
					}
					
					//정상탈퇴
					request.setAttribute("title", "알림");
					request.setAttribute("msg", "회원 탈퇴가 완료되었습니다");
					request.setAttribute("icon", "success");
					request.setAttribute("loc", "/");
				}else {
					//탈퇴실패
					request.setAttribute("title", "알림");
					request.setAttribute("msg", "회원 탈퇴중 오류가 발생하였습니다");
					request.setAttribute("icon", "error");
					request.setAttribute("loc", "/member/mypage");			
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				view.forward(request, response);
			}

}
