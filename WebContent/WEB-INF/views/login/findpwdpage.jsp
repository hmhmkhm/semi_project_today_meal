<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="<%= request.getContextPath() %>/resources/css/common/reset.css" rel="stylesheet">
<style>
.outer {
width: 80%;
min-width: 400px;
height: 500px;
margin: 10px auto;
display: flex;
justify-content: center;
align-items: center;
}

#p1 {
text-align: center;
margin-bottom: 70px;

font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 36px;
line-height: 42px;

/* middle green */

color: #406300;
}
#p2 {
text-align: left;
margin-bottom: 3px;

font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 18px;
line-height: 21px;

color: #000000;

}

#p3 {
text-align: left;
margin-bottom: 30px;


font-family: Roboto;
font-style: normal;
font-weight: normal;
font-size: 12px;
line-height: 14px;

color: #000000;
}

#p4 {

width: 105px;
height: 20px;
text-align: left;
margin-bottom: 30px;
padding: 10px 10px 14px 0px;

font-family: Roboto;
font-style: normal;
font-weight: normal;
font-size: 12px;
line-height: 14px;

color: #000000;
}
.input_area input {

width: 375px;
height: 37px;
border: 0px;
/* checkbox gray border color */
border: 1px solid #C4C4C4;
box-sizing: border-box;


}
.input_area1 input {

width: 375px;
height: 37px;
border: 0px;
/* checkbox gray border color */
border: 1px solid #C4C4C4;
box-sizing: border-box;
margin-left: 143px;

}
#questionNo {

width: 375px;
height: 37px;
border: 0px;
/* checkbox gray border color */
border: 1px solid #C4C4C4;
box-sizing: border-box;


}

.input_area2 {
display: flex;
justify-content: center;
	align-items: center;
}

.input_area2 input[type=button] {
color:white;
width: 96px;
height: 37px;

/* brightest green */

background: #A1AD61;
border-radius: 7px;
display: block;
margin-top: 20px;

border: 0px;
}

.area {
width : 518px;
height: 50px;
display: flex;
justify-content: space-between;
	
}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="wrapper">
       <div class="outer">
           <form name="findPwdArea" action="<%= request.getContextPath() %>/findpwd" method="post" onsubmit="return validate();">
               <p id="p1">비밀번호 찾기</p>
               <p id="p2">등록된 휴대폰 번호와 질문으로 찾기</p>
               <p id="p3">가입 당시 입력한 전화번호와 비밀번호 찾기 질문에 답변을 작성하여 비밀번호를 확인해주세요.</p>
               <div class="area">
               <p id="p4">아이디</p>
               <span class="input_area"><input type="text" name="userId" id="userId" ></span>
               </div>
               <div class="area">
               <p id="p4">연락처</p>
               <span class="input_area"><input type="text" name="phone" id="phone" placeholder="(-없이)01012345678"></span>
               </div>
               <div class="area">
               <p id="p4">비밀번호 찾기 질문</p>
               <select id= "questionNo" name = "questionNo" >
                  <option value="question00" selected>질문을 선택해주세요.</option>
                  <option value="question01">졸업한 초등학교는 어디입니까?</option>
                  <option value="question02">가장 가고 싶은 여행지는 어디입니까?</option>
                  <option value="question03">어릴적 장래희망은 무엇이었습니까?</option>
                  <option value="question04">좋아하는 색상은 무슨 색입니까?</option>
                  <option value="question05">키워보고 싶은 반려동물은 무엇입니까?</option>
               </select>
               </div>
               <div class="area">
               <span class="input_area1"><input type="text" name="questionAnswer" id="questionAnswer" placeholder="답변" ></span>
               </div>
               <span class="input_area2"><input type="button" name="enter" value="다음" onClick="pwd_search()"></span>
           </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <script>
    function pwd_search() { 
	 	var frm = document.findPwdArea;

	 	if (frm.userId.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }

		 if (frm.phone.value.length != 11) {
			  alert("핸드폰번호를 정확하게 입력해주세요");
			  return;
		 }
		 if (frm.questionAnswer.value.length < 1) {
			  alert("답변을 입력해주세요");
			  return;
			 }

	 frm.method = "post";
	 frm.action = "findpwdresult"; //넘어간화면
	 frm.submit();  
	 }
    </script>
</body>
</html>