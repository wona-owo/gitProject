<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="/resources/css/default.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

/* 버튼 공통 스타일 */
button {
	padding: 12px 20px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

/* 리뷰 작성 버튼 스타일 */
#openModalBtn {
	background-color: #f40;
	color: #fff;
	margin: 20px auto;
	display: block;
}

#openModalBtn:hover {
	background-color: #c30;
}

/* 제출 버튼 스타일 */
.submit-btn {
	background-color: #f40;
	color: #fff;
	width: 100%;
}

.submit-btn:hover {
	background-color: #c30;
}

/* 입력 폼 스타일 */
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
	box-sizing: border-box;
	transition: border-color 0.3s;
}

textarea {
	height: 200px;
	resize: none;
}

#charCount {
	display: block;
	margin-top: 5px;
	font-size: 14px;
	color: #555;
}
</style>
</head>
<body>
	<!-- 리뷰 작성 버튼 -->
	<button id="openModalBtn">리뷰 작성하기</button>

	<!-- 모달 팝업 -->
	<div id="reviewModal" class="modal">
		<div class="modal-content">
			<span class="close-btn">&times;</span>
			<h2>리뷰 작성하기</h2>
			<form action="/submitReview" method="post" id="reviewForm" enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">식당명</label>
					<input type="text" id="title" name="title" placeholder="식당명을 입력하세요" required>
				</div>

				<div class="form-group">
					<label for="content">리뷰 내용</label>
					<textarea id="content" name="content" placeholder="리뷰 내용을 작성하세요" required></textarea>
					<small id="charCount">현재 0자 / 최대 666자까지 입력 가능합니다.</small>
				</div>

				<div class="form-group">
					<label for="photos">사진 첨부 (최대 3장)</label>
					<input type="file" id="photos" name="photos" accept="image/*" multiple>
					<small>최대 3개의 이미지를 선택할 수 있습니다.</small>
				</div>

				<button type="submit" class="submit-btn">작성하기</button>
			</form>
		</div>
	</div>

	
	<script>
	$(document).ready(function () {
	    // 모달 열기
	    $("#openModalBtn").click(function () {
	        $("#reviewModal").fadeIn();
	    });

	    // 모달 닫기
	    $(".close-btn").click(function () {
	        $("#reviewModal").fadeOut();
	    });

	    // ESC 키로 모달 닫기
	    $(document).on("keydown", function (event) {
	        if (event.key === "Escape") {
	            $("#reviewModal").fadeOut();
	        }
	    });

	    // 글자 수 제한
	    const maxChars = 665;
	    const charCountElement = $("#charCount"); // 글자 수 표시 엘리먼트
	    const contentInput = $("#content"); // 입력 필드

	    // 글자 수 업데이트 함수
	    function updateCharCount() {
	        const content = contentInput.val(); // 입력된 텍스트
	        const currentLength = content.length;

	        // 글자 수 초과 시 잘라내기
	        if (currentLength > maxChars) {
	            contentInput.val(content.substring(0, maxChars));
	        }

	        // 글자 수 업데이트
	        const updatedText = "현재 " + currentLength + "자 / 최대 666자까지 입력 가능합니다.";
	        charCountElement.text(updatedText);

	    }

	    // 이벤트 리스너 추가
	    contentInput.on("input", updateCharCount);

	    // 파일 첨부 제한
	    $("#photos").on("change", function () {
	        if (this.files.length > 3) {
	            alert("최대 3개의 이미지만 업로드할 수 있습니다.");
	            $(this).val("");
	        }
	    });

	    // 폼 유효성 검사
	    $("#reviewForm").on("submit", function (event) {
	        const title = $("#title").val().trim();
	        const content = contentInput.val().trim();
	        if (!title || !content) {
	            alert("모든 필드를 입력해주세요.");
	            event.preventDefault();
	        }
	    });
	});

	</script>
</body>
</html>
