<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 상세 페이지</title>
<style>
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content mypage-container">
			<section class="section mypage-wrap">
				<div class="page-title">매장 정보페이지</div>
				<div class="my-info-wrap">
					<input type="hidden" name="memberNo" value="${dinnerNo}">
					<table class="tbl">
					<tr>
							<th width="30%">식당 코드</th>
							<td width="70%" class="left">${d.dinnerNo}</td>
						</tr>
						<tr>
							<th width="30%">식당 이름 </th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">식당 ID</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">오픈 시간</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">식당전화</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">식당 이메일 </th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">주차여부</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">수용인원</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">수용인원</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>
						<tr>
							<th width="30%">승인 여부</th>
							<td width="70%" class="left">${loginMember.memberId}</td>
						</tr>

						<tr>
							<th><label for="memberName">이름</label></th>
							<td class="left">
								<div class="input-wrap">
									<div class="input-item">
										<input type="text" id="memberName" name="memberName"
											value="${loginMember.memberName}" maxlength="10"
											placeholder="한글 2~10글자">
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<th><label for="memberEmail">이메일</label></th>
							<td class="left">
								<div class="input-wrap">
									<div class="input-item">
										<input type="email" id="memberEmail" name="memberEmail"
											value="${loginMember.memberEmail}">
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<th><label for="memberPhone">전화번호</label></th>
							<td class="left">
								<div class="input-wrap">
									<div class="input-item">
										<input type="text" id="memberPhone" name="memberPhone"
											value="${loginMember.memberPhone}" maxlength="13"
											placeholder="010-0000-0000">
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<th><label for="memberAddr">주소</label></th>
							<td class="left">
								<div class="input-wrap">
									<div class="input-item">
										<input type="text" id="memberAddr" name="memberAddr"
											value="${loginMember.memberAddr}" maxlength="200">
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>


</body>
</html>