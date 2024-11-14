<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerReservation.jsp</title>
<style>
* {
	border: 1px solid black;
}

.tbl {
	margin: 0 auto;
}

.tbl-row {
	text-align: center;
}
/*
clear
-- float 속성이 있는거 다음에 올때 float 속성을 없애줌

content
-- ::after 랑 같이 사용 자식 속성에 내용이 없더라도 부모 속성이 감쌀 수 있도록 해준다
*/
div {
	display: block;
	clear: both;
}

.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

.group-menu {
	position: relative;
}

.group-menu li {
	list-style-type: none;
}

.group-menu>li {
	float: left;
	text-align: center;
}

.sub-menu {
	display: none;
	position: absolute;
	left: 0;
}

.sub-menu>li {
	text-align: center;
	float: left;
}

.sub-menu>li>a {
	display: block;
}
</style>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section notice-list-wrap">
				<div class="page-title">${bookDate}</div>
				<div>
					<span>시간</span> <span>이름</span> <span>전화번호</span> <span>인원수</span>
					<span>취소를 해주는 버튼</span>
				</div>

				<c:forEach var="b" items="${bookInfo}">
					<div>
						<ul class="group-menu">
							<li>${b.bookTime}</li>
							<li>${b.memberName}</li>
							<li>${b.memberPhone}</li>
							<li>${b.bookCnt}</li>
							<li class="menu-item">
								<button class="cancel-btn">최소</button>
								<ul class="sub-menu">
									<li><select>
											<option value="poop1">내가 쉴려고</option>
											<option value="poop2">강아지가 아파서</option>
									</select></li>
									<li>
										<button onclick="confirmCancel('${b.memberNo}')">확인</button>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</c:forEach>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
		$(function() {
			// Handle menu item click
			$('.menu-item .cancel-btn').click(
					function(event) {
						event.stopPropagation();

						// Toggle the clicked sub-menu and adjust margin
						let subMenu = $(this).siblings('.sub-menu');
						$('.sub-menu').not(subMenu).hide();
						subMenu.toggle();
						$('.group-menu').css('margin-bottom',
								subMenu.is(':visible') ? '100px' : '0px');
					});

			// Close sub-menus and reset margin when clicking elsewhere on the page
			$(document).click(
					function(event) {
						if (!$(event.target).closest(
								'.sub-menu, .menu-item .cancel-btn').length) {
							$('.sub-menu').hide();
							$('.group-menu').css('margin-bottom', '0px');
						}
					});
		});

		function confirmCancel(memberNo) {
			$('.cancel-btn').closest('.group-menu').css('margin-bottom', '0px');
			$('.sub-menu').hide();
		}
	</script>
</body>
</html>
