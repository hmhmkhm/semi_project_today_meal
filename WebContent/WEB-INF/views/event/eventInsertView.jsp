<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 작성</title>
<link href="<%= request.getContextPath() %>/resources/css/event/eventInsertStyle.css"rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">
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
		<h2 id="title" style="font-size:20px;">이벤트 게시판 관리</h2>
		<div class="board_content">
			<form method="post" action="${ contextPath }/event/insert" enctype="multipart/form-data">
				<div class="content">
					<div class="titleArea">
						<h4>제목<span id="require">*</span><span class="input_area" style="margin-left : 40px;">
						<input type="text" name="title" required></span></h4><br>
					</div>
					<div>
					  &nbsp;기간 <span id="require">*</span>&nbsp;&nbsp;
					<span class="select_term" style="margin-left : 23px;">
					<span class="input_area" style="margin-left : 5px;">
						<input type="text" name="term" required></span>
					</span>
					</div>
						
					<h4 style="margin-top:20px;">
						<span></span> <span class="title_span">&nbsp;</span>첨부파일 <span
							id="require">*</span>&nbsp; <input type="file" name="img"
							id="evnetImg">
					</h4>
					<h4 style="margin-top:20px;">
						<span class="title_span">&nbsp;&nbsp;</span>내용<span id="require">*</span>
					</h4>
					<div id="event_img">
						<!-- 이미지 담는 div-->
					</div>
				</div>

				<div class="btn_area">
					<button type="submit">등록</button>
				</div>
			</form>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script>
        document.getElementById('evnetImg').addEventListener('change', function(){
            if(this.files && this.files[0]){
                let reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function(){
                    console.log(reader.result);
                    document.getElementById('event_img').innerHTML = "<img src='" + reader.result + "' style='width:900px; height:1000px'>";
                }
            }
        });
    </script>
</body>
</html>