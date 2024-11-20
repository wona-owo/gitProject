<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="tab-content" id="menu-content">
	    <c:if test="${not empty menuList}">
	        <ul>
	            <c:forEach var="menu" items="${menuList}">
	                <li>
	                    <span>${menu.dinnerNo} - ${menu.foodNo}</span>:
	                    <span>${menu.foodName}</span> 
	                    <span>${menu.price}원</span>
	                </li>
	            </c:forEach>
	        </ul>
	    </c:if>
	    <c:if test="${empty menuList}">
	        <p>메뉴가 없습니다.</p>
	    </c:if>
	</div>
</body>
</html>