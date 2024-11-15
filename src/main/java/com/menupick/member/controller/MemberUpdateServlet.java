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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 수정 페이지로 이동
	    request.getRequestDispatcher("/WEB-INF/views/member/update.jsp").forward(request, response);
	/*			String memberNo = request.getParameter("memberNo");
				String memberId = request.getParameter("memberId");
				String memberPw = request.getParameter("memberPw");
				String memberName = request.getParameter("memberName");
				String memberNick = request.getParameter("memberNick");
				String memberPhone = request.getParameter("memberPhone");
				String memberAddr = request.getParameter("memberAddr");
				String memberEmail = request.getParameter("memberEmail");

				Member updMember = new Member();
				updMember.setMemberNo(memberNo);
				updMember.setMemberId(memberId);
				updMember.setMemberPw(memberPw);
				updMember.setMemberName(memberName);
				updMember.setMemberNick(memberNick);
				updMember.setMemberPhone(memberPhone);
				updMember.setMemberAddr(memberAddr);
				updMember.setMemberEmail(memberEmail);
				
				
				MemberService service = new MemberService();
				int result = service.updateMember(updMember);
				
				// 결과 처리
		        HttpSession session = request.getSession(false);
		        if (result > 0 && session != null) {
		            // 세션에 저장된 회원 정보도 업데이트
		            Member sessionMember = (Member) session.getAttribute("loginMember");
		            sessionMember.setMemberId(memberId);
		            sessionMember.setMemberPw(memberPw);
		            sessionMember.setMemberName(memberName);
		            sessionMember.setMemberNick(memberNick);
		            sessionMember.setMemberPhone(memberPhone);
		            sessionMember.setMemberAddr(memberAddr);
		            sessionMember.setMemberEmail(memberEmail);
		            
		         // 성공 메시지 설정
		            request.setAttribute("title", "알림");
		            request.setAttribute("msg", "회원 정보가 수정되었습니다. 재로그인 하시기 바랍니다.");
		            request.setAttribute("icon", "success");
		            request.setAttribute("loc", "/member/login");
		        } else {
		            // 오류 발생 시 메시지 설정
		            request.setAttribute("title", "알림");
		            request.setAttribute("msg", "회원 정보 수정 중 오류가 발생했습니다.");
		            request.setAttribute("icon", "error");
		            request.setAttribute("loc", "/member/mypage");
		        }
				request.getRequestDispatcher("/WEB-INF/views/member/update.jsp").forward(request, response);*/
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String memberNo = request.getParameter("memberNo");
        String memberId = request.getParameter("memberId");
        String memberPw = request.getParameter("memberPw");
        String memberName = request.getParameter("memberName");
        String memberNick = request.getParameter("memberNick");
        String memberPhone = request.getParameter("memberPhone");
        String memberEmail = request.getParameter("memberEmail");

        String zipp_code = request.getParameter("zipp_code");
        String userAdd1 = request.getParameter("user_add1");
        String userAdd2 = request.getParameter("user_add2");

        String memberAddr = zipp_code + " " + userAdd1 + " " + userAdd2;

        Member updMember = new Member();
        updMember.setMemberNo(memberNo);
        updMember.setMemberId(memberId);
        updMember.setMemberPw(memberPw);
        updMember.setMemberName(memberName);
        updMember.setMemberNick(memberNick);
        updMember.setMemberPhone(memberPhone);
        updMember.setMemberAddr(memberAddr);
        updMember.setMemberEmail(memberEmail);

        MemberService service = new MemberService();
        int result = service.updateMember(updMember);

        if (result > 0) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Member sessionMember = (Member) session.getAttribute("loginMember");
                sessionMember.setMemberId(memberId);
                sessionMember.setMemberPw(memberPw);
                sessionMember.setMemberName(memberName);
                sessionMember.setMemberNick(memberNick);
                sessionMember.setMemberPhone(memberPhone);
                sessionMember.setMemberAddr(memberAddr);
                sessionMember.setMemberEmail(memberEmail);
            }

            request.setAttribute("title", "성공");
            request.setAttribute("msg", "회원 정보가 수정되었습니다. 재로그인 하시기 바랍니다.");
            request.setAttribute("icon", "success");
            request.setAttribute("loc", "/member/login");

        } else {
            request.setAttribute("title", "실패");
            request.setAttribute("msg", "회원 정보 수정 중 오류가 발생했습니다.");
            request.setAttribute("icon", "error");
            request.setAttribute("loc", "/member/mypage");
        }

        // 메시지 출력용 JSP 페이지로 forward
        request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
    }
}
