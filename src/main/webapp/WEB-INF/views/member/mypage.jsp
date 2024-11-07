<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="/resources/css/default.css" />
<style>
    /* 전체 컨텐츠 너비 설정 */
    .content {
        max-width: 450px;
        margin: 0 auto;
        padding: 20px;
        text-align: center;
    }

    /* 동그라미 버튼 스타일 */
    .circle-button {
        width: 80px;
        height: 80px;
        border-radius: 50%;
        background-color: #f40;
        color: #fff;
        font-size: 12px;
        font-weight: bold;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        text-decoration: none;
        margin: 10px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .circle-button:hover {
        background-color: #c30;
    }

    /* 버튼 그룹 스타일 */
    .button-group {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 15px;
        padding: 20px 0;
    }
</style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    
    <div class="wrap">
        <div class="content">

            <!-- 동그라미 버튼 그룹 -->
            <div class="button-group">
                <a href="/edit-info.html" class="circle-button">
                    <span>정보 수정</span>
                   
                </a>
                <a href="/reservations.html" class="circle-button">
                    <span>예약 확인</span>
                </a>
                <a href="#reviews" class="circle-button" onclick="openTab('reviews', this)">
                    <span>리뷰 보기</span>
                    
                </a>
                <a href="#favorites" class="circle-button" onclick="openTab('favorites', this)">
                    <span>즐겨찾기 보기</span>    
                </a>
            </div>

        </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
        function openTab(tabId, element) {
            // 모든 탭에서 active 클래스 제거
            document.querySelectorAll('.tab-item').forEach(tab => tab.classList.remove('active'));
            // 현재 클릭된 탭에 active 클래스 추가
            element.classList.add('active');

            // 모든 콘텐츠에서 active 클래스 제거
            document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
            // 선택한 콘텐츠에만 active 클래스 추가
            document.getElementById(tabId).classList.add('active');
        }
    </script>
</body>
</html>
