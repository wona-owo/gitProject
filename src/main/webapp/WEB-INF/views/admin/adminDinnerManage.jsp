<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				</div>
				<div class="pagination">
					<!-- 이전 버튼 -->
					<c:if test="${currentPage > 1}">
						<a href="?page=${currentPage - 1}&sort=${sort}&order=${order}"
							class="prev">&laquo; 이전</a>
					</c:if>

					<!-- 3단위 페이지 번호 표시 -->
					<c:set var="startPage"
						value="${currentPage - 1 > 0 ? currentPage - 1 : 1}" />
					<c:set var="endPage"
						value="${currentPage + 1 <= totalPages ? currentPage + 1 : totalPages}" />

					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="?page=${i}&sort=${sort}&order=${order}"
							class="${i == currentPage ? 'active' : ''}">${i}</a>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${currentPage < totalPages}">
						<a href="?page=${currentPage + 1}&sort=${sort}&order=${order}"
							class="next">다음 &raquo;</a>
					</c:if>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>