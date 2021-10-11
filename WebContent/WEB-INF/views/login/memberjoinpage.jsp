<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>   
<style>
.outer {
	width: 70%;
	min-width: 500px;
	height: 450px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}
#p1 {
text-align: center;
margin-bottom: 30px;

font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 36px;
line-height: 80px;

/* middle green */

color: #406300;
}
#join {
color:white;
font-weight: bold;
font-size: 18px;
width: 417px;
height: 54px;
left: calc(50% - 217px/2 - 98.5px);
top: calc(50% - 44px/2 + 31px);
border:none;
/* brightest green */

background: #A1AD61;
border-radius: 10px;

}
#startkakao {
    width: 417px;
    height: 54px;
    margin: 10px auto;
    display: flex;
    justify-content: center;
    align-items:center;
     height: 70vf;
 }
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="wrapper">
    <div class="outer">
    <form class="memberJoin" action="<%= request.getContextPath() %>/memberjoin" method="post" onsubmit="return validate();">
          <p id="p1">회원가입</p>
          
          <input id="join" type="button" value="일반 회원가입" onclick="location.href='<%= request.getContextPath() %>/join';">
          <ul>
           <li onclick="kakaoLogin();">
              <a href="javascript:void(0)">
              <img id="startkakao" src="<%= request.getContextPath() %>/resources/images/login/startkakao.png">
              </a>
	        </li>
	      </ul>
                  
    </form>
    </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />  
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('1e8f882bb483b14f8ef45b0c4a808623'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
              var userName = response.properties.nickname;
        	  
        	  console.log("userName", userName);
        	  location.href="<%= request.getContextPath() %>";
          },
          fail: function (error) {
            console.log(error)
          },
        })
        var apiToken = response.access_token;
        console.log("apiToken", apiToken);
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }

</script>
</body>
</html>