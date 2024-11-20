package com.menupick.dinner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.Menu;

/**
 * Servlet implementation class DinnerFoodServlet
 */
@WebServlet("/dinner/menu")
public class DinnerMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DinnerService service = new DinnerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dinnerNo = null;
        String foodNo = null;
        
        try {
        	dinnerNo = request.getParameter("dinnerNo");
            foodNo = request.getParameter("foodNo");  // foodNo를 String으로 받음
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 메뉴 리스트 가져오기
        List<Menu> menuList = service.getMenuListByDinnerNo(dinnerNo, foodNo);

        // 메뉴 리스트를 request에 저장
        request.setAttribute("menuList", menuList);

        // 상세 페이지로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/restaurant/dinnerDetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
