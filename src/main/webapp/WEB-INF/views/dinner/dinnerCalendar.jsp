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
}

.calendar {
	width: 300px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.calendar-header {
	display: flex;
	justify-content: center;
	align-items: center;
	margin: auto;
	padding: 10px;
	background-color: #4CAF50;
	color: #fff;
	padding: 10px;
}

.month-year {
	font-size: 1.2em;
}

.weekdays, .days {
	display: grid;
	grid-template-columns: repeat(7, 1fr);
	padding: 10px;
}

.weekdays span {
	font-weight: bold;
	text-align: center;
	margin-bottom: 5px;
}

.days span {
	text-align: center;
	padding: 8px;
	cursor: pointer;
	border-radius: 4px;
}

.days span:hover {
	background-color: #d3e2d3;
}

.days .today {
	background-color: #4CAF50;
	color: #fff;
	font-weight: bold;
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

					<div class="month-year">
						<span id="month"></span> <span id="year"></span>
					</div>
				</div>

				<div class="weekdays">
					<span>Sun</span> <span>Mon</span> <span>Tue</span> <span>Wed</span>
					<span>Thu</span> <span>Fri</span> <span>Sat</span>
				</div>

				<div class="days" id="days"></div>

				<div>
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
		$(document).ready(
				function() {
					const daysContainer = $("#days");
					const monthDisplay = $("#month");
					const yearDisplay = $("#year");


					let currentDate = new Date();

					function renderCalendar() {
						daysContainer.html("");
						const year = currentDate.getFullYear();
						const month = currentDate.getMonth();

						const firstDayOfMonth = new Date(year, month, 1)
								.getDay();
						const daysInMonth = new Date(year, month + 1, 0)
								.getDate();

						monthDisplay.text(currentDate.toLocaleString("default",
								{
									month : "long"
								}));
						yearDisplay.text(year);

						for (let i = 0; i < firstDayOfMonth; i++) {
							daysContainer.append("<span></span>");
						}

						for (let day = 1; day <= daysInMonth; day++) {
							const dayElement = $("<span></span>").text(day);
							if (day === new Date().getDate()
									&& year === new Date().getFullYear()
									&& month === new Date().getMonth()) {
								dayElement.addClass("today");
							}
							daysContainer.append(dayElement);
						}
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

					renderCalendar();
				});
	</script>

</body>
</html>
