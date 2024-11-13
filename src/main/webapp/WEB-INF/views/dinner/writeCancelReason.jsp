<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeCancelReason.jsp</title>
<style>
* {
	border: 1px solid black;
}

.notice-write-container {
	display: flex;
	justify-content: center;
	align-items: center;
}

.notice-write-wrap {
	width: 1000px;
}

.notice-write-wrap .input-item>input[type=text] {
	/* border-bottom: none; */
	padding: 0;
}

.notice-write-wrap .input-item>textarea {
	height: 300px;
}
</style>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content notice-write-container">
			<section class="section notice-write-wrap">
				<div class="page-title">예약 취소</div>
				<form action="/notice/write" method="post"
					enctype="multipart/form-data">
					<%-- 첨부파일 작성시 multipart/form-data 작성 필요 --%>
					<input type="hidden" name="bookNo" value=""> <input
						type="hidden" name="memberNo" value=""> <input
						type="hidden" name="memberEmail" value="">
					<%-- 현재 로그인한 사용자 == 작성자 --%>
					<table class="tbl">
						<tr>
							<th style="width: 20%;">제목</th>
							<td style="width: 80%;" colspan="3">
								<div class="input-item">
									<input type="text" name="noticeTitle">
								</div>
							</td>
						</tr>

						<tr>
							<th style="width: 20%;">작성자</th>
							<td style="width: 80%;"></td>
						</tr>

						<tr>
							<td colspan="4">
								<div class="input-item">
									<textarea name="noticeContent"></textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td colspan="4">
								<button type="submit" onClick="cancelReservation()"
									class="btn-primary lg">예약 취소</button>
								<button type="submit" onClick="cancelTheCancel()"
									class="btn-primary lg">취소를 취소</button>
							</td>
						</tr>
					</table>
				</form>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
		function cancelReservation() {

		}

		function cancelTheCancel() {

		}
	</script>
</body>
</html>