<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 페이지</title>
<link rel="stylesheet"
	href="/resources/css/diner_admin_memberdetail.css">
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
						<td>${member.adultValid}</td>
						<td>${member.memberLevel}</td>
					</tr>
				</table>

				<br>

				<div class="my-info-box">
					<p>총 신고당한 횟수 : 9999+</p>
					<div class="action-bar">
						<div class="sort-dropdown">
							<select id="sort-options">
								<option value="latest">최신순</option>
								<option value="oldest">오래된순</option>
								<option value="report">신고순</option>
							</select>
						</div>
						<button class="delete-button">선택삭제</button>
					</div>
				</div>

				<br>

				<p>회원이 쓴 리뷰</p>
				<div class="my-info-wrap">
					<div class="rvMainBox">
						<div>
							<p>식당명</p>
							<p>신고당한 횟수 : 999+</p>
							<p>아이디(별명)</p>
							<br>
							<p>작성일자</p>
							<br>
							<div class="reviewBox">
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fd2v80xjmx68n4w.cloudfront.net%2Fmembers%2Fportfolios%2FpQTUz1710345877.jpg%3Fw%3D718&type=a340">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA2MjVfMjQ5%2FMDAxNTkzMDQ3MDkxMzA5.vg4HFQ_FqqP_KFWAdH4WxdSlvbJDc8A6Te4yQpobIpkg.rAVNRC5Is6ylpsCO0R9zl9lU0e2DhGp8YhOyoxat3yYg.JPEG.pixta%2Fpixta_58587077_M.jpg&type=sc960_832">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fcdn.crowdpic.net%2Fdetail-thumb%2Fthumb_d_043E9C8692374A58466EFADCEAA5827D.jpg&type=a340">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTA5MTFfMjU2%2FMDAxNTY4MjEwMTc3MTEy.eZeivn1BoEwOMGotJgdX99C5aWTEZuCjFkjOVZdAMD4g._K139z6KKHNRuN0Yx0-pKFmMDXF5be_jZRP-LJ03rlIg.JPEG%2F84A3A6D5-581F-459E-A7E6-F7EE91469547.jpeg&type=a340">
								</div>
							</div>
							<p>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
							</p>
							<div class="checkbox-container">
								<input type="checkbox" name="rpchk">
							</div>
						</div>

						<div>
							<p>식당명</p>
							<p>신고당한 횟수 : 0</p>
							<p>아이디(별명)</p>
							<br>
							<p>작성일자</p>
							<br>
							<div class="reviewBox">
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fd2v80xjmx68n4w.cloudfront.net%2Fmembers%2Fportfolios%2FpQTUz1710345877.jpg%3Fw%3D718&type=a340">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA2MjVfMjQ5%2FMDAxNTkzMDQ3MDkxMzA5.vg4HFQ_FqqP_KFWAdH4WxdSlvbJDc8A6Te4yQpobIpkg.rAVNRC5Is6ylpsCO0R9zl9lU0e2DhGp8YhOyoxat3yYg.JPEG.pixta%2Fpixta_58587077_M.jpg&type=sc960_832">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fcdn.crowdpic.net%2Fdetail-thumb%2Fthumb_d_043E9C8692374A58466EFADCEAA5827D.jpg&type=a340">
								</div>
								<div class="reviewPhoto">
									<img
										src="https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTA5MTFfMjU2%2FMDAxNTY4MjEwMTc3MTEy.eZeivn1BoEwOMGotJgdX99C5aWTEZuCjFkjOVZdAMD4g._K139z6KKHNRuN0Yx0-pKFmMDXF5be_jZRP-LJ03rlIg.JPEG%2F84A3A6D5-581F-459E-A7E6-F7EE91469547.jpeg&type=a340">
								</div>
							</div>
							<p>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
								리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용<br>
							</p>
							<div class="checkbox-container">
								<input type="checkbox" name="rpchk">
							</div>
						</div>
					</div>
				</div>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>