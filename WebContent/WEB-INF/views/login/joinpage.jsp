<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
<link href="<%= request.getContextPath() %>/resources/css/common/reset.css" rel="stylesheet">
<style>
.outer{
width:60%;
min-width : 650px;
margin:auto;
margin-top : 10px;
margin-bottom : 50px;
}
.joinArea{
width : 465px;
margin: auto;
padding: 10px;

}
#p1 {
display: flex;
justify-content: center;
align-items: center;


width: 375px;
font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 36px;
line-height: 150px;

/* middle green */

color: #406300;
}
#p2 {

width: 315px;
height: 30px;
text-align: left;
margin-bottom: 5px;

padding: 10px 10px 25px 0px;

font-family: Roboto;
font-style: normal;
font-weight: normal;
font-size: 12px;
line-height: 14px;

color: #000000;
}

#starColor{
color: #FF0000;
}
#id_check{
color: #FF0000;
}
.input_area input {
display: flex;
justify-content: center;
align-items: center;
margin-top:5px;
width: 375px;
height: 37px;
border: 0px;

/* checkbox gray border color */
border: 1px solid #C4C4C4;
box-sizing: border-box;


}
.joinBtnArea {
width: 200px;
text-align:center;
padding : 50px;
}
	
#joinBtn {
width: 103px;
height: 37px;
border : 0px;
color:white;
background: #A1AD61;
border-radius: 7px;
margin-left : 80px;
margin-top: 50px;
	}
#postcodify_search_button{
width: 81px;
height: 35px;

/* darkest black */

background: #294401;
border-radius: 7px;
color:white;

margin-top: 30px;
float: right;
}
#idCheck{
width: 81px;
height: 35px;

/* darkest black */

background: #294401;
border-radius: 7px;
color:white;

margin-top: 30px;

float: right;
}
#questionNo {

width: 375px;
height: 37px;
border: 0px;
/* checkbox gray border color */
border: 1px solid #C4C4C4;
box-sizing: border-box;
margin-top:5px;

}
label #pwdResult{
width: 300px;
height: 30px;
}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="wrapper">
       <div class="outer">
            <form name="joinArea" class="joinArea" action="<%= request.getContextPath() %>/join" method="post" onsubmit="return validate();">
               <p id="p1">일반 회원가입</p>
               <button id="idCheck" type="button">중복확인</button>
               <p id="p2">아이디
               <span class="input_area" id="id_check">*<input type="text" maxlength="12" name="userId" id="userId" placeholder="영문 소문자, 숫자 포함 6~12자리" required></span>
               </p>
               
               <p id="p2">비밀번호
               <span class="input_area" id="starColor">*<input type="password" maxlength="15" id="userPwd" name="userPwd" placeholder="영문 대소문자, 숫자, 특수문자 포함 8자리 이상" required></span>
               </p>
               
               <p id="p2">비밀번호 확인
               <span class="input_area" id="starColor">*<label id="pwdResult"></label><input type="password" maxlength="15" id="userPwd2" name="userPwd2" required></span>
               </p>
               
               <p id="p2">이름
               <span class="input_area" id="starColor">*<input type="text" maxlength="5" name="userName" required></span>
               </p>
               
               <p id="p2">연락처
               <span class="input_area" id="starColor">*<input type="tel" maxlength="11" name="phone" placeholder="(-없이)01012345678" required></span>
               </p>
               
               <p id="p2">이메일
               <span class="input_area" id="starColor">*<input type="email" name="email" required></span>
               </p>
               
               <button id="postcodify_search_button" type="button">검색</button>
               <p id="p2">우편번호
               <span class="input_area" id="starColor">*<input type="text" name="address1" class="postcodify_postcode5" readonly></span>
               </p>
               
               <p id="p2">도로명주소
               <span class="input_area" id="starColor">*<input type="text" name="address2" class="postcodify_address" readonly></span>
               </p>
               <p id="p2">상세주소
               <span class="input_area" id="starColor">*<input type="text" name="address3" class="postcodify_details" required></span>
               </p>
               
               <p id="p2">비밀번호 찾기 질문 <span id="starColor">*
               <select id= "questionNo" name = "questionNo" required>
                  <option id= "questionNo" value="question00">질문을 선택해주세요.</option>
                  <option id= "questionNo" value="question01">졸업한 초등학교는 어디입니까?</option>
                  <option id= "questionNo" value="question02">가장 가고 싶은 여행지는 어디입니까?</option>
                  <option id= "questionNo" value="question03">어릴적 장래희망은 무엇이었습니까?</option>
                  <option id= "questionNo" value="question04">좋아하는 색상은 무슨 색입니까?</option>
                  <option id= "questionNo" value="question05">키워보고 싶은 반려동물은 무엇입니까?</option>
               </select>
               </span>
               </p>
               
               <span class="input_area"><input type="text" name="questionAnswer" id="answer" placeholder="답변" required></span>
              
               
               <div class="joinBtnArea">
               <button type="submit" id="joinBtn">가입하기</button>
               </div>
           </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <!-- jQuery와 Postcodify를 로딩한다 -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

    <!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
    <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
    <script>
    // 비밀번호 확인
        userPwd.addEventListener('input', () => {
            if(userPwd.value != userPwd2.value){
                userPwd.classList.add('invalid');
                userPwd2.classList.add('invalid');
                pwdResult.innerHTML = ' 비밀번호가 일치하지 않습니다.';
            } else {
                userPwd.classList.remove('invalid');
                userPwd2.classList.remove('invalid');
                pwdResult.innerHTML = '';

            }
        });

        userPwd2.addEventListener('input', () => {
            if(userPwd.value != userPwd2.value){
                userPwd.classList.add('invalid');
                userPwd2.classList.add('invalid');
                pwdResult.innerHTML = ' 비밀번호가 일치하지 않습니다.';
            } else {
                userPwd.classList.remove('invalid');
                userPwd2.classList.remove('invalid');
                pwdResult.innerHTML = '';

            }
        });
        // 아이디, 비밀번호 유효성 검사
        document.forms.joinArea.onsubmit = () => {
            if(!check(/^[a-z][A-Za-z\d]{5,11}$/, document.getElementById('userId'),
                "아이디는 영문 소문자, 숫자 포함하여 6~12자 입력하세요."))
                return false;  

           
            if(!check(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/,
            document.getElementById('userPwd'),
            "비밀번호는 영문 대소문자, 숫자, 특수문자 포함하여 8자 이상 입력하세요."))
            return false;
        };

        function check(regEpx, input, msg) {
            if(regExp.test(input.value))
                return true;
            alert(msg);
            input.value = '';
            input.focus();
            return false;

        } 
    
    $("#idCheck").click(function(){
    	var userId = $("[name=userId]");
    	var isUsable = false;
        
    	if(!userId || userId.val().length < 6){
    		alert('아이디는 영문 소문자, 숫자 포함하여 6~12자 입력하세요.');
    		userId.focus();
    	} else {
    		$.ajax({
    			url : "${ contextPath }/idCheck",
    			type : "post",
    			data : { userId : userId.val() },
    			success : function(result){
    				console.log(result);
    				if(result == "fail"){
    					alert("사용할 수 없는 아이디입니다.");
    					userId.focus();
    				} else {
    					if(confirm('사용 가능한 아이디 입니다. 사용하시겠습니까?')){
    						userId.attr('readonly', true);
    						isUsable = true;   
    					} else {
    						userId.attr('readonly', false);
    						userId.focus();
    						isUsable = false;    
    					}
    				}
    				if(isUsable){
    					$("#joinBtn").removeAttr("disabled");
    				} else {
    					$("#joinBtn").attr("disabled", truea);
    				}
    			},
    			error : function(e){
    				console.log(e);
    			}
    		});
    	}
    });
</script>
</body>
</html>