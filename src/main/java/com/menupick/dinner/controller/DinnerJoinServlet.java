package com.menupick.dinner.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.common.vo.MyRenamePolicy;
import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Photo;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class DinerJoinServlet
 */
@WebServlet("/dinnerJoin")
public class DinnerJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerJoinServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rootPath = request.getSession().getServletContext().getRealPath("/"); // webapp 폴더 경로
		String savePath = rootPath + "resources/photos/"; // 파일 저장 경로

		int maxSize = 1024 * 1024 * 100; // 바이트 단위 // 100MB 까지

		File dir = new File(savePath); // 오늘 날짜로 지정한 폴더

		if (!dir.exists()) { // 해당 경로에 폴더가 생성되어 있지 않을때
			dir.mkdir(); // 폴더 생성
		}

		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());

		// 3. 첨부파일 복수개 처리(input type = file 이 여러개 존재)
		Enumeration<String> files = mRequest.getFileNames(); // input type 이 file 인 태그들의 name 속성값

		// DB 에 저장할 때 필요한 피알정보들을 관리할 리스트
		ArrayList<Photo> photoList = new ArrayList<>();

		while (files.hasMoreElements()) {
			String name = files.nextElement(); // input type 이 file 인 태그의 name 속성값

			String pName = mRequest.getOriginalFileName(name); // 원본 파일명
			String pPath = mRequest.getFilesystemName(name); // 변경된 파일명

			if (pPath != null) {
				Photo p = new Photo();
				p.setPhotoName(pName);
				p.setPhotoPath(pPath);

				photoList.add(p);
			}
		}

		String dinnerName = mRequest.getParameter("dinnerName");
		String dinnerAddr = mRequest.getParameter("dinnerAddr"); // 우편번호
		String dinnerOpen = mRequest.getParameter("dinnerOpen");
		String dinnerClose = mRequest.getParameter("dinnerClose");
		String dinnerPhone = mRequest.getParameter("dinnerPhone");
		String dinnerEmail = mRequest.getParameter("dinnerEmail");
		String dinnerParking = mRequest.getParameter("dinnerParking");
		String dinnerMaxPerson = mRequest.getParameter("dinnerMaxPerson");
		String busiNo = mRequest.getParameter("busiNo");
		String dinnerId = mRequest.getParameter("dinnerId");
		String dinnerPw = mRequest.getParameter("dinnerPw");

		Dinner d = new Dinner();

		d.setDinnerName(dinnerName);
		d.setDinnerAddr(dinnerAddr);
		d.setDinnerOpen(dinnerOpen);
		d.setDinnerClose(dinnerClose);
		d.setDinnerPhone(dinnerPhone);
		d.setDinnerEmail(dinnerEmail);
		d.setDinnerParking(dinnerParking);
		d.setDinnerMaxPerson(dinnerMaxPerson);
		d.setBusiNo(busiNo);
		d.setDinnerId(dinnerId);
		d.setDinnerPw(dinnerPw);

		DinnerService service = new DinnerService();
		boolean isInserted = service.insertDinner(d, photoList);

		if (isInserted) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "식당 등록에 성공 하였습니다 로그인 페이지로 이동합니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/loginFrm");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "식당 등록에 실패하였습니다 메인 메이지로 이동합니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
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
