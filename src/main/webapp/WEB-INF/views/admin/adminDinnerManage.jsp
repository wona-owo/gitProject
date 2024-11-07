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
						<button type="button" id="firstname" class="btn-primary">매장이름순 정렬</button>
						<button type="button" id="firstCon" class="btn-primary">승인여부 순 정렬</button>
						<input type="text" placeholder="매장 이름 검색" id="searchInput">
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
						<tr onclick="location.href='/dinnerDetail?dinnerNo=${d.dinnerNo}'"
							style="cursor: pointer;">
							<td class="dinnerNo">${d.dinnerNo}</td>
							<td>${d.dinnerName}</td>
							<td>${d.dinnerAddr}</td>
							<td>${d.dinnerEmail}</td>
							<td>${d.dinnerPhone}</td>
							<td>${d.dinnerConfirm}</td>
						</tr>
					</c:forEach>
				</table>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
	$("#firstname").on("click", function(){
		
		
		
		
	}
	</script>
	
	
	
	
</body>
</html>