<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Today_Meal</title>
<!-- 외부 스타일 시트 -->
<link href="<%= request.getContextPath() %>/resources/css/productSelect/productSelect.css?ver=1.1113" rel="stylesheet">
<!-- 구글 웹 폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container">
    <div id="pageTitle">주문/결제</div>
<div class="productImage">
    <img src="<%= request.getContextPath() %>/resources/uploadFiles/product/${ orderBasket.productImg }">
</div>

<div class="productWrapper1">
    <p class="productName">${ orderBasket.productName }</p><br>
<p id="productPrice"><fmt:formatNumber value="${ orderBasket.productPrice }" groupingUsed="true"/>원</p><br>
<br>

<p>
    <span class="productStorage">보관 방법</span>
    <span class="productStorage_1"><img src="<%= request.getContextPath() %>/resources/images/productSelect/iceStorage.png" width="16px" height="16px">   냉동보관</span>                                          
</p>

<img class="grayLine" src="<%= request.getContextPath() %>/resources/images/productSelect/Line56.png">

<!-- 재료 변경 modal 출력 버튼 -->

<div class="optDP">
	<button id="optChangeBtn" >재료 변경</button>
	<div class="plusImage">
    	<img src="<%= request.getContextPath() %>/resources/images/productSelect/plusButton.png">
	</div>
	<div id="optSelect" style="width: 370px;">
	</div>
</div>

<div class="calendarBox">
<button id="dateSelectBtn">날짜 선택</button>
<input type="text" id="deliveryDate" placeholder="배송일을 선택하세요">
</div>

<div id="buyQuantity">구매수량</div>
<div class="buyQuanBtn">
    <button class="minusBtn">-</button>
    <input class="inputNum" type="number" min="1" max="99" value="1">
    <button class="plusBtn">+</button>
</div>

<div id="priceInfo">
    <span id="totalPrice">총 상품금액 : </span>
    <span id="visiblePrice">${ orderBasket.productPrice }</span>원
</div>


<div id="buttonGroup">
	<form method="post" action="<%= request.getContextPath() %>/product/select" onsubmit="return orderNextSeq();" >
	<input type="hidden" name="productName" value="${ orderBasket.productName }">
	<input type="hidden" name="productImg" value="${ orderBasket.productImg }">
	<input type="hidden" name="productPrice" value="${ orderBasket.productPrice }">
	<input type="hidden" name="total">
	<input type="hidden" name="productQty">
	<input type="hidden" name="delivery">
	<div id="dynamicBox">
	</div>
	<button id="purchaseBtn">결제하기</button>
	</form>
</div>
</div>

<div class="modalBg"></div>
</div>


<!-- 재료 변경 modal -->
<div class="optionModal">
    <div class="modalWrapper1">
        <button id="optModalCloseBtn">X</button>
        <div class="optionBox">
        <c:forEach var="option" items="${ orderBasket.optionBasketList }" varStatus="status">

        	<div class="opt1">
        		<input type="hidden" id="optNId${status.index }" value="${ option.optionName }">
        		<input type="hidden" id="optPId${status.index }" value="${ option.optionPrice }">
                <span class="opt1_1">${ option.optionName }</span>
                <span>
                	{ <fmt:formatNumber value="${ option.optionPrice }" groupingUsed="true"/>원 }
                	수량 : <input class="opInputNum" type="number" id="optId${status.index }" name="option${status.index }" min="0" max="99">
                </span>
            </div>
        </c:forEach>
            <div id="dateBtnGroup">
                <button id="cancelBtn1">취소</button>
                <button id="confirmBtn">확인</button>
            </div>
        </div>
    </div>
</div>


 <!-- 날짜 선택 modal -->
<!-- <div class="dateModal">

 <div class="modalWrapper2">
     <button id="dateModalCloseBtn">X</button>
     <div>
         <div class="topText">
             <span class="selectableDate">선택 가능 배송 도착 날짜 : </span>
             <span class="block"></span>
         </div>
		<div class=calendarBox>
        <input type="text" id="deliveryDate">
        </div>
        <div id="dateBtnGroup">
            <button id="cancelBtn2">취소</button>
            <button id="confirmBtn">확인</button>
        </div>
    </div>
</div>
</div> -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />

<script>

	let limit = ${ orderBasket.optionBasketList.size() };
	let optNameArr = [];
	let optPriceArr = [];
	let optQtyArr = [];
	let total = 0;
	let postHtml ='';
	
	$(function(){
		$("#confirmBtn").click(function(){
			let zeroCheck = 0;
			
			for(let i=0; i<limit;i++){
				
				optNameArr[i] = document.getElementById("optNId"+i).value;
				optPriceArr[i] = document.getElementById("optPId"+i).value;
				optQtyArr[i] = document.getElementById("optId"+i).value;
				zeroCheck += Number(optQtyArr[i]);
			}
			if(zeroCheck == 0){
				let pp = ${ orderBasket.productPrice };
				postHtml = '';
				$("#optSelect").html("");
				$("#dynamicBox").html("");
				$(".inputNum").val(1);
				$("#visiblePrice").html(pp);
				total = 0;
				return;
			}	
 			$.ajax({
				url : "${pageContext.servletContext.contextPath}/product/select",
				type : "post",
				dataType : "json",
				traditional : true,
				data : {ajaxType : "ajaxType",
						optNameArr : optNameArr,
						optPriceArr : optPriceArr,
						optQtyArr : optQtyArr
					   },
				success : function(optListJson){
							var html ='';
							
							var price = 0;
							var limCnt = 0;
							$(".inputNum").val(1);
							$.each(optListJson, function(index, value){
								html += '<div class="optL">';
								html += '<div>' + value.optionName + ' * ' + value.optionQty + '개</div>';
								html += '<div>' + value.optCalcPrice + '원</div>';
								html += '</div>';
								price += value.optCalcPrice;
								
								//<input type="hidden" name="productName" value="${ orderBasket.productName }">
								postHtml +='<input type="hidden" name="optionName' + index + '"value="' + value.optionName +'">';
								postHtml +='<input type="hidden" name="optionQty' + index + '"value="' + value.optionQty +'">';
								postHtml +='<input type="hidden" name="optionPrice' + index + '"value="' + value.optionPrice +'">';
								postHtml +='<input type="hidden" name="optCalcPrice' + index + '"value="' + value.optCalcPrice +'">';
								limCnt = index + 1;
							});
							postHtml +='<input type="hidden" name="limCnt" value="' + limCnt +'">';
							price += ${ orderBasket.productPrice };
							price *= $(".inputNum").val();
							
							
							$("#dynamicBox").html(postHtml);
							$("#optSelect").html(html);
							$("#visiblePrice").html(price);
							total = $("#visiblePrice").html();
							
						   },
				error : function(e){
							console.log(e);
						}
 			});
		});
	});
	
	
	let now = new Date();
	let minDateValue = '+1D';
	if(now.getHours() >= '17'){
		minDateValue = '+2D';
	}
	$(function(){
		$('#deliveryDate').datepicker({
			showOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다. 
			buttonImage: "<%= request.getContextPath() %>/resources/images/productSelect/calendarBtn.png", // 버튼 이미지 
			buttonImageOnly: false, // 버튼에 있는 이미지만 표시한다. 
			changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다. 
			changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다. 
			minDate: minDateValue, // 최소날짜
			maxDate: '+14D', // 최소날짜
			nextText: '다음 달', // next 아이콘의 툴팁. 
			prevText: '이전 달', // prev 아이콘의 툴팁. 
			numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다. 
			stepMonths: 1, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
			yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가. 
			showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
			currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널 
			closeText: '닫기', // 닫기 버튼 패널 
			dateFormat: "yy-mm-dd", // 텍스트 필드에 입력되는 날짜 형식. 
			showAnim: "slide", //애니메이션을 적용한다. 
			showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식. 
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월의 한글 형식. 
			yearRange: "2021:2022" //연도 범위  "2010:2013"
		});	
	});
	

    window.onload = function() {

    // 옵션 변경 modal
    function onClick1() {
        document.querySelector('.modalWrapper1').style.display ='block';
        document.querySelector('.modalBg').style.display ='block';
    } 

    function offClick1() {
        document.querySelector('.modalWrapper1').style.display ='none';
        document.querySelector('.modalBg').style.display ='none';
    }
 
    document.getElementById('optChangeBtn').addEventListener('click', onClick1);
    document.getElementById('optModalCloseBtn').addEventListener('click', offClick1);
    document.getElementById('confirmBtn').addEventListener('click', offClick1);
    document.getElementById('cancelBtn1').addEventListener('click', offClick1);
    // 날짜 선택 modal
    function onClick2() {
        document.querySelector('.modalWrapper2').style.display ='block';
        document.querySelector('.modalBg').style.display ='block';
    }   
    function offClick2() {
        document.querySelector('.modalWrapper2').style.display ='none';
        document.querySelector('.modalBg').style.display ='none';
    }

    /* document.getElementById('dateSelectBtn').addEventListener('click', onClick2); */
    /* document.getElementById('dateModalCloseBtn').addEventListener('click', offClick2); */
    /* document.getElementById('cancelBtn2').addEventListener('click', offClick2); */

    let scrollHeight = Math.max(
        document.body.scrollHeight, document.documentElement.scrollHeight,
        document.body.offsetHeight, document.documentElement.offsetHeight,
        document.body.clientHeight, document.documentElement.clientHeight
        );
    document.querySelector('.modalBg').style.height = scrollHeight + 'px';
    };

    // 수량 변경

    $(".plusBtn").click(function(){
    	
    var num = $(".inputNum").val();
	
    var plusNum = Number(num) + 1;

    if(plusNum >= 99) {
        $(".inputNum").val(num);
    } else {
        $(".inputNum").val(plusNum);
        
        if(Number($("#optSelect").text()) == 0){
        	$("#visiblePrice").html(Number($("#visiblePrice").html()) + ${ orderBasket.productPrice });    	
        } else if(total != 0){
        	$("#visiblePrice").html(Number($("#visiblePrice").html()) + Number(total)); 
        }
        
    }
    
    });

    $(".minusBtn").click(function(){
    var num = $(".inputNum").val();
    var minusNum = Number(num) - 1;
    
    if(minusNum <= 0) {
        $(".inputNum").val(num);
    } else {
        $(".inputNum").val(minusNum);
        if(Number($("#optSelect").text()) == 0){
        	$("#visiblePrice").html(Number($("#visiblePrice").html()) - ${ orderBasket.productPrice });    	
        } else if(total != 0){
        	$("#visiblePrice").html(Number($("#visiblePrice").html()) - Number(total));
        }
    }
    
    });
    function orderNextSeq(){
    	
     	if($("#deliveryDate").val() ==""){
    		alert("배송일을 선택하세요.");
    		return false;
    	}
     	
     	$('input[name=delivery]').attr('value',$("#deliveryDate").val());
    	$('input[name=productQty]').attr('value',$('.inputNum').val());
    	$('input[name=total]').attr('value',$("#visiblePrice").text());
		
    	return true;
    }
</script>
</body>
</html>