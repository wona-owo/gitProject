<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>매장 상세 페이지</title>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css" />
<link rel="stylesheet" href="/resources/css/default.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
<style>
/* 페이지 제목 스타일 */
.page-title {
	font-size: 1.5em;
	text-align: center;
	margin-bottom: 20px;
}

/* 테이블 스타일 */
.tbl {
	width: 80%;
	margin: 0 auto;
	border-collapse: collapse;
	font-size: 1em;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.tbl th, .tbl td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
}

.tbl th {
	background-color: #f40;
	color: #fff;
	width: 30%;
}

/* 입력 필드 스타일 */
.tbl td input[type="text"], .tbl td select {
	width: 50%;
	border: none;
	border-bottom: 1px solid #ddd;
	padding: 5px;
	font-size: 1em;
	text-align: center;
}

.tbl td input[type="text"]:focus, .tbl td select:focus {
	outline: none;
	border-bottom: 1px solid #3a5fcd;
}

/* 컨테이너 스타일 */
.mypage-container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

/* 버튼 스타일 */
.mypage-btn {
	text-align: center;
	margin-top: 20px;
	display: flex;
	justify-content: center;
	gap: 20px; /* 버튼 간격 설정 */
}

.btn-primary {
	padding: 10px 20px;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 1em;
	cursor: pointer;
	background-color: #3a5fcd;
}

.btn-primary:hover {
	background-color: #2f4fa8;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<main class="content mypage-container">
			<section class="section mypage-wrap">
				<div class="page-title">매장 정보 수정</div>

				<form id="updateForm" action="/adminDinner/update" method="post">
					<input type="hidden" name="dinnerNo" value="${dinner.dinnerNo}">
					<input type="hidden" name="dinnerId" value="${dinner.dinnerId}">

					<table class="tbl">
						<tr>
							<th>식당 코드</th>
							<td>${dinner.dinnerNo}</td>
						</tr>
						<tr>
							<th><label for="dinnerName">식당 이름</label></th>
							<td><input type="text" id="dinnerName" name="dinnerName"
								required maxlength="100" value="${dinner.dinnerName}"></td>
						</tr>
						<tr>
							<th>식당 주소</th>
							<td><input type="text" name="dinnerAddr"
								value="${dinner.dinnerAddr}"></td>
						</tr>
						<tr>
							<th>오픈 시간</th>
							<td><input type="text" name="dinnerOpen"
								value="${dinner.dinnerOpen}" placeholder="HH:mm" required /></td>
						</tr>
						<tr>
							<th>마감 시간</th>
							<td><input type="text" name="dinnerClose"
								value="${dinner.dinnerClose}" placeholder="HH:mm" required /></td>
						</tr>
						<tr>
							<th>식당 번호</th>
							<td><input type="text" id="dinnerPhone" name="dinnerPhone"
								placeholder="예: 010-1234-5678" value="${dinner.dinnerPhone}"></td>
						</tr>
						<tr>
							<th>식당 이메일</th>
							<td><input type="text" name="dinnerEmail"
								value="${dinner.dinnerEmail}"></td>
						</tr>
						<tr>
							<th>주차 여부</th>
							<td><select name="dinnerParking">
									<option value="y"
										${dinner.dinnerParking == 'y' ? 'selected' : ''}>Y</option>
									<option value="n"
										${dinner.dinnerParking == 'n' ? 'selected' : ''}>N</option>
							</select></td>
						</tr>
						<tr>
							<th>수용 인원</th>
							<td><input type="text" name="dinnerMaxPerson" required
								min="1" placeholder="최대 수용 인원을 입력하세요."
								value="${dinner.dinnerMaxPerson}"></td>
						</tr>
						<tr>
							<th>사업자 등록번호</th>
							<td><input type="text" name="busiNo"
								value="${dinner.busiNo}"></td>
						</tr>
						<tr>
							<th>승인 여부</th>
							<td><select name="dinnerConfirm">
									<option value="y"
										${dinner.dinnerConfirm == 'y' ? 'selected' : ''}>Y</option>
									<option value="n"
										${dinner.dinnerConfirm == 'n' ? 'selected' : ''}>N</option>
							</select></td>
						</tr>
					</table>

					<div class="mypage-btn">
						<button type="button" onclick="deleteDinner()" class="btn-primary">회원탈퇴</button>
						<button type="submit" class="btn-primary">정보 수정</button>
						<button type="button"
							onclick="window.location.href='/admin/DinnerPageFrm'"
							class="btn-primary">매장 상세페이지 이동</button>
					</div>
				</form>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		function deleteDinner() {
			swal({
				title : "매장 삭제",
				text : "정말 매장을 삭제하시겠습니까?",
				icon : "warning",
				buttons : {
					cancel : {
						text : "취소",
						value : false,
						visible : true,
						closeModal : true
					},
					confirm : {
						text : "삭제",
						value : true,
						visible : true,
						closeModal : true
					}
				}
			}).then(function(isConfirm) {
				if (isConfirm) {
					$('#updateForm').attr('action', '/dinner/delete');
					$('#updateForm').submit();
				}
			});
		}
		function validateAndConfirmUpdate() {
			const dinnerOpen = document
					.querySelector("input[name='dinnerOpen']").value;
			const dinnerClose = document
					.querySelector("input[name='dinnerClose']").value;
			const dinnerPhone = document
					.querySelector("input[name='dinnerPhone']").value;

			const timeExp = /^([01][0-9]|2[0-3]):[0-5][0-9]$/;
			const phoneExp = /^010-\d{3,4}-\d{4}$/;

			// 오픈 시간 유효성 검사
			if (!timeExp.test(dinnerOpenField.value)) {
				swal("알림", "오픈 시간 형식을 올바르게 입력하세요.", "warning");
				return false;
			}

			// 마감 시간 유효성 검사
			if (!timeExp.test(dinnerCloseField.value)) {
				swal("알림", "마감 시간 형식을 올바르게 입력하세요.", "warning");
				return false;
			}

			// 전화번호 유효성 검사
			if (!phoneExp.test(dinnerPhone)) {
				swal("알림", "전화번호 형식을 올바르게 입력하세요.", "warning");
				return false;
			}

			// ":" 제거 후 값 변환
			dinnerOpenField.value = dinnerOpenField.value.replace(":", "");
			dinnerCloseField.value = dinnerCloseField.value.replace(":", "");

			// 확인 메시지
			return confirm("매장 정보를 수정하시겠습니까?");
		}
	</script>
</body>
</html>