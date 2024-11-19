<%--
From : DinnerCheckReservationServlet.java
AJAX with DinnerCancelReservationServlet.java
AJAX with ApiEmailSend.java

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
.wrap { $1
	margin: 20px auto;
	padding: 20px;
	background-color: #ffffff;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

main {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 80vh;
}

.page-title {
	font-size: 24px;
	font-weight: bold;
	color: #ffffff;
	background-color: #f40;
	text-align: center;
	padding: 15px;
	border-radius: 8px;
	margin-bottom: 20px;
}

.section {
	width: 70%;
}

.notice-list-wrap>div {
	display: flex;
	justify-content: center;
	padding: 10px;
	border-bottom: 1px solid #e0e0e0;
}

.notice-list-wrap>div>span {
	width: 20%;
	font-size: 16px;
	padding: 5px;
	text-align: center;
}

.new-book-hour {
	font-weight: bold;
	font-size: 20px;
	margin-top: 20px;
	padding: 5px;
	text-align: center;
}

ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

.group-menu {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	background-color: #f8f9fa;
	border-radius: 6px;
	margin-top: 0;
	width: 100%;
}

.group-menu li {
	font-size: 16px;
	margin-right: 10px;
}

.menu-item {
	position: relative;
}

.cancel-btn {
	background-color: #f44336;
	color: #ffffff;
	border: none;
	padding: 8px 12px;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s;
}

.cancel-btn:hover {
	background-color: #d32f2f;
}

.sub-menu {
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	background-color: #ffffff;
	border: 1px solid #ddd;
	box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
	padding: 10px;
	border-radius: 4px;
	z-index: 100;
	width: 220px;
}

.sub-menu>li {
	margin-bottom: 10px;
}

.cancel-reason-select {
	width: 100%;
	padding: 6px;
	font-size: 14px;
}

.sub-menu button {
	background-color: #4caf50;
	color: #ffffff;
	border: none;
	padding: 8px 12px;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s;
}

.sub-menu button:hover {
	background-color: #388e3c;
}
</style>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section notice-list-wrap">
				<div class="page-title">${bookMonth}월${bookDay}일</div>
				<input type="hidden" value="${bookYear}" name="bookYear"
					id="bookYear"> <input type="hidden" value="${bookMonth}"
					name="bookMonth" id="bookMonth"> <input type="hidden"
					value="${bookDay}" name="bookDay" id="bookDay"> <input
					type="hidden" value="${dinnerNo}" id="dinnerNo">
				<div>
					<span>시간</span> <span>이름</span> <span>전화번호</span> <span>인원수</span>
					<span>취소</span>
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
		    console.log("Sending email");
		    $.ajax({
		        url: "/api/emailSend",
		        type: "GET",
		        data: {
		            bookNo: bookNo,
		            selectedValue: selectedValue
		        },
				success : function(res) {
					let title = "알림";
					let text = "";
					let icon = "";

					if (res > 0) {
					    title = "성공",
						text = "이메일이 전송되었습니다";
						icon = "success";
					} else {
					    title = "실패",
						text = "이메일 전송중 오류가 발생하였습니다";
						icon = "error";
					}

					swal({
						title : title,
						text : text,
						icon : icon,
					});

				},
				error : function() {
					console.log("foobar sending email");
				}
		    });
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
				}).then( function(isConfirm) {
					if (isConfirm) {

						sendEmail(bookNo, selectedValue);

						$.ajax({
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
								
								// 알림 창이 띄어지자 마자 새로고침 되서 1.5초간 대기
								if (icon === "success") {
									setTimeout(() => {
										refresh();
									}, 1500);
								}

							},
							error : function() {
								console.log("foobar confirming cancel");
							}
						});
					}
     			});
			}
		}
	</script>
</body>
</html>
