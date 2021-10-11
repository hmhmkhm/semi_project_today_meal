<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/memberModifyForm/memberModifyForm.css?ver=1.114" rel="stylesheet">
<%
	if(request.getAttribute("result") != null){
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert('회원정보 수정 성공.');
</script>
<%			
		} else{
%>			
<script>
	alert('회원정보 수정 실패.')
</script>		
<%			
		}
	}
%>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="memberModifyWrapper">
    <form id="modifyForm" action="<%= request.getContextPath() %>/memberModify" method="post" onsubmit="return validate();">
        <h1>회원정보 수정</h1>
        <div class="modifyRow">
            <h4>아이디<font color="red">*</font></h4>
            <span><input class="inputDisableType" type="text" name="userId" value="<%= loginUser.getUserId() %>" readonly></span>
        </div>
        <div class="modifyRow">
            <h4>비밀번호<font color="red">*</font></h4>
            <span>
                <input class="inputDisableType" type="password" name="userPwd" value="<%= loginUser.getUserPwd() %>" readonly>
                <button id="pwdUpdateBtn" type="button"
                onclick="openPopup('<%= request.getContextPath()%>/pwdModify', 'pwdModify', 500, 500);">비밀번호 변경</button>
            </span>
        </div>
        <div class="modifyRow">
            <h4>이름<font color="red">*</font></h4>
            <span><input class="inputDisableType" type="text" name="userName" value="<%= loginUser.getUserName() %>" readonly></span>
        </div>
        <div class="modifyRow">
            <h4>연락처<font color="red">*</font></h4>
            <span><input class="inputEnableType" id="phoneNum" type="tel" name="phone" value="<%= loginUser.getPhone() %>" required></span>
        </div>
        <div class="modifyRow">
            <h4>이메일<font color="red">*</font></h4>
            <span>
                <input class="inputDisableType" type="email" name="email" value="<%= loginUser.getEmail() %>" readonly>
                <button id="emailUpdateBtn" type="button"
                onclick="openPopup('<%= request.getContextPath()%>/emailModify', 'emailModify', 500, 500);">이메일 변경</button>
            </span>
        </div>
        <div class="modifyRow">
            <h4>우편번호<font color="red">*</font></h4>
            <span>
                <input class="inputDisableType postcodify_postcode5" type="text" name="address1" value="<%= loginUser.getAddress1() %>" readonly>
                <button type="button" id="postcodify_search_button">검색하기</button>
            </span>
        </div>
        <div class="modifyRow">
            <h4>도로명주소<font color="red">*</font></h4>
            <span><input class="inputDisableType postcodify_address" type="text" name="address2" value="<%= loginUser.getAddress2() %>" readonly></span>
        </div>
        <div class="modifyRow">
            <h4>상세주소<font color="red">*</font></h4>
            <span><input class="inputEnableType postcodify_details" type="text" name="address3" value="<%= loginUser.getAddress3() %>" required></span>
        </div>
        <div class="modifyRow">
            <h4>비밀번호 찾기용 질문<font color="red">*</font></h4>
            <span>
                <select class= "inputEnableType" name = "questionNo">
                <option value="question00" <c:if test="${loginUser.questionNo == 'question00' }">selected</c:if>>질문을 선택해주세요.</option>
                <option value="question01" <c:if test="${loginUser.questionNo == 'question01' }">selected</c:if>>졸업한 초등학교는 어디입니까?</option>
                <option value="question02" <c:if test="${loginUser.questionNo == 'question02' }">selected</c:if>>가장 가고 싶은 여행지는 어디입니까?</option>
                <option value="question03" <c:if test="${loginUser.questionNo == 'question03' }">selected</c:if>>어릴적 장래희망은 무엇이었습니까?</option>
                <option value="question04" <c:if test="${loginUser.questionNo == 'question04' }">selected</c:if>>좋아하는 색상은 무슨 색입니까?</option>
                <option value="question05" <c:if test="${loginUser.questionNo == 'question05' }">selected</c:if>>키워보고 싶은 반려동물은 무엇입니까?</option>
             </select></span>
            <span><input class="inputEnableType" type="text" name="pwdAnswer" value="<%= loginUser.getQuestionAnswer() %>" required></span>
        </div>
        <div class="modifyBtnArea">
            <button id="updateBtn">수정하기</button>
        </div>
        
    </form>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<!-- jQuery와 Postcodify를 로딩한다 -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>

<script>
	
	//팝업창 호출
	function openPopup(url, title, width, height){
	
		let left = (document.body.clientWidth / 2) - (width / 2);
		// 듀얼 모니터를 위한 계산
		left += window.screenLeft;
		let top = (screen.availHeight / 2) - (height / 2);
		let options = "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top;
		window.open(url, title, options);
	}
	
	
	function validate(){
        let phoneNum = document.getElementById('phoneNum');
        let regExp = /01{1}[016789]{1}[0-9]{7,8}/;

        if(!regExp.test(phoneNum.value)){
            alert('잘못 입력된 연락처 입니다. 다시 확인 바랍니다.');
            phoneNum.select();
            return false;
        }
        return true;
    }
	
</script>	
</body>
</html>