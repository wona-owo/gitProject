package com.menupick.dinner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Dinner;

@WebServlet("/dinner/update")
public class DinnerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DinnerUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 입력 값 추출
        String dinnerNo = request.getParameter("dinnerNo");
        String dinnerName = request.getParameter("dinnerName");
        String dinnerId = request.getParameter("dinnerId");
        String dinnerAddr = request.getParameter("dinnerAddr");
        String dinnerOpen = request.getParameter("dinnerOpen");
        String dinnerClose = request.getParameter("dinnerClose");
        String dinnerPhone = request.getParameter("dinnerPhone");
        String dinnerEmail = request.getParameter("dinnerEmail");
        String dinnerParking = request.getParameter("dinnerParking");
        String busiNo = request.getParameter("busiNo");
        String dinnerMaxPerson = request.getParameter("dinnerMaxPerson");
        String dinnerConfirm = request.getParameter("dinnerConfirm");

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

        // 서비스 호출
        DinnerService service = new DinnerService();
        int result = service.updateDinner(updDinner);


        if (result > 0) {
            // 데이터베이스 수정 성공 시, 세션 갱신
            HttpSession session = request.getSession();
            Dinner updatedDinner = service.getDinnerByNo(dinnerNo); // 수정된 데이터를 다시 가져옴
            session.setAttribute("loginMember", updatedDinner); // 세션 갱신

            request.setAttribute("title", "알림");
            request.setAttribute("msg", "매장 정보가 수정되었습니다.");
            request.setAttribute("icon", "success");
            request.setAttribute("loc", "/dinner/setting");
        } else {
            request.setAttribute("title", "알림");
            request.setAttribute("msg", "매장 정보 수정 중 오류가 발생했습니다.");
            request.setAttribute("icon", "error");
            request.setAttribute("loc", "/dinner/setting");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
        dispatcher.forward(request, response);
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
        dispatcher.forward(request, response);
    }
}

