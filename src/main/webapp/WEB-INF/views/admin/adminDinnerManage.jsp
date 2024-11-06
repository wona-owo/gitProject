<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/diner_admin_member.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장관리 페이지</title>
<style>
.notice-list-wrap {
	width: 1200px;
	margin: 0 auto;
}

.list-content {
	height: 500px;
}

.list-header {
	padding: 15px 0px;
	text-align: right;
}

.search-section {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	width: 80%;
	margin: 20px 0;
}

.search-section input[type="text"] {
	width: 50%;
	padding: 20px;
	font-size: 16px;
	border-radius: 5px;
	border: 1px solid #CCC;
}

.search-section button {
	margin-left: 10px;
	padding: 10px;
	font-size: 16px;
	background-color: #A5C9FF;
	color: #000;
	border: none;
	border-radius: 5px;
}
</style>

</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section admin-wrap">
				<div class="page-title">매장 관리 페이지</div>
				<div class="table-container">
					<div class="search-box">
						<select id="filterSelect">
							<option value="">매장 이름정렬 순</option>
							<option value="approved">매장 승인 순</option>
						</select> <input type="text" placeholder="매장 이름 검색" id="searchInput">
						<button onclick="searchDinner()">검색</button>
					</div>
				</div>

				<table class="tbl tbl_hover">
					<tr>
						<th style="width: 5%">식당코드</th>
						<th style="width: 10%">식당이름</th>
						<th style="width: 15%">주소</th>
						<th style="width: 10%">이메일</th>
						<th style="width: 10%">매장 번호</th>
						<th style="width: 5%">승인여부</th>
					</tr>

					<c:forEach var="d" items="${dinnerList}">
						<tr>
							<td class="dinnerNo">${d.dinnerNo}</td>
							<td>${d.dinnerName}</td>
							<td>${d.dinnerAddr}</td>
							<td>${d.dinnerEmail}</td>
							<td>${d.dinnerPhone}</td>
							<td>${d.dinnerConfirm}</td>
						</tr>
					</c:forEach>
				</table>

				<!-- 페이지 넘버 생성 -->
				<div class="pagination">
					<c:forEach var="i" begin="1" end="${totalPages}" step="5">
						<c:choose>
							<c:when test="${currentPage >= i && currentPage < i + 5}">
								<a href="?page=${i}" class="active">${i}</a>
							</c:when>
							<c:otherwise>
								<a href="?page=${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>