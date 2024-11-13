<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css" />
<link rel="stylesheet" href="/resources/css/default.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 상세 페이지</title>
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
	margin: 0 auto; /* 가운데 정렬 */
	border-collapse: collapse;
	font-size: 1em;
	border-radius: 10px; /* 모서리 둥글게 */
	overflow: hidden; /* 둥근 모서리에 맞춰 테두리가 잘리도록 설정 */
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
}

.tbl th, .tbl td {
	padding: 10px;
	border: 1px solid #ddd; /* 회색 테두리 */
}

.tbl th {
	background-color: #f40; /* 주황 배경 */
	color: #fff; /* 글자 흰색  */
	width: 30%;
	text-align: center;
}

.tbl td {
	background-color: #fff; /* 연한 회색 배경 */
	text-align: center;
}

/* 전체 컨테이너 스타일 */
.mypage-container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}
/* 버튼 감싸는 div 스타일 */
.mypage-btn {
	text-align: center; /* 버튼 가운데 정렬 */
	margin-top: 20px;
}

/* 버튼 스타일 */
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
	background-color: #2f4fa8; /* 버튼 호버 시 조금 어두운 색 */
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<main class="content mypage-container">
			<section class="section mypage-wrap">
				<div class="page-title">매장 정보페이지</div>
				<table class="tbl">
					<tr>
						<th width="30%">식당 코드</th>
						<td width="70%" class="left">${dinner.dinnerNo}</td>
					</tr>
					<tr>
						<th><label for="dinnerName"> 식당이름</label></th>
						<td width="70%" class="left">${dinner.dinnerName}</td>
					</tr>
					<tr>
						<th width="30%">식당 주소</th>
						<td width="70%" class="left">${dinner.dinnerAddr}</td>
					</tr>
					<tr>
						<th width="30%">오픈 시간</th>
						<td width="70%" class="left">${dinner.dinnerOpen}</td>
					</tr>
					<tr>
						<th width="30%">마감 시간</th>
						<td width="70%" class="left">${dinner.dinnerClose}</td>
					</tr>
					<tr>
						<th width="30%">식당 번호</th>
						<td width="70%" class="left">${dinner.dinnerPhone}</td>
					</tr>
					<tr>
						<th width="30%">식당 이메일</th>
						<td width="70%" class="left">${dinner.dinnerEmail}</td>
					</tr>
					<tr>
						<th width="30%">주차여부</th>
						<td width="70%" class="left">${dinner.dinnerParking}</td>
					</tr>
					<tr>
						<th width="30%">수용인원</th>
						<td width="70%" class="left">${dinner.dinnerMaxPerson}</td>
					</tr>
					<tr>
						<th width="30%">사업자 등록번호</th>
						<td width="70%" class="left">${dinner.busiNo}</td>
					</tr>
					<tr>
						<th width="30%">매장 ID</th>
						<td width="70%" class="left">${dinner.dinnerId}</td>
					</tr>
					<tr>
						<th width="30%">승인 여부</th>
						<td width="70%" class="left">${dinner.dinnerConfirm}</td>
					</tr>

				</table>

				<div class="mypage-btn">
					<!-- onclick에서 JavaScript 함수 호출 -->
					<button type="button"
						onclick="goToDetailPage('${dinner.dinnerNo}')"
						class="btn-primary lg">매장 상세페이지 이동</button>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
    function goToDetailPage(dinnerNo) {
        // 매장 상세 페이지로 이동하는 URL을 구성합니다.
        const url = `/admin/goToDetailFrm?dinnerNo=${dinnerNo}`;
        window.location.href = url;
    }
</script>

</body>
</html>