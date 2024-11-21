<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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

.card img {
	width: 100%;
	height: auto;
	max-height: 80px;
	object-fit: cover;
	border-radius: 5px;
	margin-bottom: 8px;
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

.card-info .cuisine-type {
	color: #aaa;
	font-size: 0.9em;
}

/* 즐겨찾기 아이콘 스타일 */
.favorite-icon {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 1.5em; /* 아이콘 크기 증가 */
	color: #ff4400; /* 기본 주황색 */
	cursor: pointer;
	transition: color 0.3s;
}

.favorite-icon.inactive {
	color: gray; /* 해제 상태의 회색 */
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="page-title">즐겨찾는 식당</div>
	<div class="card-container">
		<c:forEach var="dinner" items="${likeList}">

			<c:choose>
				<c:when test="${not empty dinner.photoList}">
					<c:set var="photoPath" value="${dinner.photoList[0].photoPath}" />
				</c:when>
				<c:otherwise>
					<c:set var="photoPath" value="default.jpg" />
				</c:otherwise>
			</c:choose>

			<div class="card"
				onclick="openDetailPage('${dinner.dinnerNo}', event)">
				<img src="/resources/photos/${photoPath}"
					alt="${dinner.dinnerName} 이미지">
				<div class="card-info">
					<h3>${dinner.dinnerName}</h3>
					<p>${dinner.dinnerAddr}</p>
					<p class="cuisine-type">${dinner.foodNation}</p>
				</div>
				<span class="favorite-icon active"
					data-dinner-no="${dinner.dinnerNo}"> <i
					class="fa-solid fa-map-pin"></i>
				</span>
			</div>
		</c:forEach>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		$(document).ready(
				function() {
					// 즐겨찾기 아이콘 클릭 이벤트 설정
					$(".favorite-icon").click(
							function(event) {
								event.stopPropagation(); // 상위 카드로 클릭 이벤트 전파 방지
								const iconElement = $(this); // 클릭된 아이콘 요소 참조						
								const dinnerNo = iconElement
										.attr("data-dinner-no"); // 직접 속성으로 접근

								// AJAX 요청 보내기
								$.ajax({
									url : "/member/delLike", // 요청할 서버 URL
									type : "POST",
									data : {
										"dinnerNo" : dinnerNo
									},
									dataType : "json",
									success : function(res) { // 요청 성공 시
										iconElement.addClass("inactive")
												.removeClass("active"); // 버튼을 회색으로 변경
										console.log("즐겨찾기 삭제 성공:", res);

										//새로고침
										window.location.reload();
									},
									error : function(request, status, error) {
										alert("code:" + request.status + "\n"
												+ "message:"
												+ request.responseText + "\n"
												+ "error:" + error);

									}
								});
							});
				});

		// 상세 페이지 열기
		function openDetailPage(dinnerNo, event) {
			if (event.target.closest('.favorite-button')) {
				return;
			}
			window.location.href = "/dinner/dinnerDetail?dinnerNo=" + dinnerNo;
		}
	</script>
</body>
</html>
