<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 수정</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css">
    <style>
        body {
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }
        h1 {
            font-size: 1.5em;
            margin-bottom: 1rem;
            text-align: center;
            font-weight: bold;
        }
        textarea {
            width: 100%;
            margin-bottom: 1rem;
            padding: 0.5rem;
            border: 1px solid #f40;
            border-radius: 5px;
            font-size: 1rem;
            line-height: 1.5;
            resize: vertical;
        }
        .message {
            color: red;
            text-align: center;
            margin-bottom: 1rem;
        }
        .button-primary {
            width: 100%;
            padding: 0.5rem;
            background-color: #f40;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .button-primary:hover {
            background-color: #c30;
        }
    </style>
     <script>
        function validateForm() {
            if (document.getElementById('reviewCon').value.trim().length < 1) {
                msg("알림", "리뷰 내용을 입력하세요.", "warning");
                document.getElementById('reviewCon').focus();
                return false;
            }
            return true;
        }

        function msg(title, message, type) {
            alert(message); // 기본 alert 사용 (알림 메시지를 위한 간단한 구현)
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>리뷰 수정</h1>

        <!-- 메시지 출력 -->
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>

        <!-- 수정 폼 -->
        <form action="/member/reviewModi" method="post" onsubmit="return validateForm();">
            <!-- 리뷰 번호 -->
            <input type="hidden" name="reviewNo" value="${reviewNo}">

            <!-- 리뷰 내용 -->
            <label for="reviewCon" class="input-title">리뷰 내용:</label>
            <textarea id="reviewCon" name="reviewCon" rows="8">${reviewContent}</textarea>

            <!-- 수정 버튼 -->
            <button type="submit" class="button-primary" style="background-color: #f40;">수정하기</button>
        </form>
    </div>
</body>
</html>