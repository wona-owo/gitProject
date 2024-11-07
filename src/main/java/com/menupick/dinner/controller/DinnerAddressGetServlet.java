package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Address;

/**
 * Servlet implementation class DinnerAddressGetServlet
 */
@WebServlet("/locations")
public class DinnerAddressGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerAddressGetServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DinnerService service = new DinnerService();

		// 주소 데이터 가져오기
		List<Address> addresses = service.getDinnerAddress();

		// JSON 변환 및 응답
		Gson gson = new Gson();
		String json = gson.toJson(addresses);

		response.getWriter().write(json);
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
