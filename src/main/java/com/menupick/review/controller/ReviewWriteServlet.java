package com.menupick.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet("/review/write")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 가져오기
        String dinnerNo = request.getParameter("dinnerNo");
        String memberNo = request.getParameter("memberNo");
        String content = request.getParameter("content");
        
        ReviewService service = new ReviewService();
        boolean isSuccess = service.insertReview(dinnerNo, memberNo, content);
        
        if (isSuccess) {
            // 리뷰 작성 성공 시
            request.setAttribute("title", "성공!");
            request.setAttribute("msg", "리뷰 작성이 완료되었습니다.");
            request.setAttribute("icon", "success");
            request.setAttribute("loc", "/dinner/dinnerDetail?dinnerNo=" + dinnerNo); // 리다이렉트 URL
        } else {
            // 리뷰 작성 실패 시
            request.setAttribute("title", "실패!");
            request.setAttribute("msg", "리뷰 작성에 실패했습니다. 다시 시도해주세요.");
            request.setAttribute("icon", "error");
            request.setAttribute("loc", "/dinner/dinnerDetail?dinnerNo=" + dinnerNo); // 실패 시에도 동일 페이지로 이동
        }

        // msg.jsp로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
