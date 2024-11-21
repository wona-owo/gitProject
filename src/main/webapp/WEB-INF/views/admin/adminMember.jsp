<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 페이지</title>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="/resources/css/dinner_admin_member.css">
</head>
<style>
.pagination {
	display: flex;
	justify-content: center; /* 중앙 정렬 */
	margin: 20px 0;
}

.pagination a {
	padding: 8px 12px;
	margin: 0 4px;
	border: 1px solid #ddd;
	color: #333;
	text-decoration: none;
	border-radius: 5px;
}

.pagination a.active {
	background-color: #f40;
	color: #fff;
	border-color: #f40;
}

.btn-container {
	display: flex;
	justify-content: center; /* 중앙 정렬 */
	gap: 10px;
	margin-top: 20px;
}

.btn-selectlv, .btn-selectrm {
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn-selectlv:hover, .btn-selectrm:hover {
	background-color: white;
}
</style>
<body>

	<div class="wrap">
		<main class="content">
			<section class="section admin-wrap">
				<div class="page-title">회원 관리 페이지</div>
				<div class="table-container">
					<div class="search-box">
						<form action="/admin/member" method="get">
							<input type="hidden" name="action" value="search"> <input
								type="text" name="memberNick" placeholder="회원 별명 검색">
							<button type="submit">검색</button>
						</form>
					</div>

					<table class="tbl tbl_hover">
						<tr>
							<th style="width: 7%;">선택</th>
							<th style="width: 5%">번호</th>
							<th style="width: 10%">아이디</th>
							<th style="width: 10%">회원별명</th>
							<th style="width: 10%">이메일</th>
							<th style="width: 15%">전화번호</th>
							<th style="width: 15%">주소</th>
							<th style="width: 10%">등급</th>
						</tr>

						<c:forEach var="m" items="${members}">
							<tr>
								<td>
									<!-- 선택 -->
									<div>
										<input type="checkbox" class="chk"> <label
											onclick="chkLabel(this)"></label>
									</div>
								</td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberNo}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberId}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberNick}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberEmail}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberPhone}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">${m.memberAddr}</a></td>
								<td><a href="/admin/memberDetail?memberNo=${m.memberNo}">회원</a></td>
							</tr>
						</c:forEach>
					</table>

					<!-- 페이지 번호 -->
					<div class="pagination">
						<c:forEach var="i" begin="1" end="${totalPages}">
							<a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
						</c:forEach>
					</div>

					<!-- 선택 등급 변경 및 회원 탈퇴 버튼 -->
					<div class="btn-container">
						<button class="btn-selectrm lg" onclick="removeAllMembers()">선택
							회원 탈퇴</button>
					</div>
				</div>
			</section>
		</main>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>

<script>
function refresh() {
    location.reload();
}

// 개인 탈퇴
function selectRemove(memberNo) {
    swal({
        title: "회원탈퇴",
        text: "정말 회원탈퇴를 하시겠습니까?",
        icon: "warning",
        buttons: {
            cancel: { text: "취소", value: false, visible: true, closeModal: true },
            confirm: { text: "탈퇴", value: true, visible: true, closeModal: true }
        }
    }).then(function(confirm) {
        if (confirm) {
            // 현재 버튼이 속한 행의 form을 찾아서 처리
            let form = document.createElement("form");
            form.method = "POST";
            form.action = "/admin/memberRemove";

            let input = document.createElement("input");
            input.type = "hidden";
            input.name = "memberNo";
            input.value = memberNo;

            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        }
    });
}


// 등급 변경 시 체크박스 활성화
function memberLevelChange(selectElement) {
    $(selectElement).closest("tr").find(".chk").prop("checked", true);
}

// 선택된 등급 변경
function chgLevel() {
    let checkBoxes = $(".chk:checked");
    if (checkBoxes.length < 1) {
        swal({ title: "알림", text: "선택한 회원이 없습니다", icon: "warning" });
        return;
    }

    let memberNoArr = [];
    let memberLevelArr = [];
    $.each(checkBoxes, function(index, item) {
        let row = $(item).closest("tr");
        let memberNo = row.find("td:nth-child(2)").text().trim();
        let memberLevel = row.find(".select option:selected").val();
        if (memberNo) {
            memberNoArr.push(memberNo);
            memberLevelArr.push(memberLevel);
        }
    });

    swal({
        title: "알림",
        text: "등급을 변경하시겠습니까?",
        icon: "warning",
        buttons: {
            cancel: { text: "취소", value: false, visible: true, closeModal: true },
            confirm: { text: "변경", value: true, visible: true, closeModal: true }
        }
    }).then(function(isConfirm) {
        if (isConfirm) {
            $.ajax({
                url: "/member/chgLevel",
                type: "GET",
                data: { "memberNoArr": memberNoArr.join("/"), "memberLevelArr": memberLevelArr.join("/") },
                success: function(res) {
                    let text = res > 0 ? "등급이 변경되었습니다" : "등급 변경 중 오류가 발생하였습니다";
                    let icon = res > 0 ? "success" : "error";
                    swal({ title: "알림", text: text, icon: icon }).then(refresh);
                },
                error: function() {
                    swal("오류", "등급 변경 중 오류가 발생하였습니다", "error");
                }
            });
        }
    });
}

// 선택된 회원 탈퇴
function removeAllMembers() {
    let checkBoxes = $(".chk:checked");
    if (checkBoxes.length < 1) {
        swal({ title: "알림", text: "선택한 회원이 없습니다", icon: "warning" });
        return;
    }

    let memberNoArr = [];
    $.each(checkBoxes, function(index, item) {
        let row = $(item).closest("tr");
        let memberNo = row.find("td:nth-child(2)").text().trim();
        if (memberNo) {
            memberNoArr.push(memberNo);
        }
    });

    swal({
        title: "회원 탈퇴",
        text: "선택된 회원을 탈퇴하시겠습니까?",
        icon: "warning",
        buttons: { cancel: "취소", confirm: "탈퇴" }
    }).then(function(confirm) {
        if (confirm) {
            $.ajax({
                url: "/admin/memberRemoveAll",
                type: "POST",
                data: { "memberNoArr": memberNoArr.join("/") },
                success: function(response) {
                    swal("알림", "선택된 회원이 탈퇴되었습니다", "success").then(refresh);
                },
                error: function() {
                    swal("오류", "회원 탈퇴 중 오류가 발생하였습니다", "error");
                }
            });
        }
    });
}
</script>
</html>
