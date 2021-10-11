<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>

<style>
.wrapper {
	display: flex;
	text-align: center;
	flex-direction: column;
	align-items: center;
	
}
.swiper-container {
	width:1100px;
	height:600px;
}
.swiper-slide {
	text-align:center;
	display:flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items:center; /* 위아래 기준 중앙정렬 */
	justify-content:center; /* 좌우 기준 중앙정렬 */
}

}
#BEST{
width: 1100px;
height: 600px;
}
#p1{
width: 300px;
height: 72px;


font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 64px;
line-height: 100px;

width: 1100px;
margin: 0 auto;
margin-top: 200px;

color: #5C9200;
}
#p2{
width: 506px;
height: 28px;


font-family: Roboto;
font-style: normal;
font-weight: normal;
font-size: 18px;
line-height: 21px;

width: 1100px;
margin: 0 auto;
margin-top: 45px;

color: #294401;
}
#p3{
width: 300px;
height: 200px;


font-family: Roboto;
font-style: normal;
font-weight: bold;
font-size: 64px;
line-height: 300px;

width: 1100px;
margin: 0 auto;

color: #FFFFFF;
}
.square {
width: 1100px;
height: 780px;
background: #A1AD61;
margin-top: 320px;
}
#nav2 {
	height: 50px;
	width: 1100px;
}

#nav2 ul {
	width: 800px;
	line-height : 50px;
	margin : 0 auto;
	display: flex;
	justify-content: space-around; 
	list-style: none;
}

#nav2 li {
	text-decoration:none;
	color : #294401;
	font-family: Roboto;
	font-weight : bold;
	font-size : 18px;
	display:block;
	width : 150px;
	text-align : center;
}

 img {
max-width: 100%;
}
.top5  {
 width: 170px;
height: 170px;
  position: relative
}

.top5B {
  position: absolute;
  top: 0px;
  left: 0px;
  display: none;
}
 .top5:hover .top5B {
        display: block;
      }
.salesTop5{
	width: 1000px;
	margin : 0 auto;
	display: flex;
	justify-content: space-around; 
	margin-top: 120px;
}
.new3  {
 width: 260px;
height: 260px;
  position: relative
}

.new3B {
  position: absolute;
  top: 0px;
  left: 0px;
  display: none;
}
 .new3:hover .new3B {
        display: block;
      }
.newMenu{
	width: 900px;
	margin : 0 auto;
	display: flex;
	justify-content: space-around; 
	margin-top: 80px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="wrapper">
       <div class="outer">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                    <img id="BEST" src="<%= request.getContextPath() %>/resources/images/mainpage/BEST1.png">
                    </div>
                    <div class="swiper-slide">
                    <img id="BEST" src="<%= request.getContextPath() %>/resources/images/mainpage/BEST2.png">
                    </div>
                    <div class="swiper-slide">
                    <img id="BEST" src="<%= request.getContextPath() %>/resources/images/mainpage/BEST3.png">
                    </div>
               </div>
                               <!-- 네비게이션 버튼 -->
	<div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
	<div class="swiper-button-prev"></div><!-- 이전 버튼 -->

	<!-- 페이징 -->
	<div class="swiper-pagination"></div>
            </div>
      
             <p id="p1">TOP 5</p>
             <p id="p2">매출 순위로 보는 인기 메뉴, 매출 순위 TOP 5!</p>
             <div class="salesTop5">
               <div class ="top5">
                 <img class="top5A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                 <img class="top5B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
               </div>
               <div class ="top5">
                 <img class="top5A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                 <img class="top5B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
               </div>
               <div class ="top5">
                 <img class="top5A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                 <img class="top5B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
               </div>
               <div class ="top5">
                 <img class="top5A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                 <img class="top5B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
               </div>               
               <div class ="top5">
                 <img class="top5A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                 <img class="top5B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
               </div>                                           
             </div>
            <div class="square">
             <p id="p3">신메뉴</p>
                  <nav id="nav2">
                      <ul>
                          <li>한식</li>
                          <li>/</li>
                          <li>양식</li>
                          <li>/</li>
                          <li>중식</li>
                          <li>/</li>
                          <li>일식</li>
                          <li>/</li>
                          <li>분식</li>
                          <li>/</li>
                          <li>양식</li>
                          <li>/</li>
                          <li>샐러드</li>
                      </ul>
                 </nav>
                 <div class="newMenu">
                    <div class ="new3">
                      <img class="new3A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                      <img class="new3B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
                    </div>
                    <div class ="new3">
                      <img class="new3A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                      <img class="new3B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
                    </div>
                    <div class ="new3">
                      <img class="new3A" src="<%= request.getContextPath() %>/resources/images/mainpage/food.png">
                      <img class="new3B" src="<%= request.getContextPath() %>/resources/images/mainpage/food2.png">
                    </div>                                       
                 </div>
            </div>
       </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <script>

new Swiper('.swiper-container', {
	autoplay: {
	    delay: 2000,
	  },
	pagination : { // 페이징 설정
		el : '.swiper-pagination',
		clickable : true, // 페이징을 클릭하면 해당 영역으로 이동
	},
	navigation : { // 네비게이션 설정
		nextEl : '.swiper-button-next', // 다음 버튼 클래스명
		prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
	},
});

</script> 

   
</body>
</html>