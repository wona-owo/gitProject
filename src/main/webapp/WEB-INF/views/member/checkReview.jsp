<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성 리뷰</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
	width: 600px; /* 카드 폭 조정 */
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
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="page-title">내 리뷰 확인</div>
	<div class="card-container">
		<c:choose>
			<c:when test="${not empty reviewList}">
				<c:forEach var="review" items="${reviewList}">
					<div class="card" onclick="location.href='dinnerDetail.jsp?name=${review.dinnerName}'">
						<div class="card-info">
							<h3>${review.dinnerName}</h3>
							<p>${review.reviewDate}</p>
						</div>
						<img src="${review.reviewImg}" alt="식당 이미지">
						<span class="del-icon active" data-review-no="${review.reviewNo}">
							<i class="fa-solid fa-x"></i>
						</span>
						<span class="del-icon active" onclick="location.href='reviewUpdate.jsp?no=${review.reviewNo}">
							<i class="fa-regular fa-pen"></i>
						</span>
						<div class="contents">
							<h5>${review.reviewContent}</h5>
						</div>
					</div>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
			// 삭제 아이콘 클릭 이벤트 설정
			$(".del-icon").click(function(event) {
				event.stopPropagation(); // 상위 카드로 클릭 이벤트 전파 방지
				const iconElement = $(this); // 클릭된 아이콘 요소 참조						
				const reviewNo = iconElement.attr("data-review-no"); // 데이터 속성 사용

				// AJAX 요청 보내기
				$.ajax({
					url : "/member/delReview", // 요청할 서버 URL
					type : "POST",
					data : {						
						"reviewNo" : reviewNo
					},
					dataType : "json",
					success : function(res) { // 요청 성공 시
						iconElement.addClass("inactive").removeClass("active"); // x 클릭하면 삭제
						console.log("리뷰 삭제 성공:", res);

						// 새로고침
						window.location.reload();
					},
					error: function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					}
				});
			});
		});
	</script>
</body>
</html>
