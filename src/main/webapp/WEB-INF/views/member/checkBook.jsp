<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성 리뷰</title>
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
	justify-content: center; /* 중앙 정렬 */
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	gap: 20px;
}

/* 카드 스타일 */
.card {
	border: 1px solid #ddd;
	border-radius: 10px;
	padding: 15px;
	width: 300px; /* 카드 폭 조정 */
	height: 200px; /* 카드 높이 조정 */
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
	max-height: 100px;
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
	color: #aaa;
}

/* 삭제 아이콘 스타일 */
.del-icon {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 1.5em;
	color: gray;
	cursor: pointer;
	transition: color 0.3s;
}
</style>
</head>
<body>

	<%--예약확인에 맞추어 수정 --%>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="page-title">예약 확인</div>
	<div class="card-container">
		<c:choose>
			<c:when test="${not empty bookList}">
				<c:forEach var="book" items="${bookList}">
					<div class="card"
						onclick="location.href='${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/dinner/dinnerDetail?dinnerNo=${book.dinnerNo}'">
						<!-- 식당 사진 -->
						<img
							src="${pageContext.request.contextPath}/resources/images/${book.dinnerNo}.jpg"
							alt="식당 이미지">
						<div class="card-info">
							<h2 class="restaurant-name">${book.dinnerName}</h2>
							<!-- 날짜만 표시 -->
							<p class="reservation-date">${book.bookDate.split(" ")[0]}</p>
							<!-- 시간 표시 -->
							<p class="reservation-time">${book.bookTime.substring(0, 2)}:${book.bookTime.substring(2, 4)}</p>
							<!-- 예약 인원 -->
							<p class="reservation-count">총 ${book.bookCnt}명</p>
						</div>
						<span class="del-icon active" data-book-no="${book.bookNo}">
							<i class="fa-solid fa-x"></i>
						</span>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>예약 내역이 없습니다.</p>
			</c:otherwise>
		</c:choose>
	</div>




	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							// 삭제 아이콘 클릭 이벤트 설정
							$(".del-icon")
									.click(
											function(event) {
												event.stopPropagation(); // 상위 카드로 클릭 이벤트 전파 방지
												const iconElement = $(this); // 클릭된 아이콘 요소 참조						
												const bookNo = iconElement
														.attr("data-book-no"); // 데이터 속성 사용

												// 예약 취소 확인 메시지
												if (confirm("예약을 취소하시겠습니까?")) { // 확인 버튼을 누르면 AJAX 요청 실행
													// AJAX 요청 보내기
													$
															.ajax({
																url : "/member/delBook", // 요청할 서버 URL
																type : "POST",
																data : {
																	"bookNo" : bookNo
																// 변수명 일치
																},
																dataType : "json",
																success : function(
																		res) { // 요청 성공 시
																	if (res.status === "success") {
																		alert("예약이 취소되었습니다.");
																		window.location
																				.reload(); // 페이지 새로고침
																	} else {
																		alert(res.message
																				|| "예약 취소에 실패했습니다.");
																	}
																},
																error : function(
																		request,
																		status,
																		error) {
																	alert("code:"
																			+ request.status
																			+ "\n"
																			+ "message:"
																			+ request.responseText
																			+ "\n"
																			+ "error:"
																			+ error);
																}
															});
												} else {
													// 취소 버튼을 누르면 아무 작업도 하지 않음
													console
															.log("예약 취소가 중단되었습니다.");
												}
											});
						});
		
		document.addEventListener("DOMContentLoaded", () => {
		    // 날짜 포맷팅
		    document.querySelectorAll(".reservation-date").forEach(element => {
		        const rawDate = element.getAttribute("data-date"); // 원본 데이터 (yyyy-MM-dd HH:mm:ss)
		        if (rawDate) {
		            const formattedDate = rawDate.split(" ")[0]; // 시간 제거
		            element.textContent = formattedDate; // 표시되는 내용 변경
		        }
		    });

		    // 시간 포맷팅
		    document.querySelectorAll(".reservation-time").forEach(element => {
		        const rawTime = element.getAttribute("data-time"); // 원본 데이터 (HHmm)
		        if (rawTime && rawTime.length === 4) {
		            const formattedTime = `${rawTime.substring(0, 2)}:${rawTime.substring(2, 4)}`; // HH:mm 형식
		            element.textContent = formattedTime; // 표시되는 내용 변경
		        }
		    });
		});
	</script>
</body>
</html>
