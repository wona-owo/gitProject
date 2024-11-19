<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 페이지</title>
<link rel="stylesheet"
	href="/resources/css/dinner_admin_memberdetail.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="wrap">
		<main class="content mypage-container">
			<section class="section memberRv-wrap">
				<div class="page-title">회원 상세 페이지</div>
				<table class="tbl tbl_hover">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>별명</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>성별</th>
						<th>이메일</th>
						<th>가입일자</th>
						<th>성인인증</th>
						<th>등급</th>
					</tr>

					<tr>
						<td>${member.memberNo}</td>
						<td>${member.memberId}</td>
						<td>${member.memberName}</td>
						<td>${member.memberNick}</td>
						<td>${member.memberPhone}</td>
						<td>${member.memberAddr}</td>
						<td>${member.memberGender}</td>
						<td>${member.memberEmail}</td>
						<td>${member.enrollDate}</td>
						<td>${member.adultConfirm}</td>
						<td>${member.memberLevel}</td>
					</tr>
				</table>

				<br>

				<div class="my-info-box">
					<p>총 신고당한 횟수 : 9999+</p>
					<div class="action-bar">
						<div class="sort-dropdown">
							<form action="/admin/memberDetail" method="get">
								<input type="hidden" name="memberNo" value="${member.memberNo}">
								<select name="sortOption" onchange="this.form.submit()">
									<option value="latest"
										${param.sortOption == 'latest' ? 'selected' : ''}>최신순</option>
									<option value="oldest"
										${param.sortOption == 'oldest' ? 'selected' : ''}>오래된순</option>
									<option value="report"
										${param.sortOption == 'report' ? 'selected' : ''}>신고순</option>
								</select>
							</form>
						</div>
						<button class="delete-button" onclick="removeAllMembers()">선택삭제</button>
					</div>
				</div>

				<br>

				<p>회원이 쓴 리뷰</p>
				<div class="my-info-wrap">
					<div class="rvMainBox">
						<c:choose>
							<c:when test="${not empty reviews}">
								<c:forEach var="review" items="${reviews}">
									<div>
										<p>식당명: ${review.dinnerName}</p>
										<p>신고당한 횟수: 0</p>
										<p>아이디(별명): ${member.memberId} (${member.memberNick})</p>
										<br>
										<p>작성일자: ${review.reviewDate}</p>
										<br>
										<div class="reviewBox">
											<c:choose>
												<c:when test="${review.reviewImage != null}">
													<div class="reviewPhoto">
														<img src="data:image/jpeg;base64,${fn:escapeXml(review.reviewImage)}" alt="리뷰 이미지">
													</div>
												</c:when>
												<c:otherwise>
													<div class="reviewPhoto">
														<img src="/resources/images/default_review.png" alt="기본 이미지">
													</div>
												</c:otherwise>
											</c:choose>
										</div>
										<p>${review.reviewContent}</p>
										<div class="checkbox-container">
											<form action="/review/report" method="post">
												<input type="hidden" name="review_no"
													value="${review.reviewNo}"> <input type="hidden"
													name="member_no" value="${member.memberNo}">
												<button type="button" onclick="reportReview('${review.reviewNo}', '${member.memberNo}')">신고</button>
											</form>
											<input type="checkbox" name="rpchk" data-review-no="${review.reviewNo}">
										</div>
									</div>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<p>작성한 리뷰가 없습니다.</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

<script>

	//리뷰 신고 성공여부 알림창
	function reportReview(reviewNo, memberNo) {
		$.ajax({
			url : "/review/report",
			type : "GET",
			data : {
				review_no : reviewNo,
				member_no : memberNo
			},
			success : function(response) {
				// 서버에서 반환된 텍스트 메시지를 표시
				swal("알림", "리뷰 신고가 완료되었습니다.", "success");
			},
			error : function() {
				swal("오류", "리뷰 신고 중 오류가 발생하였습니다", "error");
			}
		});
	}

	//선택된 회원 탈퇴
	function removeAllMembers() {
		let checkBoxes = $("input[name='rpchk']:checked");
		if (checkBoxes.length < 1) {
			swal({
				title : "알림",
				text : "선택한 리뷰가 없습니다",
				icon : "warning"
			});
			return;
		}

		let reviewNoArr = [];
		$.each(checkBoxes, function(index, item) {
			let reviewNo = $(item).data("review-no"); // 체크박스의 data 속성에서 reviewNo 읽기
			if (reviewNo) {
				reviewNoArr.push(reviewNo);
			}
		});

		swal({
			title : "리뷰 삭제",
			text : "선택된 리뷰를 삭제하시겠습니까?",
			icon : "warning",
			buttons : {
				cancel : "취소",
				confirm : "삭제"
			}
		}).then(
				function(confirm) {
					if (confirm) {
						$.ajax({
							url : "/reviewRemoveAll", // 리뷰 삭제에 맞는 URL로 변경
							type : "POST",
							data : {
								reviewNoArr : reviewNoArr.join("/")
							}, // 문자열로 조합하여 전송
							success : function(response) {
								swal("알림", "선택된 리뷰가 삭제되었습니다", "success").then(
										function() {
											location.reload(); // 페이지 새로고침
										});
							},
							error : function() {
								swal("오류", "리뷰 삭제 중 오류가 발생하였습니다", "error");
							}
						});
					}
				});
	}
</script>

</html>