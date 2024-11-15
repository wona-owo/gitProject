<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerReservation.jsp</title>
<style>
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

/*
border 가 없으면 취소 버튼을 눌렀을때 margin 이 이상한곳에 생겨서
transparent 한 border 를 만들어줌
*/
ul {
	border: 1px solid transparent;
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

.select-placeholder {
	display: none;
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
						<ul class="group-menu" id="group-menu-${b.memberNo}">
							<li>${b.bookTime}</li>
							<li>${b.memberName}</li>
							<li>${b.memberPhone}</li>
							<li>${b.bookCnt}</li>
							<li class="menu-item">
								<button class="cancel-btn">취소</button> <%-- memberNo 에 따라서 id 와 class 를 다르게 준다 --%>
								<ul class="sub-menu" id="sub-menu-${b.memberNo}">
									<li><select id="select-input-${b.memberNo}">
											<option value="placeholder" class="select-placeholder"
												selected>choose option</option>
											<option value="0">가게에 불남</option>
											<option value="1">불판에 불남</option>
											<option value="2">내가 아파서</option>
											<option value="3">강아지가 아파서</option>
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

						// Toggle the clicked sub-menu
						let subMenu = $(this).siblings('.sub-menu');

						// Hide other sub-menus and reset their parent margins
						$('.sub-menu').not(subMenu).each(
								function() {
									$(this).hide();
									$(this).closest('.group-menu').css(
											'margin-bottom', '0px');
								});

						// Toggle the visibility of the clicked sub-menu
						subMenu.toggle();

						// Adjust the margin of the parent .group-menu
						let groupMenu = $(this).closest('.group-menu');
						groupMenu.css('margin-bottom',
								subMenu.is(':visible') ? '100px' : '0px');
					});

			// Close sub-menus and reset margins when clicking elsewhere
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
			// Select the groupMenu and subMenu using the memberNo
			let groupMenu = $('#group-menu-' + memberNo);
			let subMenu = $('#sub-menu-' + memberNo);

			// Find the select element within the sub-menu
			let selectElement = subMenu.find('select');

			if (selectElement.length === 0) {
				console.error("Select element not found for memberNo:",
						memberNo);
				return;
			}

			// Get the selected value
			let selectedValue = selectElement.val();

			// Hide the sub-menu and reset the margin for this group
			subMenu.hide();
			groupMenu.css('margin-bottom', '0px');

			// Hide all sub-menus and reset margins globally
			$('.sub-menu').hide();
			$('.group-menu').css('margin-bottom', '0px');

			console.log('Member Number:', memberNo);

			if (selectedValue === "placeholder") {
				return;
			}

			// Proceed with your logic when a valid option is selected
			console.log("Selected value:", selectedValue);
		}
	</script>
</body>
</html>
