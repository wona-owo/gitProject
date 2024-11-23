<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

#pwSearchPopup {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 300px;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	border-radius: 20px;
}

.popup-content {
	text-align: center;
}

.memberId {
	margin-left: 16px;
}

.input-wrap {
	padding-bottom: 10px;
}

button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	background-color: #f40;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<form method="post">
		<!-- 팝업 HTML -->
		<div id="pwSearchPopup">
			<div class="popup-content">
				<h3>비밀번호 찾기</h3>
				<div class="input-wrap">
					<label for="memberId" class="memberId">아이디 입력</label> <input
						type="text" id="popupMemberId" placeholder="아이디를 입력해주세요" />
				</div>
				<div class="input-wrap">
					<label for="memberPhone" class="memberPhone">전화번호 입력</label> <input
						type="text" id="popupMemberPhone" placeholder="전화번호를 입력해주세요" />
				</div>
				<div class="search-button">
					<button type="button" onclick="searchPw()" class="search">비밀번호
						찾기</button>
				</div>
			</div>
		</div>
	</form>
	<script>
		function showPwSearchPopup() {
			document.getElementById("pwSearchPopup").style.display = "block";
		}

		// 비밀번호 찾기 함수
		function searchPw() {
			var memberId = document.getElementById("popupMemberId").value;
			var memberPhone = document.getElementById("popupMemberPhone").value;

			if (!memberId || !memberPhone) {
				alert("아이디와 전화번호를 입력해 주세요.");
				return;
			}

			$.ajax({
				url : '/member/searchPw', // 서블릿 URL
				type : 'POST',
				data : {
					memberId : memberId,
					memberPhone : memberPhone
				},
				success : function(response) {
					if (response) {
						alert("임시 비밀번호가 이메일로 전송되었습니다");
						closePopup(); // 팝업 닫기
					} else {
						alert("일치하는 회원이 없습니다.");
					}
				},
				error : function() {
					alert("서버에 오류가 발생했습니다.");
				}
			});
		}
	</script>
</body>
</html>
