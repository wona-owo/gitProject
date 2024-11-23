package com.menupick.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.member.model.service.MemberService;

/**
 * Servlet implementation class MemberReviewUpdateServlet
 */
@WebServlet("/member/reviewModi")
public class MemberReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberReviewUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // POST 요청에서 데이터 추출
        request.setCharacterEncoding("UTF-8"); // 한글 인코딩 처리

        // 리뷰 번호와 내용 추출
        String reviewNo = request.getParameter("reviewNo");
        String reviewCon = request.getParameter("reviewCon");

        // 입력 값 검증
        if (reviewNo == null || reviewNo.isEmpty() || reviewCon == null || reviewCon.isEmpty()) {
            request.setAttribute("message", "리뷰 번호나 내용이 유효하지 않습니다.");
            request.getRequestDispatcher("/WEB-INF/views/member/reviewUpdate.jsp").forward(request, response);
            return;
        }

        // 비즈니스 로직 호출
        MemberService service = new MemberService();
        int result = service.memberUpdateReview(reviewNo, reviewCon);

        // 결과 처리
        if (result > 0) {
            // 성공 시 리뷰 체크 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/ckReview");
        } else {
            // 실패 시 에러 메시지와 함께 수정 페이지로 포워드
            request.setAttribute("message", "리뷰 수정에 실패했습니다. 다시 시도해주세요.");
            request.getRequestDispatcher("/WEB-INF/views/member/reviewUpdate.jsp").forward(request, response);
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
