<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						onclick="location.href='dinnerDetail.jsp?name=${book.dinnerName}'">
						<div class="dinner-date">
							<h3>${book.dinnerName}</h3>
							<p>${book.bookDate}</p>
						</div>
						<%-- 식당 이미지 넣는가..? --%> <span
							class="del-icon active" data-review-no="${review.reviewNo}">
							<i class="fa-solid fa-x"></i>
						</span>
						<div class="time-ctn">
							<h3>${book.bookTime}</h3>
							<h3>총 ${book.bookCtn}명</h3>
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
            const bookNo = iconElement.attr("data-book-no"); // 데이터 속성 사용

            // 예약 취소 확인 메시지
            if (confirm("예약을 취소하시겠습니까?")) { // 확인 버튼을 누르면 AJAX 요청 실행
                // AJAX 요청 보내기
                $.ajax({
                    url: "/member/delBook", // 요청할 서버 URL
                    type: "POST",
                    data: {
                        "bookNo": bookNo // 변수명 일치
                    },
                    dataType: "json",
                    success: function(res) { // 요청 성공 시
                        iconElement.addClass("inactive").removeClass("active"); // x 클릭하면 삭제
                        console.log("예약 삭제 성공:", res);

                        // 새로고침
                        window.location.reload();
                    },
                    error: function(request, status, error) {
                        alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                });
            } else {
                // 취소 버튼을 누르면 아무 작업도 하지 않음
                console.log("예약 취소가 중단되었습니다.");
            }
        });
    });
</script>
</body>
</html>
