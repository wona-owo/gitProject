package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

/**
 * Servlet implementation class DinnerChgPwServletFrm
 */
@WebServlet("/dinner/chgPw")
public class DinnerChgPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerChgPwServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dinnerId = request.getParameter("dinnerId");
		String newDinnerPw = request.getParameter("newDinnerPw");

		HttpSession session = request.getSession(false);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(session != null) {
			Dinner loginMember = (Dinner) session.getAttribute("loginMember");
			
			// After password encryption
						if (!BCrypt.checkpw(dinnerId, loginMember.getDinnerPw())) {
							request.setAttribute("title", "실패");
							request.setAttribute("msg", "기존 비밀번호가 일치하지 않습니다");
							request.setAttribute("icon", "error");
							request.setAttribute("callback", "self.close();"); // 이동한 msg.jsp 에서 알림창을 띄워주고난 이후에 실행할 함수 등록

							view.forward(request, response);
							return;
						}
						DinnerService service = new DinnerService();
						int result = service.updateMemberPw(dinnerId, newDinnerPw);
						
						// 4. Process results
						if (result > 0) {
							request.setAttribute("title", "알림");
							request.setAttribute("msg", "비밀번호가 변경되었습니다. 변경된 비밀번호로 다시 로그인하세요");
							request.setAttribute("icon", "success");
							// loc 를 사용하면 팝업창에서 로그인창으로 이동하게 된다
							// bslsh 를 작성해주면 물자열로 동작한다
							request.setAttribute("callback", "self.close();window.opener.location.href=\'/member/loginFrm\';");
							// 세션 정보를 소멸시킨다
							session.invalidate();
						} else {
							request.setAttribute("title", "실패");
							request.setAttribute("msg", "비밀번호 변경 중, 오류가 발생하였습니다");
							request.setAttribute("icon", "error");
							request.setAttribute("callback", "self.close();");
						}
						view.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
