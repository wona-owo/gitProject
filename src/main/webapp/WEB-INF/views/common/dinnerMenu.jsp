<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// 세션에서 로그인 상태 확인
Boolean isLogIn = (session.getAttribute("loginMember") != null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dinner Detail</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
.menu-item {
	display: block;
	margin: 10px auto; /* 가운데 정렬 */
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background: #fefefe;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
	width: 50%; /* 박스 크기를 반으로 줄임 */
	text-align: center; /* 텍스트 가운데 정렬 */
}
</style>
</head>
<body>
	<div class="tab-content" id="menu-content">
		<p>메뉴 컨텐츠</p>
		<div id="menu-list"></div>
	</div>

	<script>
    // JSP에서 전달된 dinnerNo 값을 가져옵니다.
    const dinnerNo = "<%=request.getParameter("dinnerNo")%>";

    if (!dinnerNo || dinnerNo.trim() === "") {
        console.error("dinnerNo is not defined or empty.");
    } else {
        console.log("DinnerNo: ", dinnerNo); // 디버깅용 출력

        $.ajax({
            url: "/menuData",
            method: "GET",
            data: { dinnerNo: dinnerNo },
            success: function (response) {
                console.log("Response Data: ", response);

                if (Array.isArray(response) && response.length > 0) {
                    let menuHtml = "<ul>";
                    response.forEach(menu => {
                        const foodName = menu.foodName || "메뉴 이름 없음";
                        const price = menu.price ? Number(menu.price).toLocaleString() : "가격 정보 없음";

                        console.log("Rendering Menu Item: ", foodName, price);
                        menuHtml += "<li class='menu-item'>" + foodName + " - " + price + "원</li>";
                    });
                    menuHtml += "</ul>";
                    $("#menu-list").html(menuHtml);
                } else {
                    $("#menu-list").html("<p>메뉴가 없습니다.</p>");
                }
            },
            error: function (xhr, status, error) {
                console.error("AJAX Error: ", error);
            }
        });
    }
    </script>
</body>
</html>