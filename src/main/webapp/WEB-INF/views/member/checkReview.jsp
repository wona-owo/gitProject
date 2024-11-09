<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성 리뷰</title>
<style>
/* 페이지 타이틀 스타일 */
.page-title {
    font-size: 1.8em;
    font-weight: bold;
    margin: 20px 0;
    text-align: left;
    padding-left: 20px;
}

/* 카드 컨테이너 중앙 정렬 및 너비 조정 */
.card-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    gap: 20px;
}

/* 카드 스타일 */
.card {
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 10px;
    width: 160px;
    height: 220px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #fff;
    transition: transform 0.3s;
    text-align: left;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    position: relative;
    text-decoration: none;
    color: inherit;
}

.card:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card-info {
    width: 100%;
    text-align: left;
    padding-left: 5px;
}

.card-info h3 {
    margin: 4px 0;
    font-size: 1.1em;
}

.card-info p {
    margin: 3px 0;
    font-size: 1em;
}

.card-info {
    color: #aaa;
    font-size: 0.9em;
}
</style>
</head>
<body>
	 <!-- 페이지 타이틀 -->
    <div class="page-title">내 리뷰 보기</div>
</body>
</html>