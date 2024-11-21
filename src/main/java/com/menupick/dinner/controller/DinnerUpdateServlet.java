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
import javax.servlet.http.HttpSession;

import com.menupick.common.vo.MyRenamePolicy;
import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;
import com.menupick.dinner.vo.Photo;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/dinner/update")
public class DinnerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DinnerUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// daniel - 사진을 입력 받는것
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String savePath = rootPath + "resources/photos/";
		int maxSize = 1024 * 1024 * 100;

		File dir = new File(savePath);

		if (!dir.exists()) {
			dir.mkdir();
		}

		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());

		Enumeration<String> files = mRequest.getFileNames();

		ArrayList<Photo> photoList = new ArrayList<>();

		while (files.hasMoreElements()) {
			String name = files.nextElement();
			String pName = mRequest.getOriginalFileName(name);
			String pPath = mRequest.getFilesystemName(name);
			if (pPath != null) {
				Photo p = new Photo();
				p.setPhotoName(pName);
				p.setPhotoPath(pPath);

				photoList.add(p);
			}
		}

		// 입력 값 추출
		String dinnerNo = mRequest.getParameter("dinnerNo");
		String dinnerName = mRequest.getParameter("dinnerName");
		String dinnerId = mRequest.getParameter("dinnerId");
		String dinnerAddr = mRequest.getParameter("dinnerAddr");
		String dinnerOpen = mRequest.getParameter("dinnerOpen");
		String dinnerClose = mRequest.getParameter("dinnerClose");
		String dinnerPhone = mRequest.getParameter("dinnerPhone");
		String dinnerEmail = mRequest.getParameter("dinnerEmail");
		String dinnerParking = mRequest.getParameter("dinnerParking");
		String busiNo = mRequest.getParameter("busiNo");
		String dinnerMaxPerson = mRequest.getParameter("dinnerMaxPerson");
		String dinnerConfirm = mRequest.getParameter("dinnerConfirm");
		String photoPath = mRequest.getParameter("photoPath");

		// 기본값 설정
		if (dinnerConfirm == null || dinnerConfirm.isEmpty()) {
			dinnerConfirm = "N";
		}

		// 유효성 검사 (예: 시간 형식)
		if (!dinnerOpen.matches("^([01][0-9]|2[0-3]):[0-5][0-9]$")) {
			request.setAttribute("msg", "오픈 시간 형식이 잘못되었습니다.");
			forwardToErrorPage(request, response);
			return;
		}

		if (!dinnerClose.matches("^([01][0-9]|2[0-3]):[0-5][0-9]$")) {
			request.setAttribute("msg", "마감 시간 형식이 잘못되었습니다.");
			forwardToErrorPage(request, response);
			return;
		}

		if (!dinnerPhone.matches("^010-\\d{3,4}-\\d{4}$")) {
			request.setAttribute("msg", "전화번호 형식이 잘못되었습니다.");
			forwardToErrorPage(request, response);
			return;
		}

		// Dinner 객체 생성
		Dinner updDinner = new Dinner();
		updDinner.setDinnerNo(dinnerNo);
		updDinner.setDinnerName(dinnerName);
		updDinner.setDinnerId(dinnerId);
		updDinner.setDinnerAddr(dinnerAddr);
		updDinner.setDinnerOpen(dinnerOpen);
		updDinner.setDinnerClose(dinnerClose);
		updDinner.setDinnerPhone(dinnerPhone);
		updDinner.setDinnerEmail(dinnerEmail);
		updDinner.setDinnerParking(dinnerParking);
		updDinner.setBusiNo(busiNo);
		updDinner.setDinnerMaxPerson(dinnerMaxPerson);
		updDinner.setDinnerConfirm(dinnerConfirm);

		DinnerService service = new DinnerService();

		if (photoPath.length() < 1) {
			service.insertFakePhoto(dinnerNo);
		}

		updDinner.setPhotoList(photoList);

		String absolutePath = request.getSession().getServletContext().getRealPath("/") + "resources/photos/";
		String prevPhotoPath = service.dinnerPhotoPath(dinnerNo);

		if (prevPhotoPath != null) {
			absolutePath += prevPhotoPath;

			File file = new File(absolutePath);
			if (file.exists()) {
				file.delete();
			}
		}

		System.out.println(updDinner);

		// 서비스 호출
		int result = service.updateDinner(updDinner);

		if (result > 0) {
			// 데이터베이스 수정 성공 시, 세션 갱신
			HttpSession session = request.getSession();
			Dinner updatedDinner = service.getDinnerByNo(dinnerNo); // 수정된 데이터를 다시 가져옴
			session.setAttribute("loginMember", updatedDinner); // 세션 갱신

			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장 정보가 수정되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/dinner/settingFrm");

		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "매장 정보 수정 중 오류가 발생했습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/dinner/settingFrm");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// ":" 추가 메서드
	private String addColonToTime(String time) {
		if (time != null && time.length() == 4) {
			return time.substring(0, 2) + ":" + time.substring(2); // "1200" -> "12:00"
		}
		return time;
	}

	private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
	}
}
