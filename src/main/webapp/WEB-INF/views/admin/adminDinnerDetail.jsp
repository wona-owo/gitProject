<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 상세 페이지</title>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css" />
<link rel="stylesheet" href="/resources/css/default.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
}

.btn-primary {
	padding: 10px 20px;
	background-color: #3a5fcd;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 1em;
	cursor: pointer;
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
				<div class="page-title">매장 정보페이지</div>
				<form id="updateForm" action="/dinner/update" method="post">
					<input type="hidden" name="dinnerNo" value="${dinner.dinnerNo}">
					<table class="tbl">
						<tr>
							<th>식당 코드</th>
							<td>${dinner.dinnerNo}</td>
						</tr>
						<tr>
							<th><label for="dinnerName">식당 이름</label></th>
							<td>${dinner.dinnerName}</td>
						</tr>
						<tr>
							<th>식당 주소</th>
							<td>${dinner.dinnerAddr}</td>
						</tr>
						<tr>
							<th>오픈 시간</th>
							<td>${dinner.dinnerOpen}</td>
						</tr>
						<tr>
							<th>마감 시간</th>
							<td>${dinner.dinnerClose}</td>
						</tr>
						<tr>
							<th>식당 번호</th>
							<td>${dinner.dinnerPhone}</td>
						</tr>
						<tr>
							<th>식당 이메일</th>
							<td>${dinner.dinnerEmail}</td>
						</tr>
						<tr>
							<th>주차 여부</th>
							<td>${dinner.dinnerParking}</td>
						</tr>
						<tr>
							<th>수용 인원</th>
							<td>${dinner.dinnerMaxPerson}</td>
						</tr>
						<tr>
							<th>사업자 등록번호</th>
							<td>${dinner.busiNo}</td>
						</tr>
						<tr>
							<th>매장 ID</th>
							<td>${dinner.dinnerId}</td>
						</tr>
						<tr>
							<th>승인 여부</th>
							<td>${dinner.dinnerConfirm}</td>
						</tr>
					</table>
					<div class="mypage-btn">
						<button type="button"
							onclick="window.location.href='/admin/DinnerPageFrm'"
							class="btn-primary">매장 상세페이지 이동</button>
					</div>
				</form>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
