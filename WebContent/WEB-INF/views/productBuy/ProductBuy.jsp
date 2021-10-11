<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>옵션날짜 선택 구매</title>

<!-- 외부 스타일 시트 -->
<link href="<%= request.getContextPath() %>/resources/css/productBuy/productBuy.css" rel="stylesheet">
<!-- 구글 웹 폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />


	<div class="container">
		<div id="pageTitle">주문/결제</div>

		<div class="productImage">
			<img src="<%= request.getContextPath() %>/resources/images/productBuy/fishcake.png">
		</div>

		<div class="productWrapper1">
			<p class="productName">오뎅탕 세트</p>
			<br>
			<p id="productPrice">25,000원</p>
			<br>

			<p>
				<span class="productWeight">중량/용량</span> <span
					class="productWeight_1">1500g</span>
			</p>
			<br>

			<p>
				<span class="productStorage">보관 방법</span> <span
					class="productStorage_1">
					<img src="<%= request.getContextPath() %>/resources/images/productBuy/iceStorage.png"
					width="16px" height="16px">냉동보관</span>
			</p>

			<img class="grayLine"
				src="<%= request.getContextPath() %>/resources/images/productBuy/line56.png">
			
			<!-- 재료 변경 modal 출력 버튼 -->
			<button id="optChangeBtn">재료 변경</button>
			<div class="plusImage">
				<img src="<%= request.getContextPath() %>/resources/images/productBuy/plusButton.png">
			</div>

			<div class="optSelected">
				<span>우엉마끼*2<br></span> <span>시로볼*1<br></span> <span>생선두부튀김*4</span>
			</div>

			<div class="optSelPrice">
				<span>2,000원<br></span> <span>1,000원<br></span> <span>4,000원</span>
			</div>

			<img class="grayLine" src="<%= request.getContextPath() %>/resources/images/productBuy/line56.png">

			<button id="dateSelectBtn">날짜 선택</button>

			<p id="expectedDate">2021 -08 -30, 07시 이전 도착 예정</p>
			<p id="expressInfo">17:00 이후 주문은 다음날로 넘어갑니다</p>

			<img class="grayLine_1" src="<%= request.getContextPath() %>/resources/images/productBuy/line56.png">

			<div id="buyQuantity">구매수량</div>
			<div class="buyQuanBtn">
				<button class="minusBtn">-</button>
				<input class="inputNum" type="number" min="1" max="99" value="1">
				<button class="plusBtn">+</button>
			</div>

			<div id="priceInfo">
				<span id="totalPrice">총 상품금액 : </span> <span id="visiblePrice">3,2000원</span>
			</div>

			<img class="grayLine_2" src="<%= request.getContextPath() %>/resources/images/productBuy/line56.png">

			<div id="buttonGroup">
				<button id="basketBtn">장바구니 담기</button>
				<button id="purchaseBtn">바로 구매하기</button>
			</div>
		</div>

		<div class="modalBg"></div>
	</div>


	<!-- 재료 변경 modal -->
	<div class="optionModal">

		<div class="modalWrapper1">
			<button id="optModalCloseBtn">X</button>
			<div>
				<div class="opt1">
					<span class="opt1_1">우엉마끼 560g</span> <span class="buyQuanBtn1">
						<button class="minusBtn">-</button> <input class="inputNum"
						type="number" min="1" max="99">
						<button class="plusBtn">+</button>
					</span>
				</div>

				<div class="opt2">
					<span class="opt2_1">시로볼 600g</span> <span class="buyQuanBtn1">
						<button class="minusBtn">-</button> <input class="inputNum"
						type="number" min="1" max="99">
						<button class="plusBtn">+</button>
					</span>
				</div>

				<div class="opt3">
					<span class="opt3_1">아까볼 600g</span> <span class="buyQuanBtn1">
						<button class="minusBtn">-</button> <input class="inputNum"
						type="number" min="1" max="99">
						<button class="plusBtn">+</button>
					</span>
				</div>

				<div class="opt4">
					<span class="opt4_1">생선두부튀김 560g</span> <span class="buyQuanBtn1">
						<button class="minusBtn">-</button> <input class="inputNum"
						type="number" min="1" max="99">
						<button class="plusBtn">+</button>
					</span>
				</div>

				<div id="dateBtnGroup">
					<button id="cancelBtn1">취소</button>
					<button id="confirmBtn">확인</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 날짜 선택 modal -->
	<div class="dateModal">

		<div class="modalWrapper2">
			<button id="dateModalCloseBtn">X</button>
			<div> 
				<div class="topText">
					<span class="selectableDate">선택 가능 배송 도착 날짜 : </span> <span
						class="block"></span>
				</div>
				<!-- 달력 부분 -->
				<table id="calendar">
					<thead>
						<tr>
							<td><input type="button" value="<" /></td>
                      
							<td colspan="3">year-month<td>
                      <td> <input type = "button" value = ">"/> </td>
                      </tr>
					</thead>
					<tbody>
						<tr>
							<td>일</td>
							<td>월</td>
							<td>화</td>
							<td>수</td>
							<td>목</td>
							<td>금</td>
							<td>토</td>
						</tr>
					</tbody>
				</table>

				<div id="dateBtnGroup">
					<button id="cancelBtn2">취소</button>
					<button id="confirmBtn">확인</button>
				</div>
			</div>
		</div>
	</div>


	<script>
        window.onload = function() {
	 
        function onClick1() {
            document.querySelector('.modalWrapper1').style.display ='block';
            document.querySelector('.modalBg').style.display ='block';
            console.log(document.querySelector('.modalWrapper1'));
            console.log(document.querySelector('.modalBg'));
        } 

        function offClick1() {
            document.querySelector('.modalWrapper1').style.display ='none';
            document.querySelector('.modalBg').style.display ='none';
        }
     
        document.getElementById('optChangeBtn').addEventListener('click', onClick1);
        document.getElementById('optModalCloseBtn').addEventListener('click', offClick1);
        document.getElementById('cancelBtn1').addEventListener('click', offClick1);
        
        
        function onClick2() {
            document.querySelector('.modalWrapper2').style.display ='block';
            document.querySelector('.modalBg').style.display ='block';
        }   
        function offClick2() {
            document.querySelector('.modalWrapper2').style.display ='none';
            document.querySelector('.modalBg').style.display ='none';
        }
    
        document.getElementById('dateSelectBtn').addEventListener('click', onClick2);
        document.getElementById('dateModalCloseBtn').addEventListener('click', offClick2);
        document.getElementById('cancelBtn2').addEventListener('click', offClick2);

        let scrollHeight = Math.max(
            document.body.scrollHeight, document.documentElement.scrollHeight,
            document.body.offsetHeight, document.documentElement.offsetHeight,
            document.body.clientHeight, document.documentElement.clientHeight
            );
        document.querySelector('.modalBg').style.height = scrollHeight + 'px';
        };

        

        $(".plusBtn").click(function(){
        var num = $(".inputNum").val();
        var plusNum = Number(num) + 1;

        if(plusNum >= 99) {
            $(".inputNum").val(num);
        } else {
            $(".inputNum").val(plusNum);          
        }
        }); 

        $(".minusBtn").click(function(){
        var num = $(".inputNum").val();
        var minusNum = Number(num) - 1;
        
        if(minusNum <= 0) {
            $(".inputNum").val(num);
        } else {
            $(".inputNum").val(minusNum);          
        }
        });


        today = new Date();
	    
        year = today.getFullYear();
        
        month = today.getMonth();
        YM = year + "년" + (month+1) + "월"; 	
        document.getElementById("YM").innerHTML = YM;	
        first_date = new Date(year,month,1).getDate();
        last_date = new Date(year,month+1,0).getDate();
        first_day = new Date(year,month,1).getDay();
        
    </script>


	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
</body>
</html>