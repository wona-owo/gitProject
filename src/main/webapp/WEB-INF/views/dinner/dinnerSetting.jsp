<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>매장 정보 수정</title>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css" />
<style>
/* CSS 코드는 이전과 동일 */
.page-title {
	font-size: 1.5em;
	text-align: center;
	margin-bottom: 20px;
}

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

.tbl td input[type="text"], .tbl td select, .tbl td input[type="number"]
	{
	width: 50%;
	border: none;
	border-bottom: 1px solid #ddd;
	padding: 5px;
	font-size: 1em;
	text-align: center;
}

.tbl td input[readonly] {
	background-color: #f9f9f9;
	cursor: not-allowed;
}

.tbl td input[type="text"]:focus, .tbl td input[type="number"]:focus {
	outline: none;
	border-bottom: 1px solid #3a5fcd;
}

.mypage-container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.mypage-btn {
	display: flex;
	justify-content: center; /* 가로 방향 중앙 정렬 */
	gap: 20px; /* 버튼 간격 설정 */
	margin-top: 20px;
}

.btn-primary {
	padding: 10px 20px;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 1em;
	cursor: pointer;
	background-color: #3a5fcd;
	text-align: center;
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
				<form id="updateForm"
				<%--
${pageContext.request.contextPath}
--%>
					action="/dinner/update"
					method="post" onsubmit="return validateAndConfirmUpdate();"
					enctype="multipart/form-data">
					<input type="hidden" name="dinnerId" value="${dinner.dinnerId}" />
					<input type="hidden" name="photoPath" value="${photoPath}" />
					<c:set var="dinnerNo" value="${dinner.dinnerNo}"/>
					<input type="hidden" name="dinnerNo" value="${dinnerNo}" />

					<table class="tbl">
						<tr>
							<th>식당 코드</th>
							<td>${dinner.dinnerNo}</td>
						</tr>
						<tr>
							<th>식당 이름</th>
							<td><input type="text" name="dinnerName"
								value="${dinner.dinnerName}" required /></td>
						</tr>
						<tr>
							<th>매장 ID</th>
							<td><input type="text" value="${dinner.dinnerId}" readonly />
								<input type="hidden" name="dinnerId" value="${dinner.dinnerId}" />
							</td>
						</tr>
						<tr>
							<th>식당 주소</th>
							<td><input type="text" name="dinnerAddr"
								value="${dinner.dinnerAddr}" required /></td>
						</tr>
						<%-- <daniel> --%>
						<tr>
							<th>식당 사진</th>
							<td><img src="/resources/photos/${photoPath}"
								style="height: 150px; width: auto;" alt="식당 사진이 없습니다"></td>
						</tr>
						<tr>
							<th>식당 사진 변경</th>
							<td><input type="file" name="uploadFile" /></td>
						</tr>
						<%-- </daniel> --%>
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
							<th>식당 전화번호</th>
							<td><input type="text" name="dinnerPhone"
								value="${dinner.dinnerPhone}" placeholder="010-1234-5678"
								required /></td>
						</tr>
						<tr>
							<th>식당 이메일</th>
							<td><input type="email" name="dinnerEmail"
								value="${dinner.dinnerEmail}" required /></td>
						</tr>
						<tr>
							<th>주차 여부</th>
							<td><select name="dinnerParking" required>
									<option value="y"
										${dinner.dinnerParking == 'y' ? 'selected' : ''}>Y</option>
									<option value="n"
										${dinner.dinnerParking == 'n' ? 'selected' : ''}>N</option>
							</select></td>
						</tr>
						<tr>
							<th>수용 인원</th>
							<td><input type="number" name="dinnerMaxPerson" min="1"
								value="${dinner.dinnerMaxPerson}" required /></td>
						</tr>
						<tr>
							<th>사업자 등록번호</th>
							<td><input type="text" name="busiNo"
								value="${dinner.busiNo}" required /></td>
						</tr>
						<tr>
							<th>승인 여부</th>
							<td><input type="text"
								value="${dinner.dinnerConfirm == 'Y' ? '승인' : '미승인'}" readonly /></td>
						</tr>
					</table>

					<div class="mypage-btn">
						<button type="button" onclick="confirmDelete('${dinnerNo}')" class="btn-primary">회원탈퇴</button>
						<button type="button"
							onclick="window.location.href='${pageContext.request.contextPath}/dinner/dinnerPageFrm?dinnerNo=${dinner.dinnerNo}'"
							class="btn-primary" style="margin-left: 30px;">매장 상세페이지
							이동</button>
						<button type="submit" class="btn-primary">정보 수정</button>
					</div>
				</form>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
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

        function confirmDelete(dinnerNo) {
        	console.log(dinnerNo);
            swal({
                title: "정말로 삭제하시겠습니까?",
                text: "이 작업은 되돌릴 수 없습니다!",
                icon: "warning",
                buttons: ["취소", "확인"],
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    window.location.href = "/dinner/deletefrm?dinnerNo=" + dinnerNo;
                }
            });
        }

	</script>
</body>
</html>