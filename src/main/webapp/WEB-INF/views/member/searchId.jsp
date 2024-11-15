<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>
  #idSearchPopup {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 300px;
    padding: 20px;
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);

  }
  .popup-content {
    text-align: center;
  }
</style>
</head>
<body>
	<form method="post">
		<!-- 팝업 HTML -->
	 	<div id="idSearchPopup" >
		  <div class="popup-content">
		    <h3>아이디 찾기</h3>
		    <div class="input-wrap">
		      <label for="memberName">이름 입력</label>
		      <input type="text" id="popupMemberName" placeholder="이름을 입력해주세요" />
		    </div>
		    <div class="input-wrap">
		      <label for="memberPhone">전화번호 입력</label>
		      <input type="text" id="popupMemberPhone" placeholder="전화번호를 입력해주세요" />
		    </div>
		    <div class="btn-wrap">
		      <button type="button" onclick="searchId()">아이디 찾기</button>
		    </div>
		  </div>
		</div>
	</form>
	<script>
	function showIdSearchPopup() {
        document.getElementById("idSearchPopup").style.display = "block";
    }

    // 아이디 찾기 함수
    function searchId() {
        var memberName = document.getElementById("popupMemberName").value;
        var memberPhone = document.getElementById("popupMemberPhone").value;

        if (!memberName || !memberPhone) {
            alert("이름과 전화번호를 입력해 주세요.");
            return;
        }

        $.ajax({
            url: '/member/searchId', // 서블릿 URL
            type: 'POST',
            data: {
                memberName: memberName,
                memberPhone: memberPhone
            },
            success: function(response) {
                if (response) {
                    alert("아이디: " + response);
                    closePopup(); // 팝업 닫기
                } else {
                    alert("일치하는 회원이 없습니다.");
                }
            },
            error: function() {
                alert("서버에 오류가 발생했습니다.");
            }
        });
	}
	</script>
</body>
</html>
