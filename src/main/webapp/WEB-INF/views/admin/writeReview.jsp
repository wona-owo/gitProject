<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="/resources/css/default.css">
<style>
/* 기본 설정 */
* {
	box-sizing: border-box; /* 모든 요소에 border-box 적용 */
}

/* 모달 배경 스타일 */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.6);
	display: flex;
	align-items: center;
	justify-content: center;
}

/* 모달 컨텐츠 스타일 */
.modal-content {
	width: 90%;
	max-width: 600px;
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	position: relative;
	text-align: center;
}

/* 닫기 버튼 스타일 */
.close-btn {
	position: absolute;
	top: 15px;
	right: 15px;
	font-size: 20px;
	cursor: pointer;
	color: #f40;
	font-weight: bold;
	transition: color 0.3s;
}

.close-btn:hover {
	color: #c30;
}

/* 버튼과 폼 스타일 */
.form-group {
	margin-bottom: 20px;
	text-align: left;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	color: #333;
}

.form-group input[type="text"], .form-group textarea {
	width: 100%;
	padding: 15px;
	font-size: 16px;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-sizing: border-box; /* 입력 필드 경계 포함 너비 조정 */
	transition: border-color 0.3s;
}

.form-group input[type="text"]:focus, .form-group textarea:focus {
	border-color: #f40;
}

.submit-btn {
	background-color: #f40;
	color: #fff;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: bold;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100%;
	transition: background-color 0.3s;
}

.submit-btn:hover {
	background-color: #c30;
}

/* 리뷰 작성 버튼 스타일 */
#openModalBtn {
	background-color: #f40;
	color: #fff;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
	margin: 20px auto;
	display: block;
}

#openModalBtn:hover {
	background-color: #c30;
}
</style>

</head>
<body>
	<!-- 리뷰 작성 버튼 -->
	<button id="openModalBtn">리뷰 작성하기</button>

	<!-- 모달 팝업 HTML 구조 -->
	<div id="reviewModal" class="modal">
		<div class="modal-content">
			<span class="close-btn">&times;</span>
			<h2>리뷰 작성하기</h2>
			<form action="/submitReview" method="post" id="reviewForm"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">식당명</label> <input type="text" id="title"
						name="title" placeholder="리뷰 제목을 입력하세요" required>
				</div>

				<div class="form-group">
					<label for="content">리뷰 내용</label>
					<textarea id="content" name="content" placeholder="리뷰 내용을 작성하세요"
						required></textarea>
				</div>

				<div class="form-group">
					<label for="photos">사진 첨부 (최대 3장)</label> <input type="file"
						id="photos" name="photos" accept="image/*" multiple> <small>최대
						3개의 이미지를 선택할 수 있습니다.</small>
				</div>

				<button type="submit" class="submit-btn">작성하기</button>
			</form>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
			// 모달 열기
			$("#openModalBtn").click(function() {
				$("#reviewModal").fadeIn();
			});

			// 모달 닫기 버튼
			$(".close-btn").click(function() {
				$("#reviewModal").fadeOut();
			});

			// 모달 외부를 클릭하면 닫기
			$(window).click(function(event) {
				if (event.target == document.getElementById("reviewModal")) {
					$("#reviewModal").fadeOut();
				}
			});
		});
	</script>

</body>
</html>
