<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>리뷰 신고</title>
</head>
<body>
    <form action="/review/report" method="post">
        <input type="hidden" name="review_no" value="<%= request.getParameter("review_no") %>">
        <input type="hidden" name="member_no" value="<%= request.getParameter("member_no") %>">
        <button type="submit">신고</button>
    </form>
</body>
</html>
