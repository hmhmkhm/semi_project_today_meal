<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>마이페이지 - 리뷰관리</title>
    <style>
    	* {
    	font-family: 'Noto Sans KR', sans-serif;
    	}
    	.onmouseover {
			background: #f3f5f7;
			cursor: pointer;
		}
        #container {
            width: 1100px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
        }

        .snb {
            width: 200px;
            height: 2500px;
        }

        .contents {
            width: 888px;
            height: 100%; /*2500px -> 100%*/
        }

        .inner {
            margin-top: 30px;
            margin-left: 27px;
            width: 836px;
            height: 100%;
        }

        #review_title {
            text-align: center;
        }

        ul,
        li {
            margin: 0;
            padding: 0;
            font-size: 14px;
        }

        .review_list {
            margin: 20px 30px;
            min-height: 565px;
            /* margin: 0 auto; */
        }

        .review_list>ul {
            border-bottom: 1px solid #f3f5f7;
            height: 50px;
            line-height: 50px;
            display: flex;
            justify-content: space-around;
            list-style: none;
        }

        .review_list>ul.last {
            border: 0;
        }

        .review_list>ul>li {
            text-align: center;
        }

        .review_header {
            background-color: #E5E5E5;
            font-weight: bold;
        }

        .review_list .review_content {
            width: 250px;
        }

        .review_list .insert_date {
            width: 80px;
        }

        .review_list .title {
            width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .sort_list li {
            display: inline-block;
            text-align: right;
        }

        .sort_list .selected {
            font-weight: bold;
        }

        #sort {
            text-align: right;
            margin-right: 35px;
        }

        .review_ul {
            display: inline-block;
        }
        
        .title1,
        .insert_date1 {
            padding-top: 30px;
        }

        .modal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: scroll;
        }

        .modal .bg {
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
        }

        .modalBox {
            position: absolute;
            background-color: #fff;
            width: 800px;
            height: 600px;
            padding: 15px;
            overflow: scroll;
        }
		.modalBox button {
            display: inline-block;
            width: 80px;
            margin: 0 auto;
            padding: 10px;
            border: 0px;
            margin-top: 20px;
            border-radius: 7px;
        }

        .hidden {
            display: none;
        }

        .modifybtn {
            background: #5C9200;
            color: white;
        }

        .deletebtn {
            background-color: #294401;
            color: white;
        }

        h2 {
            text-align: center;
        }

        .reviewdate {
            text-align: right;
        }

        .reviewDate {
            border-bottom: 1px solid #EBEBEB;
            width: 700px;
            margin: 0 auto;
        }

        .reviewImg {
            width: 300px;
            height: 300px;
            margin: 0 auto;
            border: 1px solid black;
            margin-top: 10px;
        }
        .closeArea img{
           float: right;
           
        }
        .closeArea img:hover{
           cursor: pointer;
        }
        .btnarea {
            text-align: center;
            border-top: 1px solid #EBEBEB;
            width: 700px;
            margin: 0 auto;
        }
        #pContent{
            text-align: center;
            word-break:break-all;
        }
        .contentArea {
            width: 700px;
            margin: 0 auto;
            margin-top: 20px;
        }
        .reviewTtile {
            width: 700px;
            margin: 0 auto;
        }
        #review_img {
            width: 300px;
            height: 300px;
        }
        .fileArea {
        	padding-left : 300px;
        	margin-top : 10px;
        }
        
        .recentList:hover {
        	cursor : pointer;
        }
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/common/top.jsp" />
    <div id="container">
    <jsp:include page="/WEB-INF/views/common/mypageSubMenubar.jsp" />
        <nav class="snb"></nav>
        <div class="contents">
            <div class="inner">
                <div id="review_title">
                    <h2 style="font-size : 20px;">리뷰 관리</h2>
                </div>
                <div class="reivew_sort" id="sort">
                    <ul class="sort_list">
                        <li class="recentList"><a>최신순</a> </li>
                        <li class="nameDesc">| <a href="${contextPath }/mypage/reviewDesc" style="text-decoration-line : none; color:black;">상품명 내림차순</a> | </li>
                        <li class="nameAsc"><a href="${contextPath }/mypage/reviewAsc" style="text-decoration-line : none; color:black;">상품명 오름차순</a></li>
                    </ul>
                </div>
                <div class="review_list">
                    <ul class="review_header">
                        <li class="title">상품명</li>
                        <li class="review_content">리뷰내용</li>
                        <li class="insert_date">작성일</li>
                    </ul>
                    <c:forEach var="r" items="${review }">
                    <div class="ReviewDiv" style="display:inline-block; width:776px;"><ul class="review_ul" onclick="openModal(this)" style="height: 80px; display: flex; justify-content: space-around;">
	                        <li class="title title1" style="text-align:center;">${r.productName}</li>
	                        <li class="review_content" style="margin:0px; padding: 0px; padding-top:27px; text-align:center; word-break:break-all;">
	                            <span>${r.reviewText}</span>
	                        </li>
	                        <li class="insert_date insert_date1">${r.reviewRegister }</li>
	                    </ul><div class="tteess"><form method="post" action="" name="reviewForm" enctype="multipart/form-data"><div class="modal hidden"><div class="bg"></div><div class="modalBox"><div class="closeArea"><img class="closeBtn" src="../resources/images/review/close.png" alt="" width="20px" height="20px"></div>
	                                <div class="reviewTtile">
	                                	
	                                    <h2 style="font-size:20px;">내가 쓴 리뷰</h2>
	                                </div>
	                                <div class="reviewDate">
	                                    <p class="reviewdate">작성일  &nbsp; : &nbsp; ${r.reviewRegister }</p>
	                                </div>
	                                
	                                <div class="reviewImg" id="attachimg">
	                                    <img id="review_img" src="../resources/uploadFiles/review/${r.reviewImage }" alt="">
	                                </div>
	                                <div class="fileArea"> 
	                                	<input type="file" id="imgarea" name="img">
	                                </div>
	                                <div class="contentArea" style="width: 700px;">
	                                    <textarea class="textarea" rows="15" cols="110" name="content" required>${r.reviewText}</textarea>
	                                </div>
	                                <div class="btnarea">
	                                    <button class="modifybtn" type="submit" onclick="updateReveiwView()">등록</button>
	                                    <button class="deletebtn" onclick="deleteReveiwView()">삭제</button>
	                                </div>
	                            </div>
	                        </div>
	                        <input type="hidden" name="rno" value="${ r.reviewNo}">
	                    <input type="hidden" name="img" value="${ r.reviewImage }">
                      	</form>
                      	</div></div>
                      <!-- 모달 끝 지점 -->
                      <br>
                      </c:forEach>
                    
                </div>
                
            </div>
	          <!--  <div class="paging">
					<jsp:include page="/WEB-INF/views/common/paging/paging.jsp"/>	
			    </div>
			  --> 
        </div>
    </div>
    <div class="paging">
		<jsp:include page="/WEB-INF/views/common/paging/paging.jsp"/>	
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <c:forEach var="re" items="${product.reviewList }">
          <form name="reviewDelForm" method="post">
          	<input type="hidden" name="rno" value="${ re.reviewNo}">
			<input type="hidden" name="img" value="${ re.reviewImage }">
          </form>
    </c:forEach>
    <!-- 모달 밖으로 뺀 test
    <c:forEach var="ree" items="${product.reviewList }">
    <div>
    	<form method="post" action="" name="reviewForm" enctype="multipart/form-data">
    					 
	                        <div class="modal hidden">
	                            <div class="bg"></div>
	                            <div class="modalBox">
	                                <div class="closeArea"><img src="../resources/images/review/close.png" alt="" width="20px" height="20px" class="closeBtn"></div>
	                                <div class="reviewTtile">
	                                	
	                                    <h2 style="font-size:20px;">내가 쓴 리뷰</h2>
	                                </div>
	                                <div class="reviewDate">
	                                    <p class="reviewdate">작성일 </p>
	                                </div>
	                                
	                                <div class="reviewImg" id="attachimg">
	                                    <img id="review_img" src="../resources/uploadFiles/review/${ree.reviewImage }" alt="">
	                                </div>
	                                <div class="fileArea"> 
	                                	<input type="file" id="imgarea" name="img">
	                                </div>
	                                <div class="contentArea" style="width: 700px;">
	                                	
	                                    <textarea class="textarea" rows="15" cols="110" name="content" required>${ree.reviewText}</textarea>
	                                </div>
	                                <div class="btnarea">
	                                    <button class="modifybtn" type="submit" onclick="updateReveiwView()">등록</button>
	                                    <button class="deletebtn" onclick="deleteReveiwView(${ree.reviewNo})">삭제</button>
	                                </div>
	                            </div>
	                        </div>
	                        	<input type="hidden" name="rno" value="${ ree.reviewNo}">
	                        	<input type="hidden" name="img" value="${ ree.reviewImage }">
                      	</form>
    </div>
    </c:forEach>
    -->
    <!-- 모달 script-->
    <script>
	    //const open = ()=> {
	    //    document.querySelector(".modal").classList.remove("hidden");
	    //}
	/*
	    const close =()=> {
	        document.querySelector(".modal").classList.add("hidden");
	    }
	    
	    document.querySelector(".openBtn").addEventListener("click", function(){
	    	 document.querySelector(".modal").classList.remove("hidden");
	    });
	    
        //document.querySelector(".openBtn").addEventListener("click", open);
        document.querySelector(".closeBtn").addEventListener("click", close);
        document.querySelector(".bg").addEventListener("click", close);
       */
        function openModal(elem){
        	console.log(elem);
        	console.log(elem.className);
        	var test = elem.parentNode; // 부모 찾기
        	console.log(test); //찾음
        	var one = test.lastChild; 
        	console.log(one); // 모달영역 div 찾음!
        	// 모달영역인 div를 찾아서 선택하게끔 만들기!
        	var two = one.firstChild; //form태그
        	console.log(two);
        	var three = two.firstChild;
        	console.log(three); // class=modal div 찾음
        	var t1 = three.firstChild;
        	console.log(t1); // class="bg" 찾음
			var t2 = t1.nextSibling;
        	console.log(t2); 
        	var t3 = t2.firstChild.firstChild;
        	console.log(t3); // class=closeBtn
        	console.log(t3.className);
        	var closeBtn = t3.classList;
        	
        	three.classList.remove("hidden");
        	
        	const close =()=> {
        	three.classList.add("hidden");
	    	}
        	
        	t1.addEventListener("click", close);
        	t3.addEventListener("click", close);
        }
        
    </script>
    
    <script>
        document.getElementById('imgarea').addEventListener('change', function(){
            if(this.files && this.files[0]){
                let reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function(){
                    console.log(reader.result);
                    document.getElementById('attachimg').innerHTML = "<img src='" + reader.result + "' width='300px' height='300px'>";
                }
            }
        });
    </script>
    <script>
		// 1. status 컬럼을 'N'으로 변경하는 삭제 기능 구현하기
		function deleteReveiwView(){ 
				document.forms.reviewForm.action = "${contextPath}/review/delete";
				document.forms.reviewForm.submit();			
		}
		function updateReveiwView() {
			document.forms.reviewForm.action = "${contextPath}/review/update";
			document.forms.reviewForm.submit();
		}
	</script>
	
	<script>
	// 상품명 내림차순 정렬
	$(function(){
		 $(".nameDesc").click(function(){
			 $.ajax({ //매개변수로 객체 전달
					type: 'post',
					// url : 데이터를 전송할 url(필수)
					contentType:"application/json; charset=UTF-8",
					url : "${ contextPath }/mypage/reviewNameDesc",
					// data : 요청 시 전달할 파라미터 설정
					data: "",
					//{input1 : $(".title").val(), input2 : $(".review_content").val(), input3 : $(".insert_date").val()},
					success: function(data) {
						var str = "<ul class='review_header'><li class='title'>상품명</li>"
                    		+ "<li class='review_content'>리뷰내용 </li>"
                   			+ "<li class='insert_date'>작성일</li></ul>";
							str +=   "<c:forEach var='r' items='${product.reviewList }'>"
									+ "<ul><li class='title'>" + "${product.productName}" + "</li>"
									+ "<li class='review_content'>" + "${r.reviewText}" +"</li>"
									+ "<li class='insert_date'>" + "${r.reviewRegister}" +"</li></ul>"
									+ "</c:forEach>";
									// 모달창 추가해야됨
						$('.review_list').html(str);
					},
					// error : ajax 통신 실패 시 처리할 함수를 지정하는 속성
					error : function(){
						console.log('ajax 통신 실패!');
					}
				});
			});
		})
	</script>
	
    <script>
    // 최신순 정렬
    $(function(){
		 $(".recentList").click(function(){
				location.href="${contextPath}/mypage/review";
			});
		});
    </script>
    <script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제거 처리
		const review_list = document.querySelector(".review_list");
		
		review_list.addEventListener('mouseover', function(){
			console.log('mouseover');
			console.log(event.target);
			
			if(event.target.classList.contains('review_ul')) //ul이라면
				event.target.classList.add('onmouseover');	//클래스 추가
			else if(event.target.parentNode.classList.contains('review_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		review_list.addEventListener('mouseout', function(){
			console.log('mouseout');
			console.log(event.target);
			
			if(event.target.classList.contains('review_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('review_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
	
	</script>
</body>

</html>