<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerReservation.jsp</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<main class="content">
			<section class="section notice-list-wrap">
				<div class="page-title">여기다가 날짜</div>
				<div class="list-content">

					<table class="tbl hover">
						<tr>
							<th style="width: 20%">예약 시간</th>
							<th style="width: 20%">예약자 이름</th>
							<th style="width: 20%">전화번호</th>
							<th style="width: 20%">인원수</th>
							<th style="width: 20%">예약 취소</th>
						</tr>

						<c:forEach var="book" items="${bookInfo}">
							<tr>
								<td>${book.bookTime}</td>
								<td>이름</td>
								<td>전화번호</td>
								<td>${book.bookCnt}</td>
								<td><button onClick="cancelBook()">취소</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</section>
		</main>

		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>
	function cancelBook(){
		console.log("poop");
	}

	$.ajax({

	})
	</script>

</body>
</html>