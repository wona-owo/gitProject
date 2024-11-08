<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<main class="content">
			<section class="section join-wrap">
				<div class="page-title">회원가입</div>
				<form action="/member/join" method="post" autocomplete="off" onsubmit="return joinValidate()">
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberId">아이디</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberId" name="memberId" placeholder="영어 대소문자, 숫자 포함 4~10글자" maxlength="10"/>
							<button type="button" id="idDuplChkBtn" class="btn-primary">중복체크</button>
						</div>
						<p id="idMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPw">비밀번호</label>
						</div>
						<div class="input-item">
							<input type="password" id="memberPw" name="memberPw" placeholder="영어 대소문자, 숫자, 특수문자 포함 8~20글자" maxlength="20"/>
						</div>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPwConfirm">비밀번호 확인</label>
						</div>
						<div class="input-item">
							<input type="password" id="memberPwConfirm" maxlength="20"/>
						</div>
						<p id="pwMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberName">이름</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberName" name="memberName" placeholder="한글 2~10글자" maxlength="10">
						</div>
						<p id="nameMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberNick">닉네임</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberNick" name="memberNick" placeholder="한글,영어,숫자,특수문자 포함 4~10글자" maxlength="10">
							<button type="button" id="nickDuplChkBtn" class="btn-primary">중복체크</button>
						</div>
						<p id="nickMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPhone">전화번호</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberPhone" name="memberPhone" placeholder="전화번호(010-0000-0000)" maxlength="13">
						</div>
						<p id="phoneMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberAddr">주소</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberAddr" name="memberAddr" maxlength="200">
						</div>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberGender">성별</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberGender" name="memberGender" placeholder="M,F">
						</div>						
					</div>										
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberEmail">이메일</label>
						</div>
						<div class="input-item">
							<input type="email" id="memberEmail" name="memberEmail">
						</div>
						<p id="emailMessage" class="input-msg"></p>
					</div>
					<!--<div class="input-wrap">
						<div class="input-title">
							  <label for="adultConfirm">성인인증</label>
						</div>
						<div class="input-item">
							<input type="text" id="adultConfirm" name="adultConfirm">
						</div>
						<p id="adultMessage" class="input-msg"></p>
					</div>	-->																											
					<div class="join-button-box">
						<button type="submit" class="btn-primary lg">회원가입</button>
					</div>
				</form>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>	
	<script>
	const checkObj = {
		"memberId" : false,
		"idDuplChk" : false,
		"nickDuplChk" : false,
		"memberPw" : false,
		"memberPwConfirm" : false,
		"memberName" : false,
		"memberNick" : false,
		"memberPhone" : false		
	}
	
	const memberId = $('#memberId'); 
	const idMessage = $('#idMessage');
	

	memberId.on('input',function(){
        checkObj.idDuplChk = false;
        idMessage.removeClass('valid');
        idMessage.removeClass('invalid');
        
        const regExp = /^[a-zA-Z0-9]{4,10}$/;
        
        if(regExp.test($(this).val())){ 
            idMessage.html("");
            idMessage.addClass("valid");
            checkObj.memberId = true;
        }else{
            idMessage.html("영어, 숫자 4~10글자 사이로 입력하세요");
            idMessage.addClass("invalid");
            checkObj.memberId = false;
        }
    });
	
	
	$('#idDuplChkBtn').on('click', function(){
        if(!checkObj.memberId){
            msg("알림", "유효한 아이디를 입력한 후 중복체크를 진행하세요", "error");
            return false;
        }
        $.ajax({
            url : "/idDuplChk",
            data : {"memberId" : memberId.val()},
            type : "get", 
            success : function(res){
                if(res == 0){
                    msg("알림", "사용 가능한 아이디입니다", "success");
                    checkObj.idDuplChk = true;
                }else {
                    msg("알림", "중복된 아이디가 존재합니다", "warning");
                    checkObj.idDuplChk = false;
                }
            },
            error : function(){
                console.log('ajax 오류 발생');
            }
        });
    });
	
	const memberNick = $('#memberNick'); 
	const nickMessage = $('#nickMessage');
	
	memberNick.on('input',function(){
		
		checkObj.nickDuplChk = false;
		
		nickMessage.removeClass('valid');
		nickMessage.removeClass('invalid');
		
		const regExp = /^[가-힣a-zA-z0-9!@#$%^&*()-_=+]{4,10}$/;
		
		if(regExp.test($(this).val())){ 
			nickMessage.html("");
			nickMessage.addClass("valid");
			checkObj.memberNick = true;
		}else{
			nickMessage.html("한글,영어,숫자,특수문자 4~10글자 사이로 입력하세요")
			nickMessage.addClass("invalid");
			checkObj.memberNick = false;
		}
	});
	
	$('#nickDuplChkBtn').on('click', function(){
		
		if(!checkObj.memberNick){
			msg("알림", "유효한 닉네임을 입력한 후 중복체크를 진행하세요", "error");
			return false; 
		}
		
		$.ajax({
			url : "/nickDuplChk",
			data : {"memberNick" : memberNick.val()},
			type : "get", 
			success : function(res){
				if(res == 0){
					msg("알림", "사용 가능한 닉네임입니다", "success");
					checkObj.nickDuplChk = true;
				}else {
					msg("알림", "중복된 닉네임이 존재합니다", "warning");
					checkObj.nickDuplChk = false;
				}
			},
			error : function(){
				console.log('ajax 오류 발생');
			}
		})
		
	});
	
	const memberPw = $('#memberPw');
	const pwMessage = $('#pwMessage');
	
	memberPw.on('input',function(){
        pwMessage.removeClass('valid');
        pwMessage.removeClass('invalid');
        
        const regExp = /^[a-zA-Z0-9!@#$%^&*()-_=+]{8,20}$/;
        
        if(regExp.test($(this).val())){
            checkObj.memberPw = true;
            pwMessage.html("");
            pwMessage.addClass("valid");
        }else{
            pwMessage.html("비밀번호 형식이 유효하지 않습니다");
            pwMessage.addClass("invalid");
            checkObj.memberPw = false;
        }
    });
	
	const memberPwConfirm = $('#memberPwConfirm');
    memberPwConfirm.on('input', function(){
        if(memberPwConfirm.val() === memberPw.val()){
            pwMessage.addClass('valid');
            pwMessage.html('');
            checkObj.memberPwConfirm = true;
        }else{
            pwMessage.addClass('invalid');
            pwMessage.html('비밀번호가 일치하지 않습니다');
            checkObj.memberPwConfirm = false;
        }
    });
	
	const memberPhone = $('#memberPhone');
	const phoneMessage = $('#phoneMessage');
	
	memberPhone.on('input', function(){
		phoneMessage.removeClass('valid');
		phoneMessage.removeClass('invalid');
		
		const regExp = /^010-\d{3,4}-\d{4}$/;
		
		if(regExp.test($(this).val())){
			phoneMessage.addClass('valid');
			phoneMessage.html("");
			checkObj.memberPhone = true;
		}else{
			phoneMessage.addClass('invalid');
			phoneMessage.html("전화번호 형식이 유효하지 않습니다");
			checkObj.memberPhone = false;
		}
	});
	
	const memberName = $('#memberName');
	const nameMessage = $('#nameMessage');
	
	memberName.on('input', function(){
		nameMessage.removeClass('valid');
		nameMessage.removeClass('invalid');
		
		const regExp = /^[가-힣]{2,10}$/;
		
		if(regExp.test($(this).val())){
			nameMessage.addClass('valid');
			nameMessage.html("");
			checkObj.memberName = true;
		}else{
			nameMessage.addClass('invalid');
			nameMessage.html("이름 형식이 유효하지 않습니다");
			checkObj.memberName= false;
		}
	});
	
	function joinValidate(){
        let str = "";
        for(let key in checkObj){
            if(!checkObj[key]){
                switch(key){ 
                    case "memberId" : str = "아이디 형식"; break;
                    case "idDuplChk" : str = "아이디 중복체크를 진행하세요"; break;
                    case "nickDuplChk" : str = "닉네임 중복체크를 진행하세요"; break;
                    case "memberPw" : str = "비밀번호 형식"; break;
                    case "memberPwConfirm" : str = "비밀번호 확인 형식"; break;
                    case "memberPhone" : str = "전화번호 형식"; break;
                }       
                str += "이 유효하지 않습니다";
                msg("회원가입 실패", str, "error");
                return false;
            }
        }
        return true;
    }

    function msg(title, text, icon){
        swal({
            title : title,
            text : text,
            icon : icon
        });     
    }
	</script>
</body>
</html>