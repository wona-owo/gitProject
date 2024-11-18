<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장관리 페이지</title>
<link rel="stylesheet" href="/resources/css/dinner_admin_member.css" />
<style>
.pagination {
	display: flex;
	justify-content: center; /* 중앙 정렬 */
	margin: 20px 0;
}

.pagination a {
	padding: 8px 12px;
	margin: 0 4px;
	border: 1px solid #ddd;
	color: #333;
	text-decoration: none;
	border-radius: 5px;
}

.pagination a.active {
	background-color: #f40;
	color: #fff;
	border-color: #f40;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
</head>
<body>

	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section admin-wrap">
				<div class="page-title">매장 관리 페이지</div>
				<div class="table-container">
					<div class="search-box">
						<form action="/admin/dinner" method="get">
							<input type="hidden" name="action" value="search"> <input
								type="text" name="dinnerName" placeholder="매장 이름 검색">
							<button type="submit">검색</button>
						</form>

						<!-- 매장 이름 순 정렬 버튼 -->
						<button type="button" id="firstname" class="btn-primary"
							onclick="toggleSortByName()">매장이름순 정렬</button>
						<!-- 승인 여부 필터링 버튼 -->
						<button type="button" id="firstCon" class="btn-primary"
							onclick="filterByApproval()">승인여부 순 정렬</button>

					</div>

					<table class="tbl tbl_hover">
						<tr>
							<th style="width: 5%">식당코드</th>
							<th style="width: 10%">식당이름</th>
							<th style="width: 15%">주소</th>
							<th style="width: 10%">이메일</th>
							<th style="width: 10%">매장 번호</th>
							<th style="width: 8%">승인여부</th>
							<th style="width: 8%">수정 여부</th>
						</tr>

						<c:forEach var="d" items="${dinners}">
							<tr>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerNo}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerName}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerAddr}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerEmail}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerPhone}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">${d.dinnerConfirm}</a></td>
								<td><a
									href="/admin/adminDinnerManageFrm?dinnerNo=${d.dinnerNo}">X</a></td>
							</tr>
						</c:forEach>
					</table>

					<!-- 페이지 번호 -->
					<div class="pagination">
						<c:forEach var="i" begin="1" end="${totalPages}">
							<a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
						</c:forEach>
					</div>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>

		let sortOrder = 'asc'; // 초기 정렬 순서

		// 매장이름순 정렬 토글 함수
		function toggleSortByName() {
			sortOrder = sortOrder === 'asc' ? 'desc' : 'asc'; // 정렬 순서 변경
			const url = `/admin/dinner?action=sortByName&order=${sortOrder}`;
			window.location.href = url;
		}

		// 승인 여부 필터링 함수
		function filterByApproval() {
			const url = `/admin/dinner?action=filterByApproval&approved=y`;
			window.location.href = url;
		}
	</script>
</body>
</html>