package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Photo;

/**
 * Servlet implementation class DinnerSettingServletFrm
 */
@WebServlet("/dinner/settingFrm")
public class DinnerSettingFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerSettingFrmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Dinner loginDinner = (Dinner) session.getAttribute("loginMember");

		if (loginDinner == null) {
			response.sendRedirect("/member/loginFrm");
			return;
		}
		
		String dinnerNo = loginDinner.getDinnerNo();
		
		DinnerService service = new DinnerService();
		String photoPath = service.dinnerPhotoPath(dinnerNo);
		
		ArrayList<Photo> photoList = new ArrayList<>();
		
		Photo photo = new Photo();
		
		photo.setPhotoPath(photoPath);
		
		photoList.add(photo); 
		
		loginDinner.setPhotoList(photoList);
		
		request.setAttribute("dinner", loginDinner);
		request.setAttribute("photoPath", photoPath);
		request.getRequestDispatcher("/WEB-INF/views/dinner/dinnerSetting.jsp").forward(request, response);
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
