<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행중인 이벤트</title>
<link href="<%= request.getContextPath() %>/resources/css/event/EventDetailStyle.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<div id="container">
            <h2 style="font-size:34px; margin-top : 15px; margin-bottom:10px;">진행중인 이벤트</h2>
            <div class="notice_content">
                <div class="subject">
                    <span>제목 :  ${event.event_title } </span> 
                    <span>기간 :  ${event.term } </span>
                </div>
                <div id="eventImg_area" style="height: 1500px; text-align : center;">
                    <!-- 이벤트 이미지 영역-->
                    <img src="../resources/uploadFiles/event/${event.content }" style="width:900px; height: 1500px; ">
                </div>
                <div class="btn_area">
                    <button type="button" onclick="location.href='${contextPath}/event/list'">목록으로</button>
                </div>
            </div>
            <form name="eventForm" method="post">
				<input type="hidden" name="eno" value="${ e.eventNo }">
			</form>    
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
        </div>
</body>
</html>