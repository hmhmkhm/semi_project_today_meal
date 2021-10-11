<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/resources/css/common/mypageSubMenubar.css?ver=1.1" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="yjmainframe">
        <div class="yjwrapper">
            <h2 class="yjheader2">MY PAGE</h2>
            <div class="yjcategory">
                <ul>
                    <li class="yjfirst_li">
                    <a class ="yja" href="${ pageContext.servletContext.contextPath}/user/search">
                    <img class="yjicon" src="<%= request.getContextPath() %>/resources/images/mypageSubMenubar/food.png">
                   	상세주문조회
                   	</a>
                   	</li>
                </ul>
                <ul class="yjmenuDrop">
                    <li class="yjfirst_li"><img class="yjicon" src="<%= request.getContextPath() %>/resources/images/mypageSubMenubar/chat.png">나의 활동</li>
                    <div class="vertical_line">
                        <li class="yjhiddenLi"><a class ="yja" href="#">나의 1:1 문의</a></li>
                        <li class="yjhiddenLi"><a class ="yja" href="${ pageContext.servletContext.contextPath}/myLike/list">나의 좋아요 목록</a></li>
                        <li class="yjhiddenLi"><a class ="yja" href="${ pageContext.servletContext.contextPath}/mypage/review">리뷰 관리</a></li>
                    </div>
                </ul>
                <ul>
                    <li class="yjfirst_li"><a class ="yja" href="#"><img class="yjicon" src="<%= request.getContextPath() %>/resources/images/mypageSubMenubar/cart.png">나의 장바구니</a></li>
                </ul>
                <ul class="yjmenuDrop">
                    <li class="yjfirst_li"><img class="yjicon" src="<%= request.getContextPath() %>/resources/images/mypageSubMenubar/me.png">나의 정보</li>
                    <div class="vertical_line">
                        <li class="yjhiddenLi"><a class ="yja" href="${ pageContext.servletContext.contextPath}/memberModify">회원 정보 수정</a></li>
                        <li class="yjhiddenLi"><a class ="yja" href="${ pageContext.servletContext.contextPath}/accountDelete" onclick="return confirm('탈퇴하시겠습니까?')">회원 탈퇴</a></li>
                    </div>
                </ul>
            </div>
        </div>
    </div>

    <script>
    
        let target = document.querySelector(".yjmainframe");

        let menuDrop = document.querySelectorAll('.yjmenuDrop');

        for(let i=0;i<menuDrop.length;i++){
            menuDrop[i].addEventListener('click', function(){

                for(let j=0; j<menuDrop.length;j++){
                    menuDrop[j].classList.remove('yjactive');
                }
                this.classList.add('yjactive');
            });
        }
    </script>
</body>
</html>