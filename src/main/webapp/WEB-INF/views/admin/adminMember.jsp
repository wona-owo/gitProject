<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 페이지</title>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="/resources/css/diner_admin_member.css">
</head>
<body>

	<div class="wrap">
		<main class="content">
			<section class="section admin-wrap">
				<div class="page-title">회원 관리 페이지</div>
				<div class="table-container">
				    <div class="search-box">
				        <input type="text" placeholder="회원 이름 검색" id="searchInput">
				        <button onclick="searchMembers()">검색</button>
				    </div>
				
				<table class="tbl tbl_hover">
					<tr>
						<th style="width: 5%;">선택</th>
						<th style="width: 5%">번호</th>
						<th style="width: 10%">아이디</th>
						<th style="width: 10%">회원별명</th>
						<th style="width: 10%">이메일</th>
						<th style="width: 15%">전화번호</th>
						<th style="width: 15%">주소</th>
						<th style="width: 10%">등급변경</th>
						<th style="width: 10%">강제탈퇴</th>
					</tr>
					
					<c:forEach var="m" items="${memberList}">
					<tr>
						<td> <!-- 선택 -->
							<div class="input-wrap">
								<input type="checkbox" class="chk">
								<label onclick="chkLabel(this)"></label>
							</div>
						</td>
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberNo}</a></td>		 <!-- 번호 -->
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberId}</a></td> 		 <!-- 아이디 -->
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberNick}</a></td>      <!-- 별명 -->
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberEmail}</a></td>	 <!-- 이메일 -->
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberPhone}</a></td>	 <!-- 전화번호 -->
						<td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberAddr}</a></td>	     <!-- 주소 -->
						
						<td>						 													 <!-- 등급변경 -->
							<div class="select">
								<select>
									<option value="1" selected>회원</option>
									<option value="2">가맹점</option>
								</select>
							</div>
						</td>
						
						<td>							                                                 <!--  탈퇴 -->
							<button class="btn-primary sm" onclick="selectRemove('${m.memberNo}')">탈퇴</button>
						</td>
					</tr>
					</c:forEach>
						
					<tr>
						<td colspan="10">
							<button class="btn-selectlv lg" onclick="chgLevel(this)">선택 등급 변경</button>
							<button class="btn-selectrm lg" onclick="removeMembers()">선택 회원 탈퇴</button>
						</td>
					</tr>
				</table>
				</div>
			</section>
		</main>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
</body>

<script>

//탈퇴
function selectRemove(memberNo){
	// 탈퇴 확인
    if (confirm("정말로 이 회원을 탈퇴 처리하시겠습니까?")) {
        // Ajax 요청으로 특정 회원 탈퇴 처리
        $.ajax({
            url: "/admin/memberRemove", // 실제 탈퇴 처리를 담당하는 URL
            type: "POST",
            data: { memberNo: memberNo }, // 데이터 전송 (URL 인코딩 없이 기본 객체 형식)
            success: function(response) {
                if (response === "success") {
                    alert("회원이 성공적으로 탈퇴되었습니다.");
                    location.reload(); // 페이지 새로고침으로 목록 업데이트
                } else {
                    alert("탈퇴 처리 중 오류가 발생했습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.log("ajax 통신 오류", status, error);
                alert("서버와의 통신에 실패하였습니다.");
            }
        });
    }
}


//선택 등급 변경
function chgLevel(obj) {
    // 선택된 회원 정보 배열
    let selectedMembers = [];

    // 체크된 체크박스의 행에서 회원 번호와 등급을 가져옴
    $('.chk:checked').each(function() {
        let memberNo = $(this).closest('tr').find('td:nth-child(2)').html(); // 두 번째 <td>의 내용 (번호)
        let memberLevel = $(this).closest('tr').find('select option:selected').val(); // 선택된 등급

        // 배열에 추가
        selectedMembers.push({ memberNo: memberNo, memberLevel: memberLevel });
    });

    // 선택된 회원이 없을 경우 경고
    if (selectedMembers.length === 0) {
        swal("알림", "등급을 변경할 회원을 선택해 주세요.", "warning");
        return;
    }

    // SweetAlert으로 변경 확인 메시지 표시
    swal({
        title: "알림",
        text: "선택된 회원의 등급을 변경하시겠습니까?",
        icon: "success",
        buttons: {
            cancel: {
                text: "취소",
                value: false,
                visible: true,
                closeModal: true
            },
            confirm: {
                text: "변경",
                value: true,
                visible: true,
                closeModal: true
            }
        }
    }).then(function(isConfirm) {
        if (isConfirm) {
            // Ajax 요청으로 등급 변경 요청
            $.ajax({
                url: "/member/chgLevel",
                type: "POST",
                contentType: "application/json", // JSON 형식으로 데이터 전송
                data: JSON.stringify(selectedMembers),
                success: function(res) {
                    let title = '알림';
                    let text = '';
                    let icon = '';

                    if (res > 0) {
                        text = '등급이 변경되었습니다.';
                        icon = 'success';
                    } else {
                        text = "등급 변경 중 오류가 발생하였습니다.";
                        icon = 'error';
                    }

                    // SweetAlert로 결과 알림
                    swal({
                        title: title,
                        text: text,
                        icon: icon
                    });
                },
                error: function() {
                    console.log('ajax 통신 오류');
                    swal("오류", "서버와의 통신에 실패하였습니다.", "error");
                }
            });
        }
    });
}

//선택 회원 탈퇴
function removeMembers() {
    // 선택된 회원 정보 배열
    let selectedMembers = [];

    // 체크된 체크박스의 행에서 회원 번호를 가져옴
    $('.chk:checked').each(function() {
        let memberNo = $(this).closest('tr').find('td:nth-child(2)').html(); // 두 번째 <td>의 내용 (번호)
        selectedMembers.push(memberNo);  // 회원 번호만 추가
    });

    // 선택된 회원이 없을 경우 경고
    if (selectedMembers.length === 0) {
        swal("알림", "탈퇴할 회원을 선택해 주세요.", "warning");
        return;
    }

    // SweetAlert으로 탈퇴 확인 메시지 표시
    swal({
        title: "알림",
        text: "선택된 회원을 탈퇴 처리하시겠습니까?",
        icon: "warning",
        buttons: {
            cancel: {
                text: "취소",
                value: false,
                visible: true,
                closeModal: true
            },
            confirm: {
                text: "탈퇴",
                value: true,
                visible: true,
                closeModal: true
            }
        }
    }).then(function(isConfirm) {
        if (isConfirm) {
            // Ajax 요청으로 탈퇴 요청
            $.ajax({
                url: "/admin/removeMembers",
                type: "POST",
                contentType: "application/json", // JSON 형식으로 데이터 전송
                data: JSON.stringify(selectedMembers), // 회원 번호 배열 전송
                success: function(res) {
                    let title = '알림';
                    let text = '';
                    let icon = '';

                    if (res > 0) {
                        text = '선택된 회원이 탈퇴 처리되었습니다.';
                        icon = 'success';
                    } else {
                        text = "탈퇴 처리 중 오류가 발생하였습니다.";
                        icon = 'error';
                    }

                    // SweetAlert로 결과 알림
                    swal({
                        title: title,
                        text: text,
                        icon: icon
                    }).then(function() {
                        // 성공 후 페이지를 새로고침하여 반영
                        if (res > 0) location.reload();
                    });
                },
                error: function() {
                    console.log('ajax 통신 오류');
                    swal("오류", "서버와의 통신에 실패하였습니다.", "error");
                }
            });
        }
    });
}


</script>
</html>