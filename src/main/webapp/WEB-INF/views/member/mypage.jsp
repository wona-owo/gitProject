<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    /* 마이페이지 타이틀 스타일 */
    .my-page-title {
        font-size: 28px;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .page-title::after {
        content: '';
        display: block;
        width: 50px;
        height: 2px;
        background-color: #hh4400;
        margin: 8px auto 20px;
    }

    /* 버튼 그룹 컨테이너 스타일 */
    .button-group-container {
        max-width: 800px; /* 폭을 줄임 */
        margin: 0 auto; /* 중앙 정렬 */
        border: 1px solid #ddd; /* 연한 회색 테두리 */
        border-radius: 8px;
        padding: 20px;
        background-color: #f9f9f9;
    }

    /* 동그라미 버튼 스타일 */
    .circle-button {
        width: 200px;
        height: 200px;
        border-radius: 50%;
        background-color: #f40;
        color: #fff;
        font-size: 25px;
        font-weight: bold;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
        margin: 10px;
    }
    .circle-button:hover {
        background-color: #c30;
    }

    /* 버튼 그룹 스타일 */
    .button-group {
        display: grid;
        grid-template-columns: repeat(2, 1fr); /* 2x2 그리드 */
        gap: 15px; /* 버튼 사이 간격 */
        justify-items: center;
    }
</style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="wrap">
        <div class="content">

            <!-- 타이틀 -->
            <div class="my-page-title">마이페이지</div>

            <!-- 동그라미 버튼 그룹 컨테이너 -->
            <div class="button-group-container">
                <div class="button-group">
                    <a href="/" class="circle-button">
                        <span>정보 수정</span>
                        
                    </a>
                    <a href="/member/ckBook" class="circle-button">
                        <span>예약 확인</span>
                        
                    </a>
                    <a href="/member/ckReview" class="circle-button">
                        <span>리뷰 보기</span>
                        
                    </a>
                    <a href="/member/ckLike" class="circle-button">
                        <span>즐겨찾기 보기</span>
                        
                    </a>
                </div>
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
