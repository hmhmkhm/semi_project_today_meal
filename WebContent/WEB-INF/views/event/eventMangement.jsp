<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 게시판 관리</title>
<link href="<%= request.getContextPath() %>/resources/css/event/AdminEventList.css" rel="stylesheet">
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
		<nav class="snb">
			<jsp:include page="/WEB-INF/views/common/adminPage/subMenu.jsp" />
		</nav>
		<div class="contents">
			<div class="inner">
                <div id="admin_evnet_title">
                    <h2 style="font-weight:bold; font-size:20px;">이벤트 게시판 관리</h2>
                </div>
                <div id="admin_insert"><button id="insert_btn" onclick="location.href='${contextPath}/event/insert'">글 작성</button></div>
                <div class="event_list">
                    <ul class="event_header">
                        <li class="title">제목</li>
                        <li class="mo_del">수정/삭제</li>
                    </ul>
                     <c:forEach var="e" items="${ eventList }">
                    <ul class="event_ul" onclick="">
                    	
                        <li class="title"> ${ e.event_title } </li>
                        <li class="modifyEvent"><button class="btn_mod" onclick="updateEvent(${e.eno})">수정</button></li>
                        <li class="deleteEvent"><button class="btn_del" onclick="deleteEvent(${e.eno})">삭제</button></li>
                    </ul>
                      <form name="EventForm" method="post">
						<input type="hidden" name="eno" value="${ e.eno }">
						<input type="hidden" name="title" value="${ e.event_title }">
						<input type="hidden" name="term" value="${ e.term }">
						<input type="hidden" name="content" value="${ e.content }">
					</form>
					
                    </c:forEach>
                </div>
                <div class="paging">
					<jsp:include page="/WEB-INF/views/common/paging/paging.jsp">	
						<jsp:param name="url" value="${ contextPath }/admin/eventlist?"/>
					</jsp:include>
			</div>
			</div>
			
		</div>
	</div>
	
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		function detailView(eno){
	        location.href='${contextPath}/event/detail?eno=' + eno; 
	     }
		function updateEvent(eno){
			//document.forms.EventForm.action = '${contextPath}/event/updateView';
			//document.forms.EventForm.submit();
			location.href='${contextPath}/event/updateView?eno=' + eno; 
		}
		function deleteEvent(){
			if(confirm('이 게시글을 삭제하시겠습니까?')){
				document.forms.EventForm.action = '${contextPath}/event/delete';
				document.forms.EventForm.submit();
			}
		}
	</script>
</body>
</html>