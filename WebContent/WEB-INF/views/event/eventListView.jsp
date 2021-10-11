<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행중인 이벤트</title>
<link href="<%= request.getContextPath() %>/resources/css/event/eventStyle.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
	}
.onmouseover {
	background: #f3f5f7;
	cursor: pointer;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<div id="container">
                    <h2 style="font-size:32px">진행중인 이벤트</h2>
                    <div class="search_area">
                        <form method="get" action="${ contextPath }/event/list">
                            <span class="input_area"> 
                                <input class="inputSearch" type="search" name="searchValue" value="${ param.searchValue }">
                            </span>
                            <button type="submit">검색</button>
                        </form>
                    </div>
                    <div class="board_list" onclick="">
                        <ul class="board_header">
                            <li class="title">제목</li>
                            <li class="term">기간</li>
                            <li class="count">조회수</li>
                        </ul>
                       <c:forEach var="e" items="${ eventList }">
                        <ul class="board_ul" onclick="detailView(${e.eno})">
                            <li class="title">${ e.event_title }</li>
                            <li class="term">${ e.term }</li>
                            <li class="count">${e.count}</li>
                        </ul>
                        </c:forEach>
                        
                        
                    </div>
                <!-- </div> -->
            <!-- </div> -->
            
            <div class="paging">
				<jsp:include page="/WEB-INF/views/common/paging/paging.jsp"/>
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
        </div>
        <script>
            function detailView(eno){
               location.href='${contextPath}/event/detail?eno=' + eno; 
            }
         </script>
         <script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제거 처리
		const boardList = document.querySelector(".board_list");
		
		boardList.addEventListener('mouseover', function(){
			console.log('mouseover');
			console.log(event.target);
			
			if(event.target.classList.contains('board_ul')) //ul이라면
				event.target.classList.add('onmouseover');	//클래스 추가
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		boardList.addEventListener('mouseout', function(){
			console.log('mouseout');
			console.log(event.target);
			
			if(event.target.classList.contains('board_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
	
	</script>
</body>
</html>