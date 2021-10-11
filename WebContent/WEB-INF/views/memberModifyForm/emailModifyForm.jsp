<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/memberModifyForm/emailModifyForm.css?ver=1.1" rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">

<%
	if(request.getAttribute("result") != null){
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert('이메일 변경 완료.');
	opener.document.location.reload();
	window.close();
	
</script>
<%			
		} else{
%>			
<script>
	alert('이메일 변경에 실패하였습니다. 인증문자를 확인해주세요.');
	let failFlg = true;

</script>		
<%			
		}
	} else{
		%>
		<script>let failFlg = false;</script>
		<%
	}
%>
</head>
<body>
<div class="emailOuter">
    <div class="emailHeader">
        <h1>이메일 변경</h1>
        <div class="emailForm">
            <form id="updateEmailForm" action="<%= request.getContextPath() %>/emailModify" method="post" onsubmit="return checkEmail();">
            	<input type="hidden" name="mac" id="mac" value=<%= request.getAttribute("mac") %>>
                <h4>현재 이메일</h4>
                <span class="input_area inputDisableType">
                    <input type="email" name="userEmail" id="userEmail" maxlength="30" value="${ uEmail }" readonly>
                </span>
                <h4>변경할 이메일</h4>
                <span class="input_area2">
                    <input type="email" name="newEmail" id="newEmail" maxlength="30" value="<%= request.getAttribute("newEmail") !=null?request.getAttribute("newEmail"):"" %>" required>
                </span>
                
                <h4>인증문자 확인</h4>
                <span class="input_area">
                    <input type="text" name="userMac" id="userMac" maxlength="6" minlength="6" value="<%= request.getAttribute("userMac") !=null?request.getAttribute("userMac"):"" %>"required>
                </span>
                <div class="btnArea">
                    <button id="updateEmailBtn">변경하기</button>
                </div>
            </form>
            <form id="form">
	        <input type="hidden" name="message" id="message">
	        <input type="hidden" name="reply_to" id="reply_to">
	        <input type="submit" id="checkEmailBtn" value="인증문자발송" >
	        </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@3/dist/email.min.js"></script>
<script type="text/javascript">emailjs.init('user_RwKtP1HNbeS8yyxzCDOU2')</script>

<script>
	const mac = document.getElementById('mac');
	
	const btn = document.getElementById('checkEmailBtn');
	const message = document.getElementById('message');
	const reply_to = document.getElementById('reply_to');
	const userEmail = document.getElementById('userEmail');
	const newEmail = document.getElementById('newEmail');
	
	if(failFlg == true){
		btn.value = '발송완료';
		btn.disabled = true;
		btn.style.backgroundColor = 'red';
		newEmail.readOnly = true;
	} else{
		let copyMac = 0;
	}
	
	let regex=/([\w\.]+)@((([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	document.getElementById('form').addEventListener('submit', function(event) {
	event.preventDefault();
	
	reply_to.value = newEmail.value;
	
	if(reply_to.value != '' && regex.test(reply_to.value)){
		if(failFlg == false){
			message.value = Math.random().toString(16).substr(2,6);
			mac.value = message.value;
		} else{
			message.value = mac.value;
		}
		
	} else{
		alert('입력한 이메일을 다시 확인해주세요.');
		return;
	}
	
	/* console.log(mac.value); */

	btn.value = 'Sending...';
	
	const serviceID = 'default_service';
	const templateID = 'template_oibpov5';
	
/*  	btn.value = '발송완료';
	btn.disabled = true;
	btn.style.backgroundColor = 'red';
	alert('인증문자 발송 완료!'); */
	
 	emailjs.sendForm(serviceID, templateID, this)
	    .then(() => {
	    btn.value = '발송완료';
        btn.disabled = true;
        btn.style.backgroundColor = 'red';
	    alert('인증문자 발송 완료!');
	    }, (err) => {
	    btn.value = 'Send Email';
	    alert(JSON.stringify(err));
	    });
	});
	
	function checkEmail(){
		
		if(btn.value == '발송완료'){
			return true;			
		} else{
			alert('인증문자를 요청하세요.');
			return false;
		}
		
	}
	
	
</script>



</body>
</html>