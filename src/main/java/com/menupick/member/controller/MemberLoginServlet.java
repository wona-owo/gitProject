package com.menupick.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("saveId : " + request.getParameter("saveId"));  <- 체크시 chk, 체크 안하는 경우 null
		
				//1. 인코딩 -> 필터
				
				//2. 값 추출
				String loginId = request.getParameter("loginId");
				String loginPw = request.getParameter("loginPw");
				
				//3. 비즈니스 로직 -> 로그인
				MemberService service = new MemberService();
				Member loginMember = service.memberLogin(loginId, loginPw);
				
				//4. 결과 처리
				
				if(loginMember != null) {
					//정상로그인 !

					//로그인 정보는 존재하지만, level값이 3인 회원인 경우
					if(loginMember.getMemberLevel() == 3) {
						request.setAttribute("title", "알림");
						request.setAttribute("msg", "로그인 권한이 없습니다. 관리자에게 문의하세요.");
						request.setAttribute("icon", "error");
						request.setAttribute("loc", "/member/loginFrm");
						
						request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
						return;
					}
					/*
					 getSession() : 매개변수에 true를 전달한 것과 동일
					 getSession(true) : 기존에 세션이 존재하면, 해당 세션을 반환. 존재하지 않으면 새로운 세션을 생성하여 반환
					 getSession(false) : 기존에 세션이 존재하면, 해당 세션을 반환. 존재하지 않으면 null을 반환
					*/ 	
				
					HttpSession session = request.getSession(true);
					session.setAttribute("loginMember", loginMember);	//세션에 로그인 회원 정보 등록
					session.setMaxInactiveInterval(600);	//단위 == 초 == 600초 == 10분		
					
					
					/*
					 세션은 웹서버에 저장됨
					 세션도 쿠키를 이용하긴 함. 
					 아이디 정보는 클라이언트(브라우저)에 저장함(상대적으로 덜 중요. 상대적으로 보안성 부족 but 속도는 빠름) -> 아이디는 쿠키에 저장함
					 */
					
					/*
					쿠키 : 클라이언트에 저장되는 공간을 의미함
					 - 세션에 비해 상대적으로 보안이 취약함
					*/
								
					Cookie cookie = new Cookie("saveId", loginId);
					
					if(request.getParameter("saveId") != null) {
						//아이디 저장 체크박스를 체크한 경우
						cookie.setMaxAge(60 * 60 * 24 * 30);	//단위 == 초 -- 60 * 60 * 24 * 30 == 30일
					} else {
						//아이디 저장 체크박스를 체크하지 않은 경우
						cookie.setMaxAge(0);	//유효시간을 0초로 변경하여, 결론적으로 쿠키를 해제한다.
					}
					
					//쿠키를 적용시킬 경로
					cookie.setPath("/member/loginFrm");
					
					//쿠키는 클라이언트에 저장되는 정보이므로, 응답객체를 통해 전달해 줌
					response.addCookie(cookie);
					
					response.sendRedirect("/");
					
				} else {
					//null 일때 == 입력한 아이디, 비밀번호와 일치하는 회원이 없을 때 == 로그인 실패 
					request.setAttribute("title", "알림");
					request.setAttribute("msg", "아아디 또는 비밀번호를 확인하세요.");
					request.setAttribute("icon", "error");
					request.setAttribute("loc", "/member/loginFrm"); //다시 로그인 페이지로 이동할 수 있도록
					
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					view.forward(request, response);
				}
				
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
