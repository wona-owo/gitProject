<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div>
		<main class="content">
			<section class="section notice-list-wrap">
				<div class="page-title">매장 관리 페이지</div>
				<c:if test="${not empty loginMember}">
					<div class="search-section">
						<input type="text" placeholder="검색할 매장명을 입력해주세요 " id="searchInput">
						<button onclick="/">검색</button>
					</div>
				</c:if>



				<div class="list-content">
					<table class="tbl hover">
						<tr>
							<th style="width: 5%">식당코드</th>
							<th style="width: 30%">매장명</th>
							<th style="width: 15%">매장 주소</th>
							<th style="width: 5%">담당자 이름</th>
							<th style="width: 10%">담당자 연락처</th>
							<th style="width: 10%">매장 전화번호</th>
							<th style="width: 10%">승인 여부</th>
						</tr>
						<c:forEach var="notice" items="${noticeList}">
							<tr>
								<td>${notice.noticeNo}</td>
								<td><a href='/notice/view?noticeNo=${notice.noticeNo}'>${notice.noticeTitle}</a></td>
								<td>${notice.noticeWriter}</td>
								<td>${notice.noticeDate}</td>
								<td>${notice.readCount}</td>
							</tr>

						</c:forEach>
					</table>
				</div>
				<div id="pageNavi" style="margin-top: 20px">${pageNavi}</div>
			</section>
		</main>
		</div>
</body>
</html>