package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Photo;


/**
 * Servlet implementation class DinnerLike
 */
@WebServlet("/dinner/likeFrm")
public class DinnerLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerLikeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 값 추출
		// dinner 값
		String dinnerNo = request.getParameter("dinner_no");
		String dinnerName = request.getParameter("dinner_name");
		

		// 3. 로직
		ArrayList<Dinner> dinnerList = new ArrayList<>();
		DinnerService service = new DinnerService();
		dinnerList = service.likeDinner(dinnerNo, dinnerName);
		
		for (Dinner d : dinnerList) {
		    String photoPath = service.dinnerPhotoPath(d.getDinnerNo());

		    // Get the photoList from the Dinner object
		    ArrayList<Photo> photoList = d.getPhotoList();

		    // Initialize photoList if it's null
		    if (photoList == null) {
		        photoList = new ArrayList<>();
		        d.setPhotoList(photoList);
		    }

		    Photo photo;

		    // Check if the list already has at least one Photo
		    if (photoList.size() > 0) {
		        // Get the first Photo and set its photoPath
		        photo = photoList.get(0);
		        photo.setPhotoPath(photoPath);
		    } else {
		        // Create a new Photo, set its photoPath, and add it to the list
		        photo = new Photo();
		        photo.setPhotoPath(photoPath);
		        photoList.add(photo);
		    }
		}
		
		// 4. 결과 처리
		request.setAttribute("dinnerList", dinnerList);
		
		request.getRequestDispatcher("/WEB-INF/views/dinner/search.jsp").forward(request, response);
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
