<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 배송지</title>
<link href="<%= request.getContextPath() %>/resources/css/common/reset.css" rel="stylesheet">
<style>
.outer{
width:60%;
min-width : 650px;
margin:auto;
margin-top : 10px;
margin-bottom : 50px;
}
.kakaoDeliveryArea{
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

width: 70px;
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

.input_area {
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
	
#joineBtn {
width: 103px;
height: 37px;
color:white;
background: #A1AD61;
border-radius: 7px;
margin-left: 80px;
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
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="wrapper">
       <div class="outer">
            <form class="kakaoDeliveryArea" action="<%= request.getContextPath() %>/delivery" method="post" onsubmit="return validate();">
               <p id="p1">기본 배송지 입력</p>
               
               <button id="postcodify_search_button" type="button">검색</button>
               <p id="p2">우편번호
               <span class="input_area">*<input type="text" name="address" class="postcodify_postcode5" readonly></span>
               </p>
               <p id="p2">도로명주소
               <span class="input_area">*<input type="text" name="address" class="postcodify_address" readonly></span>
               </p>
               <p id="p2">상세주소
               <span class="input_area">*<input type="text" name="address" class="postcodify_details"></span>
               </p>
               <div class="joinBtnArea">
               <button id="joineBtn">가입하기</button>
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
</body>
</html>