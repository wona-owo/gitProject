package com.menupick.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;
import com.menupick.review.model.service.ReviewService;
import com.menupick.review.model.vo.MemberReport;
import com.menupick.review.model.vo.Review;
import com.menupick.review.model.vo.ReviewReport;

/**
 * Servlet implementation class AdminMemberDetailServlet
 */
@WebServlet("/admin/memberDetail")
public class AdminMemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMemberDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩

		// 2. 값 추출
		String memberNo = request.getParameter("memberNo");
		String sortOption = request.getParameter("sortOption"); // 정렬 옵션 받기

		if (sortOption == null || sortOption.isEmpty()) {
			sortOption = "latest"; // 기본값: 최신순
		}

		// 3. 로직
		// 회원 정보 가져오기
		MemberService service = new MemberService();
		Member member = service.getMemberNo(memberNo);

		// 리뷰 정보 가져오기
		ReviewService rvservice = new ReviewService();
		List<Review> reviews = rvservice.getReviewsByMemberNo(memberNo, sortOption);

		// 리뷰별 신고 횟수 가져오기
		List<ReviewReport> reviewReports = rvservice.getReviewReportsByMemberNo(memberNo);
		
		// 회원별 신고 횟수 가져오기
        MemberReport memberReport = rvservice.getMemberReport(memberNo);

        
        
		// 4. 결과 처리
		request.setAttribute("member", member);
		request.setAttribute("reviews", reviews);
		request.setAttribute("memberReport", memberReport);     // 회원 신고 횟수
        request.setAttribute("reviewReports", reviewReports);   // 리뷰별 신고 횟수
		request.setAttribute("sortOption", sortOption); // 현재 정렬 옵션 전달
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberDetail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
