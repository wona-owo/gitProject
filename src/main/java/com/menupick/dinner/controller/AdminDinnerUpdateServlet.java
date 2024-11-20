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

    // POST 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 GET 요청처럼 처리
        doGet(request, response);
    }

    // GET 요청 처리
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 값 추출
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

        // Dinner 객체 생성 및 값 설정
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

        // 결과 처리
        if (result > 0) {
            // 성공 시
            request.setAttribute("title", "알림");
            request.setAttribute("msg", "매장 정보가 수정되었습니다. ");
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
}
