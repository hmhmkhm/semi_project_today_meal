<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.model.vo.Member, login.model.service.MemberService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<style>
.outer {
	width: 70%;
	min-width: 500px;
	height: 350px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}
.id {

width : 416px;
	height: 50px;
	display: flex;
	justify-content: space-between;
	
}
.pwd {

width : 292px;
	height: 50px;
	display: flex;
	justify-content: space-between;
	
}
#p1 {
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
text-align: left;
margin-bottom: 30px;
padding: 10px 10px 14px 10px;
font-family: Roboto;
font-style: normal;
font-weight: normal;
font-size: 18px;
line-height: 30px;

/* middle green */

color: #406300;
}

.input_area input {

	width: 200px;
	height: 37px;
/* checkbox gray border color */

border: 1px solid #C4C4C4;
box-sizing: border-box;


}
.input_area2 input[type=submit] {

/* brightest green */

background: #A1AD61;
border-radius: 7px;
color:white;
width: 108px;
height: 83px;
border:none;
}
#remember{
width: 20px;
height: 20px;

}
.check{

width : 150px;
height: 33px;
display: flex;
justify-content: space-between;
	
}
#find {
color:white;

width: 200px;
height: 44px;
left: calc(50% - 217px/2 - 98.5px);
top: calc(50% - 44px/2 + 31px);

/* brightest green */

background: #A1AD61;
border-radius: 10px;
border:none;
}
.find {

width : 416px;
	height: 53px;
	display: flex;
	justify-content: space-between;
	
	
}
#kakaologin {
display: block;
width: 416px;
height: 48px;

}
#kakaologout {
color: white;
}

</style>

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="wrapper">
       <div class="outer">
           <form class="loginArea" action="<%= request.getContextPath() %>/login" method="post" onsubmit="return validate();">
               <p id="p1">로그인</p>
               <div class="id">
               <p id="p2">아이디</p>
               <span class="input_area"><input type="text" name="userId" id="userId"></span>
               <span class="input_area2"><input type="submit" value="로그인"></span>
               </div>
               <div class ="pwd">
               <p id="p2">비밀번호</p>
               <span class="input_area"><input type="password" name="userPwd" id="userPwd"></span>
               
               </div>
               <div class="check">
               <input type="checkbox" name="remember" id="remember">
               <label for="remember">아이디 기억하기</label>
               </div>
               <div class="find">
               <input id="find" type="button" value="아이디 찾기" onclick="location.href='<%= request.getContextPath() %>/findId';">
               <input id="find" type="button" value="비밀번호 찾기" onclick="location.href='<%= request.getContextPath() %>/findPwd';">
               </div>
          <ul>
           <li onclick="kakaoLogin();">
              <a href="javascript:void(0)">
                  <img id="kakaologin" src="<%= request.getContextPath() %>/resources/images/login/kakaologin.png">
               </a>
	        </li>
	        <li onclick="kakaoLogout();">
               <a href="javascript:void(0)" id="kakaologout">로그아웃</a>
	        </li>
	      </ul> 
          </form>
       </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <script>
    function validate(){
    	let userId = document.getElementById('userId');
    	let userPwd = document.getElementById('userPwd');
    	
    	if(!userId.value.trim().length){
    		alert("아이디를 입력하세요.");
    		userId.focus();
    		return false;
    	}
    	
    	if(!userPwd.value.trim().length){
    		alert("비밀번호를 입력하세요.");
    		userPwd.focus();
    		return false;
    	}
    	
    	return true;
    }    
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/login/rememberUserId.js"></script>
    
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
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
</script>
</body>
</html>