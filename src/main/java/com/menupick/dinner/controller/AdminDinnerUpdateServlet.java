package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

@WebServlet("/adminDinner/update")
public class AdminDinnerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminDinnerUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 값 추출
        String dinnerNo = request.getParameter("dinnerNo");
        String dinnerName = request.getParameter("dinnerName");
        String dinnerId = request.getParameter("dinnerId");
        String dinnerAddr = request.getParameter("dinnerAddr");

        // 입력받은 시간 값에서 ':' 제거
        String dinnerOpen = request.getParameter("dinnerOpen").replace(":", "");
        String dinnerClose = request.getParameter("dinnerClose").replace(":", "");

        String dinnerPhone = request.getParameter("dinnerPhone");
        String dinnerEmail = request.getParameter("dinnerEmail");
        String dinnerParking = request.getParameter("dinnerParking");
        String busiNo = request.getParameter("busiNo");
        String dinnerMaxPerson = request.getParameter("dinnerMaxPerson");
        String dinnerConfirm = request.getParameter("dinnerConfirm");

        // Dinner 객체 생성 및 값 설정
        Dinner updDinner = new Dinner();
        updDinner.setDinnerNo(dinnerNo);
        updDinner.setDinnerName(dinnerName);
        updDinner.setDinnerId(dinnerId);
        updDinner.setDinnerAddr(dinnerAddr);
        updDinner.setDinnerOpen(dinnerOpen); // ':' 제거된 형식 저장
        updDinner.setDinnerClose(dinnerClose); // ':' 제거된 형식 저장
        updDinner.setDinnerPhone(dinnerPhone);
        updDinner.setDinnerEmail(dinnerEmail);
        updDinner.setDinnerParking(dinnerParking);
        updDinner.setBusiNo(busiNo);
        updDinner.setDinnerMaxPerson(dinnerMaxPerson);
        updDinner.setDinnerConfirm(dinnerConfirm);

        // 서비스 호출
        DinnerService service = new DinnerService();
        int result = service.updateDinner(updDinner);

        if (result > 0) {
            // 성공 시: 포맷팅된 데이터를 다시 화면에 표시
            Dinner updatedDinner = service.getDinnerByNo(dinnerNo);
            if (updatedDinner != null) {
                // 시간 데이터에 ':' 추가
                updatedDinner.setDinnerOpen(formatTimeWithColon(updatedDinner.getDinnerOpen())); 
                updatedDinner.setDinnerClose(formatTimeWithColon(updatedDinner.getDinnerClose()));
                
                request.setAttribute("dinner", updatedDinner); // 포맷팅된 데이터 전달
            }

            request.setAttribute("title", "알림");
            request.setAttribute("msg", "매장 정보가 수정되었습니다.");
            request.setAttribute("icon", "success");
            request.setAttribute("loc", "/"); 

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
            dispatcher.forward(request, response);
        } else {
            // 실패 시
            request.setAttribute("title", "알림");
            request.setAttribute("msg", "매장정보 수정 중 오류가 발생했습니다.");
            request.setAttribute("icon", "error");
            request.setAttribute("loc", "/");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
            dispatcher.forward(request, response);
        }
    }

    // 시간 데이터 포맷팅 메서드
    private String formatTimeWithColon(String time) {
        if (time != null && time.length() == 4) {
            return time.substring(0, 2) + ":" + time.substring(2);
        }
        return time; // 잘못된 형식은 그대로 반환
    }
}