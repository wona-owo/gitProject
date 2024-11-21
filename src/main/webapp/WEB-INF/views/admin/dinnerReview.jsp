<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
/* 리뷰 리스트 컨테이너 */
.review-list {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	padding: 20px;
	justify-content: center;
}

/* 리뷰 아이템 */
.review-item {
	width: 300px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	transition: transform 0.3s, box-shadow 0.3s;
}

.review-item:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
}

/* 리뷰 헤더 스타일 */
.review-header {
	background-color: #f7f7f7;
	padding: 15px;
	border-bottom: 1px solid #ddd;
}

.review-header h2 {
	font-size: 20px;
	color: #333;
	margin: 0;
}

.review-header p {
	font-size: 14px;
	color: #666;
	margin: 5px 0;
}

/* 리뷰 이미지 */
.review-image {
	text-align: center;
	padding: 10px;
	border-bottom: 1px solid #ddd;
	cursor: pointer; /* 클릭 가능 */
}

.review-image img {
	max-width: 100%;
	height: auto;
	border-radius: 5px;
}

/* 리뷰 본문 */
.review-body {
	padding: 15px;
}

.review-content {
	font-size: 16px;
	line-height: 1.6;
	color: #333;
	margin-bottom: 10px;
}

/* 신고 횟수 */
.report-count {
	font-size: 14px;
	color: #999;
	text-align: right;
}

/* 신고 버튼 스타일 (크기 축소) */
.review-body button {
	display: block;
	width: 70%; /* 이전보다 더 작게 */
	margin: 10px auto 0;
	padding: 8px; /* 버튼 내부 여백 축소 */
	background-color: #ff4d4f;
	color: #fff;
	font-size: 12px; /* 글자 크기 축소 */
	font-weight: bold;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-align: center;
	transition: background-color 0.3s, transform 0.2s;
}

.review-body button:hover {
	background-color: #e03e3f;
	transform: translateY(-2px);
}

.review-body button:active {
	background-color: #d32f2f;
	transform: translateY(0);
}


/* "작성한 리뷰가 없습니다." 메시지 */
.no-reviews {
	font-size: 18px;
	color: #888;
	text-align: center;
	margin-top: 50px;
}
</style>

<c:choose>
	<c:when test="${not empty reviews}">
		<div class="review-list">
			<c:forEach var="review" items="${reviews}">
				<div class="review-item">
					<input type="hidden" name="review_no" value="${review.reviewNo}">
					<div class="review-header">
						<p class="review-date">작성일: ${review.reviewDate}</p>
						<p class="review-author">작성자: ${review.memberNick}</p>
						<p>신고당한 횟수:
							<c:set var="reportCount" value="0" />
							<c:forEach var="report" items="${reviewReports}">
								<c:if test="${review.reviewNo == report.reviewNo}">
									<c:set var="reportCount" value="${report.reportCount}" />
								</c:if>
							</c:forEach>
							${reportCount}
						</p>
					</div>
					<div class="review-body">
						<div class="review-image" onclick="reportReview('${review.reviewNo}')">
							<c:choose>
								<c:when test="${review.reviewImage != null}">
									<img
										src="data:image/jpeg;base64,${fn:escapeXml(review.reviewImage)}"
										alt="리뷰 이미지">
								</c:when>
								<c:otherwise>
									<img src="/resources/images/default_review.png" alt="기본 이미지">
								</c:otherwise>
							</c:choose>
						</div>
						<p class="review-content">${review.reviewContent}</p>
						<button type="button" onclick="reportReview('${review.reviewNo}','${memberNo}')">신고</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<p class="no-reviews">작성한 리뷰가 없습니다.</p>
	</c:otherwise>
</c:choose>

<script>
function reportReview(reviewNo, memberNo) {
	swal({
		title : "리뷰 신고",
		text : "이 리뷰를 신고하시겠습니까?",
		icon : "warning",
		buttons : {
			cancel : "취소",
			confirm : "신고"
		}
	}).then(function(confirm) {
		if (confirm) {
			$.ajax({
				url : "/review/report",
				type : "POST",
				data : {
					review_no : reviewNo,
					member_no : memberNo
				},
				success : function(response) {
					if (response === "success") {
                        swal("알림", "리뷰 신고가 완료되었습니다.", "success");
                    } else if (response === "already_reported") {
                        swal("알림", "이미 신고된 리뷰입니다.", "info");
                    } else if (response === "invalid_data") {
                        swal("오류", "유효하지 않은 데이터입니다.", "error");
                    } else {
                        swal("오류", "알 수 없는 오류가 발생하였습니다.", "error");
                    }
				},
				error : function() {
					swal("오류", "리뷰 신고 중 오류가 발생하였습니다.", "error");
				}
			});
		}
	});
}

</script>
