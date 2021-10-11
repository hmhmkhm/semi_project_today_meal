<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.model.vo.Member, login.model.dao.MemberDao"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<link href="<%= request.getContextPath() %>/resources/css/common/reset.css" rel="stylesheet">
<style>
.outer {
width: 80%;
min-width: 400px;
height: 80px;
margin: 150px auto;

}

#p1 {
display: flex;
justify-content: center;
align-items: center;
text-align: center;
margin-bottom: 30px;

font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 36px;
line-height: 42px;

/* middle green */

color: #406300;
}
#p2 {
display: flex;
justify-content: center;
align-items: center;
text-align: left;
margin-bottom: 50px;

font-family: Roboto;
font-style: normal;
font-size: 18px;
line-height: 21px;

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

.input_area {
display: flex;
justify-content: center;
	align-items: center;	


}

.input_area input[type=button] {

color:white;
width: 96px;
height: 37px;

/* brightest green */

background: #A1AD61;
border-radius: 7px;
display: block;
margin: 5px;
border: 0px;
}


</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
 
     request.setCharacterEncoding("UTF-8");
     String userId = request.getParameter("userId");
     String phone = request.getParameter("phone");
     String questionNo = request.getParameter("questionNo");
     String questionAnswer = request.getParameter("questionAnswer");
     
     String pwd = new MemberDao().findPwd(userId, phone, questionNo, questionAnswer);  
 
%>

  <form name="pwdsearch" method="post">
      <%
       if (pwd != null) {
      %>
      
      <div class="outer">
        <p id="p1">비밀번호 찾기</p>
	      <p id="p2">회원님의 비밀번호는 <%= pwd.substring(0,4) %> * * * *
	       입니다.</p>
	     <span class="input_area">
 		    <input type="button" id="btnLogin" value="로그인" onclick="location.href='<%= request.getContextPath() %>/login';"/>
       	</span>
       </div>
      <%
  } else {
	  %>
	 
      <div class="outer">
           <p id="p1">비밀번호 찾기</p>
           <p id="p2">등록된 정보가 없습니다.</p>
              <span class="input_area">
              <input type="button" id="btnback" value="다시 찾기" onClick="history.back()"/>
 		      <input type="button" id="btnjoin" value="회원가입" onclick="location.href='<%= request.getContextPath() %>/memberjoin';"/>
              </span>
          
       </div>
  
       
       <%
  }
 %> 
 </form>
 <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>