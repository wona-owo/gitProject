<%--
From : DinnerCheckReservationServlet.java
AJAX with DinnerCancelReservationServlet.java

예약 상세 정보를 보여주고 예약을 취소 할 수 도 있음
예약을 취소하면 DB 에서 예약 정보를 삭제 하고 회원에게 email 을 보내줘야함
그리고 새로고침

@Author : 김찬희
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- bootTime 에서 기간 부분만 추출하기 위해서 function 을 추가 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerReservation.jsp</title>
<style>
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
				<div class="page-title">${bookMonth}월${bookDay}일</div>
				<input type="hidden" value="${bookYear}" id="bookYear"> <input
					type="hidden" value="${bookMonth}" id="bookMonth"> <input
					type="hidden" value="${bookDay}" id="bookDay"> <input
					type="hidden" value="${dinnerNo}" id="dinnerNo">
				<div>
					<span>시간</span> <span>이름</span> <span>전화번호</span> <span>인원수</span>
					<span>취소를 해주는 버튼</span>
				</div>

				<%-- 이전 시간값을 선언 --%>
				<c:set var="previousHour" value="" scope="page" />
				<c:forEach var="b" items="${bookInfo}">
					<c:set var="currentHour" value="${fn:substring(b.bookTime, 0, 2)}" />

					<%-- 현재 값이 전값과 다른지 비교 (가장 처음에 보여줄때는 이전과 배교할 수 없으니 그냥 보여줌)--%>
					<c:if test="${empty previousHour or previousHour != currentHour}">
						<div class="new-book-hour">${currentHour}시</div>
					</c:if>

					<%-- 다음 값이랑 비교하기 위해 현재 값을 최신화 --%>
					<c:set var="previousHour" value="${currentHour}" scope="page" />

					<div>
						<ul class="group-menu" id="group-menu-${b.memberNo}">
							<li>${b.bookTime}</li>
							<li>${b.memberName}</li>
							<li>${b.memberPhone}</li>
							<li>${b.bookCnt}</li>
							<li class="menu-item"><input type="button"
								class="cancel-btn" value="취소">
								<ul class="sub-menu" id="sub-menu-${b.memberNo}">
									<%-- memberNo 에 따라서 id 를 다르게 준다 --%>
									<li><select id="select-input-${b.memberNo}"
										class="cancel-reason-select">
											<option value="" class="select-placeholder" disabled>취소
												사유 선택</option>
											<option value="0">숯에 불남</option>
											<option value="1">불판에 불남</option>
									</select></li>

									<li>
										<button type="submit"
											onclick="confirmCancel('${dinnerNo}', '${b.memberNo}', '${b.bookNo}')">확인</button>
									</li>
								</ul></li>
						</ul>
					</div>
				</c:forEach>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>
		$(function() {
			$('.menu-item .cancel-btn').click(
					function(event) {
						event.stopPropagation();

						let subMenu = $(this).siblings('.sub-menu');

						// Hide other sub-menus and reset their parent margins
						$('.sub-menu').not(subMenu).each(
								function() {
									$(this).hide();
									$(this).closest('.group-menu').css(
											'margin-bottom', '0px');
								});

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

		function refresh() {
			let year = $("#bookYear").val();
			let month = $("#bookMonth").val();
			let day = $("#bookDay").val();
			let dinnerNo = $("#dinnerNo").val();

			window.location.href = "/dinner/checkReservation?year=" + year
					+ "&month=" + month + "&day=" + day + "&dinnerNo="
					+ dinnerNo + "&check=" + 1;
		}
		
		function sendEmail(bookNo, selectedValue) {
			window.location.href ="/api/emailSend" + "?bookNo=" + bookNo + "&selectedValue=" + selectedValue;
		}

		function confirmCancel(dinnerNo, memberNo, bookNo) {
			let groupMenu = $('#group-menu-' + memberNo);
			let subMenu = $('#sub-menu-' + memberNo);

		    let selectElement = $('#select-input-' + memberNo);
		    let selectedValue = selectElement.find(":selected").val();

			console.log("Selected Value:", selectedValue);

			// 확인 버튼을 눌렀을때 sub-menu 를 닫고 포함 되어있는 div 태그의 margin 을 지움
			subMenu.hide();
			groupMenu.css('margin-bottom', '0px');

			// 확인 버튼을 눌렀을때 sub-menu 를 다른 div 태그의 margin 을 지움
			$('.sub-menu').hide();
			$('.group-menu').css('margin-bottom', '0px');

			if (selectedValue === "none") {
				return;
			} else {
				swal({
					title : "알림",
					text : "예약을 취소 하시겠습니까?",
					icon : "warning",
					buttons : {
						cancel : {
							text : "취소",
							value : false,
							visible : true,
							closeModal : true
						},
						confirm : {
							text : "확인",
							value : true,
							visible : true,
							closeModal : true
						}
					}
				})
						.then(
								function(isConfirm) {
									if (isConfirm) {
										sendEmail(bookNo, selectedValue);

										$
												.ajax({
													url : "/dinner/cancelReservation",
													type : "GET",
													data : {
														"bookNo" : bookNo,
													},
													success : function(res) {
														let title = "알림";
														let text = "";
														let icon = "";

														if (res > 0) {
															text = "예약이 취소 되었습니다";
															icon = "success";
														} else {
															text = "예약 취소 중 오류가 발생하였습니다";
															icon = "error";
														}

														swal({
															title : title,
															text : text,
															icon : icon,
														});
														
														// 알림 창이 띄어지자 마자 새로고침 되서 3초간 대기
														if (icon === "success") {
														    setTimeout(() => {
														        refresh();
														    }, 3000);
														}

													},
													error : function() {
														console.log("foobar");
													}
												});
									}
								});
			}
		}
	</script>
</body>
</html>
