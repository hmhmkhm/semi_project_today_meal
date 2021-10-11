<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>상품정보페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
</head>
<style>
    body {
        padding: 50px;
    }
    * {
        font-family: 'Noto Sans KR', sans-serif;
    }
    .product_view {
        position: relative;
        padding: 0 0 0 395px;
        width: 1000px;
        height: 375px;
        box-sizing: border-box;
        background-color: #F5F5F5;
        margin: 0 auto;
    }

    .product_view .img {
        position: absolute;
        left: 0;
        top: 0;
        margin-left: 50px;
        margin-top: 20px;
        margin-bottom: 20px;
    }

    .product_view .img>img {
        width: 368px;
        height: 330px;
        border: 1px solid #e8e8e8;
    }

    .product_view h2 {
        text-align: center;
        margin: 0 0 15px;
        padding: 50px 0 20px;
        font-size: 24px;
        font-weight : bold;
    }

    .product_view table th,
    .product_view table td {
        padding: 14px 0;
        font-size: 15px;
        color: #444;
        text-align: center;
    }
	.product_view table td,
	.product_view table th {
		padding-left : 100px
	}
    table {
        margin-left: 150px;
    }

    .product_view .btns {
        padding: 45px 0 0;
        text-align: center;
    }

    .product_view .btns>a.btn2 {
        background: #294401;
        border-radius : 7px;
    }

    a {
        color: #666;
        text-decoration: none;
    }

    li {
        list-style: none;
    }

    .review_sort_list li {
        display: inline-block;
    }

    .review_sort_list .selected {
        font-weight: bold;
    }

    .sub_btn p {
        display: inline-block;
        justify-content: space-around;
    }

    .sub_btn {
        width: 1000px;
        height: 53px;
        margin: 0px auto;
        border: 1px solid lightgray;
        margin-top: 20px;
        margin-bottom: 20px;
    }

    #moveDtail {
        background-color: #A1AD61;
        text-align: center;
        width: 500px;
        margin: 0 auto;
        padding-top: 15px;
        padding-bottom: 15px;
        float: left;
    }

    #moveReview {
        /* text-align: center;
        width: 500px;
        height: 50px; */
        text-align: center;
        width: 500px;
        margin: 0 auto;
        padding-top: 15px;
        padding-bottom: 15px;
        background-color : white;
    }

    .detail_img {
        width: 800px;
        height: 900px;
        border: 1px solid lightgray;
        margin: 0 auto;
    }

    .review_sort {
        text-align: right;
        padding-right: 300px;
    }

    .review_info {
        width: 300px;
        vertical-align: middle;
    }

    .review_content {
        width: 550px;
        height: 200px;
        text-align: center;
        vertical-align: middle;
        word-break: break-all;
        margin-left: 50px;
    }

    .review_thum {
        width: 100px;
        margin-left: 45px;
        border: 1px solid black;
    }

    .review_list {
        width: 1000px;
        height: 200px;
        /* background-color: lightgray; */
        margin-left: 290px;
        /*border: 1px solid black;*/
        margin-top : 20px
    }

    #myDiv {
        display: none;
        width: 800px;
        background-color: lightgray;
        margin-left: 150px;
    }

    .con {
        vertical-align: middle;
    }

    #hide {
        padding-top: 10px;
        height: 500px;
        width : 1000px;
        margin-bottom : 20px;
    }

    .box {
        display: inline-block;
    }
    /* 0909 test */
    .main{
            position: relative;
            top: 50%;
            width: 1000px;
            height: 200px;
        }
           
        .main_common{
            display:inline-table;
            float: left;
            width: 100px;
            height: 100px;
        }
         
        .pcontent {
            display: table-cell;
            vertical-align: middle;
            text-align: center;
            word-break:break-all;
        }

        .main_1 {
            width: 200px;
            height: 200px;
        }
        .main_2{
            width: 500px;
            height: 200px;
        }
        .main_3 {
            width: 200px;
            height: 200px;
            text-align: center;
            vertical-align: middle;
        }
        .img_review {
            margin-top: 50px;
        }
        .detail_review_img {
            text-align: center;
            vertical-align: middle;
            width: 300px;
            height: 300px;
        }
        .detail_review_img img {
        	margin-left : 370px;
        }
        .detail_info {
            width: 100px;
            height: 100px;
            border: 1px solid black;
        }
        .detail_content {
            width: 500px;
            height: 100px;
            border: 1px solid black;
        }
        .detail {
            position: relative;
            top: 50%;
            width: 1000px;
            height: 170px;
            text-align: center;
            vertical-align: middle;
        }
        .fold_review {
            text-align: right;
            font-weight: bold;
        }
        .fold_review:hover{
            cursor: pointer;
        }
        /* 별점 스타일 */
		.star {
			display: inline-block;
			width: 10px;
			height: 10px;
			
		}
		.star_left {
			background: url(../../images/review/star.PNG) no-repeat 0 0;;
			background-size: 60px;
			margin-right: -3px;
		}
		.star_right {
			background: url(../images/review/star.PNG) no-repeat -30px 0;
			background-size: 60px;
			margin-left: -3px;
		}
		.detail_content {
			width : 100px;
		}
		
        .orderBtn {
        	background: #294401;
        	border-radius : 7px;
        	color : white;
        	width: 136px;
        	height: 42px;
        	font-size : 16px;
        }
</style>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/common/top.jsp" />
    <div class="product_view">
        <h2 name="product_name"> <!-- 상품명 받아오기 --> ${product.productName} </h2>
        <table>
            <colgroup>
                <col style="width:173px;">
                <col>
            </colgroup>
            <tbody>
                <tr>
                    <th>가격</th>
                    <td class="price" name="price"><!--  가격 받아오기  -->${product.productPrice }</td>
                </tr>
                <tr>
                    <th>배송정보</th>
                    <td>배송일 지정</td>
                </tr>

            </tbody>
        </table>
        <div class="img">
            <img src="../resources/uploadFiles/product/${product.productImg }" alt="">

        </div>
        <div class="btns">
        	<form method="post" action="${contextPath}/product/select">
        	<button class="orderBtn" type="button" onclick="pSelect(${product.productNo});">재료변경/주문하기</button>
        	</form>
        	
        </div>
    </div>
    <!-- 내부이동 버튼 영역-->
    <div class="sub_btn">
        <p id="moveDtail"><a href="#pro_detail">상세정보</a></p>
        <p id="moveReview"><a href="#reviewsort">리뷰</a></p>
    </div>
    <!-- 상품 상세 정보 -->
    <div class="detail_img" id="pro_detail">
        <img src="../resources/uploadFiles/product/${product.productImg }" alt="" style="width:800px; height:900px;">
    </div>
    <!-- 리뷰 정렬 영역 -->
    <div class="review_sort" id="reviewsort">
        <ul class="review_sort_list">
            <li class="selected"><span class="recentList">최신순</span> &nbsp; | &nbsp;</li>
            <li class="starPointlist" name="reviewPointDesc">별점높은순</li>
        </ul>
    </div><div class="review_list" ><c:forEach var="r" items="${ product.reviewList }"><ul class="review" onclick="review_detail(this);" ><li class="review_li" ><div class="main">
                    <div class="main_1 main_common">
                        <p class="pcontent"> 별점
                          <c:choose>
							 <c:when test="${r.point eq 0.5 }">
							  <img class="star" src="../resources/images/review/star_half.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 1.5 }">
							  <img class="star" src="../resources/images/review/1.5.png" style="width:40px; height:20px; margin-left:5px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 2 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 2.5 }">
							  <img class="star" src="../resources/images/review/2.5.png" style="width:60px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 3 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 3.5 }">
							  <img class="star" src="../resources/images/review/3.5.png" style="width:100px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 4 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							<c:when test="${r.point eq 4.5 }">
							  <img class="star" src="../resources/images/review/4.5.png" style="width:100px; height:20px;"><br>
							 </c:when>
							 <c:otherwise>
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <br>
							 </c:otherwise>
						</c:choose>
              <span class="rwriter">작성자 ${r.userName }</span>            
                         <br> <span class="register">작성일 ${r.reviewRegister }</span></p>
                    </div>
                    <div class="main_2 main_common"><p class="pcontent"><a href=#none >${r.reviewText }</a></p></div>
                    <div class="main_3 main_common">
                        <img class="img_review" src="../resources/uploadFiles/review/${r.reviewImage }" alt=""
                        style="width: 100px; height: 100px;">
                    </div>
                </div>
            </li>
        </ul><div id="hide" style="display: none; background-color:#F5F5F5;" class="hide">
              <div>
                <div class="detail">
                    <div class="main_common main_1">
                        <div class="pcontent">
                        	<p>별점 
                        	<c:choose>
							 <c:when test="${r.point eq 0.5 }">
							  <img class="star" src="../resources/images/review/star_half.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 1.5 }">
							  <img class="star" src="../resources/images/review/1.5.png" style="width:40px; height:20px; margin-left:5px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 2 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 2.5 }">
							  <img class="star" src="../resources/images/review/2.5.png" style="width:60px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 3 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 3.5 }">
							  <img class="star" src="../resources/images/review/3.5.png" style="width:100px; height:20px; margin-left:20px;"><br>
							 </c:when>
							 <c:when test="${r.point eq 4 }">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;"><br>
							 </c:when>
							<c:when test="${r.point eq 4.5 }">
							  <img class="star" src="../resources/images/review/4.5.png" style="width:100px; height:20px;"><br>
							 </c:when>
							 <c:otherwise>
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <img class="star" src="../resources/images/review/star_on.png" style="width:20px; height:20px;">
							  <br>
							 </c:otherwise>
						</c:choose>
                        	</p>
                        	<p>작성자 ${r.userName }</p>
                        </div>
                    </div>
                    <div class="main_common main_2">
                        <p class="pcontent">${r.reviewText }</p>
                    </div>
                </div>
                <div class="detail_review_img">
                    <img style="width: 300px; height: 300px;" src="../resources/uploadFiles/review/${r.reviewImage }" alt="">
                </div>
                <div><p class="fold_review" id="fold" onclick="folding(this);">▲ 리뷰 접기</p></div></div></div></c:forEach>
                <div class="paging">
		<jsp:include page="/WEB-INF/views/common/paging/paging.jsp"/>	
    </div><jsp:include page="/WEB-INF/views/common/footer.jsp"/></div>
    <jsp:include page="/WEB-INF/views/common/top.jsp" />
	
   
	<script>
    function review_detail(elem) {
		console.log(elem);
		var test1 = elem.nextSibling; 
    	console.log(test1); //hide
		
    	var test2 = elem.firstChild;
    	console.log(test2);
    	
    	var test3 = test2.firstChild;
    	console.log(test3); //.main
		
		// main : 리뷰 프리뷰 / hide : 리뷰를 자세히 볼 수 있는 div영역
        var main = document.querySelector('.main');
        let hide = document.querySelector('.hide');
        
		if (test1.style.display == 'none') {
			test1.style.display = '';
			test3.style.display = 'none';
        } else {
        	test1.style.display = 'none';
        	test3.style.display = '';
        }
        elem.style.display = '';
        
    }
    function folding(elem) {
    	console.log(elem);
    	var test1 = elem.parentNode;
    	//console.log(test1);
    	
    	var test2 = test1.parentNode;
    	//console.log(test2);
    	
    	var hideDiv = test2.parentNode;
    	//console.log(hideDiv); //#hide
    	
    	var test4 = hideDiv.parentNode.firstChild;
    	//console.log(test4);
        
        var test8 = test4.firstChild.firstChild.firstChild;
        //console.log(test8); // 최상위 div > > > mian
    	
        //ul다음의li다음의 main찾기??
        var test9 = hideDiv.previousSibling;
        //console.log(test9);
        
        var mainDiv = test9.firstChild.firstChild;
        //console.log(mainDiv);
        
        //let hide = document.querySelector(".hide");
        //var main = document.querySelector('.main');
        if (mainDiv.style.display == 'none') {
        	mainDiv.style.display = '';
        	hideDiv.style.display = 'none';
        }
    }
    </script>

    <!--  sub_btn 특정 위치에서 따라다니는 메뉴바 스크립트 -->
    <script>
    $(window).scroll(function() {
    	if($(this).scrollTop() > 400) {
    		$(".sub_btn").css('position','fixed').css('margin-left','250px').css('top','0');
    	} else {
    		 $(".sub_btn").css('position', 'relative');
    	}
    });
    </script>
	 <!-- 별점 순 정렬 ajax -->
	 
	 <script>
	 $(function(){
		 $(".starPointlist").click(function(){
				$.ajax({
					type: 'post',
					contentType:"application/json; charset=UTF-8",
					url : "${ contextPath }/productDetail/starPointlist",
					// data : 요청 시 전달할 파라미터 설정
					data: "",
					success: function(result) {
						$('.review_list').html(result);
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
	 function pSelect(pno){
		 location.href='${contextPath}/product/select?pno=' + pno;
	 }
	 </script>
</body>
</html>