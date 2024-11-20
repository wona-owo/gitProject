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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dinnerNo = request.getParameter("dinnerNo"); // 히든 필드
		
		String rootPath = request.getSession().getServletContext().getRealPath("/"); // webapp 폴더 경로
		String savePath = rootPath + "resources/photos/" + dinnerNo + "/"; // 파일 저장 경로

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

        String dinnerName = request.getParameter("dinnerName");
        
        String zipp_code = request.getParameter("zipp_code"); // 우편번호
		String userAdd1 = request.getParameter("user_add1");   // 기본 주소
		String userAdd2 = request.getParameter("user_add2");   // 상세 주소
		
        String dinnerOpen = request.getParameter("dinnerOpen");
        String dinnerClose = request.getParameter("dinnerClose");
        String dinnerPhone = request.getParameter("dinnerPhone");
        String dinnerEmail = request.getParameter("dinnerEmail");
        String dinnerParking = request.getParameter("dinnerParking");
        String dinnerMaxPerson = request.getParameter("dinnerMaxPerson");
        String busiNo = request.getParameter("busiNo");
        String dinnerId = request.getParameter("dinnerId");
        String dinnerPw = request.getParameter("dinnerPw");
        String dinnerConfirm = request.getParameter("dinnerConfirm"); // 히든 필드
        
        String dinnerAddr = zipp_code + " " + userAdd1 + " " + userAdd2;
        
        Dinner dinner = new Dinner(dinnerNo, dinnerName, dinnerAddr, dinnerOpen, dinnerClose,
                dinnerPhone, dinnerEmail, dinnerParking, dinnerMaxPerson, 
                busiNo, dinnerId, dinnerPw, dinnerConfirm, null, null, null, null, null, null);
        
        DinnerService service = new DinnerService();
        boolean isInserted = service.insertDinner(dinner, photoList);
        
        System.out.println("dinnerName : " + dinnerName);
        System.out.println("dinnerAddr : " + dinnerAddr);
        System.out.println("dinnerOpen : " + dinnerOpen);
        System.out.println("dinnerClose : " + dinnerClose);
        System.out.println("dinnerPhone : " + dinnerPhone);
        System.out.println("dinnerEmail : " + dinnerEmail);
        System.out.println("dinnerParking : " + dinnerParking);
        System.out.println("dinnerMaxPerson : " + dinnerMaxPerson);
        System.out.println("busiNo : " + busiNo);
        System.out.println("dinnerId : " + dinnerId);
        System.out.println("dinnerPw : " + dinnerPw);
        System.out.println("dinnerConfirm : " + dinnerConfirm);
        
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
