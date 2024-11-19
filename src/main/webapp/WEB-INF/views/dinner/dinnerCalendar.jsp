<%--
From : DinnerCalendarFrmServlet.java
AJAX with : DinnerCalendarReservationServlet.java (/dinner/reservation)
To : DinnerCheckReservationServlet.java (/dinner/checkReservation)

스크립트를 이용해서 달력을 생성
ajax 를 이용해서 달력에서 보여지는 달에 해당하는 예약 정보를 json 형태로 받아옴
예약 있는 날을 클릭하면 예약을 상세보기/취소 할수 있는 페이지로 이동

Author : 김찬희
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerCalendar.jsp</title>
<style>
main {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 80vh;
}

section {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	width: 50%;
}

.calendar {
	width: 100%;
	max-width: 600px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.calendar-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	background-color: #ff4400;
	color: #fff;
}

.calendar-title {
	font-size: 1.5em;
	margin-left: 10px;
}

.month-year {
	font-size: 1.2em;
}

.weekdays, .days {
	display: grid;
	grid-template-columns: repeat(7, 1fr);
	padding: 10px;
	border-bottom: 1px solid #dcdcdc;
	gap: 0;
}

.weekdays span {
	font-weight: bold;
	text-align: center;
	margin-bottom: 5px;
}

.days span {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	padding: 10px;
	cursor: pointer;
	border: 1px solid #dcdcdc;
	text-align: center;
	box-sizing: border-box;
	background-color: #fff;
	height: 80px;
}

.days span.has-number div:first-child {
	background-color: #ff4400;
	color: #fff;
	border-radius: 50%;
	padding: 5px;
	width: 30px;
	height: 30px;
	display: flex;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin-bottom: 5px;
}

.days span.has-number:hover {
	background-color: rgba(255, 68, 0, 0.1);
}

.days .today {
	border: 2px solid #ff4400;
	border-radius: 0;
	color: #ff4400;
	font-weight: bold;
}

.days .today:hover {
	border: 2px solid #ff4400;
	border-radius: 0;
	color: #ff4400;
	font-weight: bold;
}

/* Reservation count style */
.days span div:last-child {
	color: #4CAF50;
	font-size: 0.9em;
}

#prev-month, #next-month, #check-today {
	border: none;
	width: 50px;
	height: 50px;
	border-radius: 8px;
	background-color: #f0f0f0;
	transition: background-color 0.3s;
}

#prev-month:hover, #next-month:hover, #check-today:hover {
	background-color: #b6b6b6;
	cursor: pointer;
}

#check-today-div {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 30px;
}

#check-today {
	margin-right: 20px;
	margin-top: 10px;
	width: 75px;
	height: 40px;
	background-color: #ff4400;
	color: #fff;
	border-radius: 5px;
	font-weight: bold;
	transition: background-color 0.3s;
}

#check-today:hover {
	background-color: #e03e00;
}
</style>

</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<div>
				<button id="prev-month">◀</button>
			</div>
			<section class="section">
				<div class="calendar-header">
					<div class="calendar-title">예약 현황</div>
					<div class="month-year">
						<span id="month"></span> <span id="year"></span> <input
							type="hidden" name="dinnerNo" id="dinnerNo"
							value="${loginMember.dinnerNo}">
					</div>
				</div>
				<div class="weekdays">
					<span style="color: red;">Sun</span> <span>Mon</span> <span>Tue</span>
					<span>Wed</span> <span>Thu</span> <span>Fri</span> <span
						style="color: skyblue;">Sat</span>
				</div>

				<div class="days" id="days"></div>

				<div id="check-today-div">
					<button id="check-today">오늘</button>
				</div>
			</section>
			<div>
				<button id="next-month">▶</button>
			</div>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
		$(function() {
			const daysContainer = $("#days");
			const monthDisplay = $("#month");
			const yearDisplay = $("#year");

			let currentDate = new Date();

			function renderCalendar() {
				daysContainer.html("");
				const year = currentDate.getFullYear();
				const month = currentDate.getMonth();

				const firstDayOfMonth = new Date(year, month, 1).getDay();
				const daysInMonth = new Date(year, month + 1, 0).getDate();

				monthDisplay.text(currentDate.toLocaleString("default", {
					month : "long"
				}));
				yearDisplay.text(year);

				for (let i = 0; i < firstDayOfMonth; i++) {
					daysContainer.append("<span></span>");
				}

				function getBookCnt(day, data) {
					let dayStr = day.toString().padStart(2, '0');
					return data[dayStr] || 0;
				}

				$
						.ajax({
							url : "/dinner/reservation",
							data : {
								dinnerNo : $("#dinnerNo").val(),
								displayMonth : $("#month").text(),
								displayYear : $("#year").text()
							},
							type : "GET",
							success : function(res) {
								for (let day = 1; day <= daysInMonth; day++) {
									const dayEl = $("<span></span>");
									const dayNumEl = $("<div></div>").text(day);
									const bookCnt = getBookCnt(day, res);

									const bookCntEl = $("<div></div>").html(
											bookCnt ? bookCnt + "팀" : "&nbsp;");
									dayEl.append(dayNumEl, bookCntEl);

									if (bookCnt) {
										dayEl.addClass("has-number");
									}

									const today = new Date();
									if (day === today.getDate()
											&& year === today.getFullYear()
											&& month === today.getMonth()) {
										dayEl.addClass("today");
									}

									dinnerNo = $("#dinnerNo").val();

									const dayLink = $("<a></a>")
											.attr(
													"href",
													bookCnt ? "/dinner/checkReservation?dinnerNo="
															+ dinnerNo
															+ "&day="
															+ day
															+ "&month="
															+ month
															+ "&year=" + year
															: null).addClass(
													bookCnt ? "" : "disabled");

									dayLink.append(dayEl);
									daysContainer.append(dayLink);
								}
							},
							error : function() {
								console.error("poop");
							}
						});
			}

			$("#prev-month").on("click", function() {
				currentDate.setMonth(currentDate.getMonth() - 1);
				renderCalendar();
			});

			$("#next-month").on("click", function() {
				currentDate.setMonth(currentDate.getMonth() + 1);
				renderCalendar();
			});

			$("#check-today").on("click", function() {
				currentDate = new Date();
				renderCalendar();
			});

			// Initial render
			renderCalendar();
		});
	</script>
</body>
</html>
