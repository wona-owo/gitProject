<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 선택</title>
<style>
/* 전체 레이아웃 */
.wrap {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    position: relative;
}

/* 메인 콘텐츠 */
.main-content {
    flex: 1;
    display: flex;
    justify-content: space-evenly;
    align-items: flex-start; /* 위로 정렬 */
    padding: 10px 20px; /* 상단 패딩을 줄임 */
    gap: 0px; /* 두 박스 사이 간격 */
    margin-top: -20px; /* 콘텐츠 전체를 위로 당김 */
}

/* 선택 영역 스타일 */
.option-container {
    width: 35%;
    height: 350px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #fff; /* 박스 배경은 흰색 */
    border: 2px solid #f40; /* 주 컬러 테두리 */
    border-radius: 15px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
    text-align: center;
    transition: transform 0.3s ease, box-shadow 0.3s ease, border-color 0.3s ease;
    position: relative; /* 아이콘 배치를 위해 */
}

.option-container:hover {
    transform: translateY(-8px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
    border-color: #c30; /* 호버 시 더 진한 주 컬러 */
}

/* 제목 스타일 */
.option-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
    color: #333;
}

/* 설명 텍스트 */
.option-desc {
    font-size: 16px;
    color: #666;
    margin-bottom: 20px;
    padding: 0 20px;
    line-height: 1.6;
}

/* 버튼 스타일 */
.btn-primary {
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    color: #fff;
    background-color: #f40;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
}

/* 페이지 제목 스타일 */
.page-title {
    font-size: 28px;
    font-weight: bold;
    text-align: center;
    margin-top: 40px;
    margin-bottom: 20px;
    color: #333;
}

/* 페이지 설명 */
.page-desc {
    font-size: 18px;
    text-align: center;
    color: #555;
    margin-bottom: 40px;
}
</style>
</head>
<body>
    <!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    
    <!-- 메인 콘텐츠 -->
    <div class="wrap">
        <div class="page-title">회원가입</div>
        <p class="page-desc">회원가입 유형을 선택하세요. 원하는 유형을 클릭하여 시작하세요.</p>
        <div class="main-content">
            <!-- 일반 회원 등록 -->
            <div class="option-container">
                <div class="option-title">일반 회원 가입</div>
                <p class="option-desc">일반 사용자를 위한 회원가입을 진행하세요. 다양한 혜택과 서비스를 이용할 수 있습니다.</p>
                <a href="/member/joinFrm" class="btn-primary">회원가입</a>
            </div>
            
            <!-- 매장 등록 -->
            <div class="option-container">
                <div class="option-title">매장 등록</div>
                <p class="option-desc">사업자를 위한 매장 등록을 진행하세요. 고객과의 연결을 쉽게 만들어 드립니다.</p>
                <a href="/dinnerJoinFrm" class="btn-primary">매장 등록</a>
            </div>
        </div>
    </div>
    
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
