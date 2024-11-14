<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerReservation.jsp</title>
<style>
.tbl{
	margin: 0 auto;
}

.tbl-row {
	text-align: center;
}
</style>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section notice-list-wrap">
				<div class="page-title">${bookDate}</div>
				<div class="list-content">
					<table border="1" class="tbl hover">
						<tr>
							<th style="width: 20%">예약 시간</th>
							<th style="width: 20%">예약자 이름</th>
							<th style="width: 20%">전화번호</th>
							<th style="width: 20%">인원수</th>
							<th style="width: 20%">예약 취소</th>
						</tr>

						<c:forEach var="book" items="${bookInfo}">
							<tr class="tbl-row">
								<td>${book.bookTime}</td>
								<td>${book.memberName}</td>
								<td>${book.memberPhone}</td>
								<td>${book.bookCnt}</td>
								<td><button onClick='cancelBook("${book.memberNo}")'>취소</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
		function cancelBook(memberNo) {
			console.log(memberNo);
		}
	</script>
</body>
</html>