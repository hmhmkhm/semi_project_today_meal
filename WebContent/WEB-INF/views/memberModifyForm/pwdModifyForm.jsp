<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/memberModifyForm/pwdModifyForm.css?ver=1.111" rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">

<%
	if(request.getAttribute("result") != null){
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert('성공적으로 비밀번호를 변경하였습니다');
	opener.document.location.reload();
	window.close();
</script>
<%			
		} else{
%>			
<script>
	alert('비밀번호 변경에 실패하였습니다. 비밀번호를 확인해주세요.')
</script>		
<%			
		}
	}
%>

</head>
<body>
<div class="pwdOuter">
    <div class="pwdHeader">
        <h1>비밀번호 변경</h1>
        <div class="pwdForm">
            <form id="updatePwdForm" action="<%= request.getContextPath() %>/pwdModify" method="post" onsubmit="return checkPwd();">
                <h4>현재 비밀번호</h4>
                <span class="input_area">
                    <input type="password" name="userPwd" id="userPwd" minlength="8" maxlength="15" required>
                </span>
                <h4>변경할 비밀번호</h4>
                <span class="input_area">
                    <input type="password" name="newPwd" id="newPwd" minlength="8" maxlength="15" required>
                </span>
                <h4>변경할 비밀번호 확인</h4>
                <span class="input_area">
                    <input type="password" name="newPwd2" id="newPwd2" minlength="8" maxlength="15" required>
                </span>
                <div class="btnArea">
                    <button id="updatePwdBtn">변경하기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
	function checkPwd(){
		const userPwd = document.getElementById('userPwd');
		const newPwd = document.getElementById('newPwd');
		const newPwd2 = document.getElementById('newPwd2');
		
		if(userPwd.value ==""|| newPwd.value =="" || newPwd2.value ==""){
			alert('비밀번호를 입력해주세요');
			return false;
		}
		
		
		if(newPwd.value != newPwd2.value){
			alert('비밀번호가 다릅니다.');
			newPwd2.select();
			return false;
		}
		
		let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
		
		if(!regex.test(newPwd.value)){
			alert('비밀번호는 영문 대소문자 숫자 특수문자 포함하여 8자 이상 입력하세요');
			newPwd.select();
			return false;
		}
		
		return true;
	}
	
</script>
</body>
</html>