<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/payment/payment.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<div id="container">
		<div class="contents">
			<div class="inner">
				<div class="payment_title">
					주문/결제
				</div>	
			 	<div class="left">
			 		<div class="product_info">
			 			<div class="payment_subject">
							상품정보
						</div>
			 			<div class="date">
			 				배송 : <span>${ orderBasket.delivery }</span>&nbsp;도착(배송일 지정)
			 			</div>
			 			<div class="product">
			 				<div class="img_area">
			 					<img src="<%= request.getContextPath() %>/resources/uploadFiles/product/${ orderBasket.productImg }" class="pro_thumb">
			 				</div>
			 				<div class="pro_name">
			 					<p class="goods">${ orderBasket.productName }</p>
			 					<p>${ orderBasket.productQty }개(${ orderBasket.total }원)</p>
			 				</div>
			 				<div class="pro_info">
			 					<table class="info">
			 						<tr>
			 							<td>상품금액</td>
			 							<td class="info_price">${ orderBasket.productQty }개(${ orderBasket.total }원)</td>
			 						</tr>
			 						
			 						<c:set var = "total" value = "0" />
			 						<c:forEach var="o" items="${ orderBasket.optionBasketList }">
			 						<tr>
			 							<c:set var="productPrice" value="${ orderBasket.total }"/>	
			 							<c:set var="optionPrice" value="${ o.optCalcPrice }"/>
			 							<c:set var="result" value="${ productPrice }"/>
			 							
			 							<td>${ o.optionName }</td>
			 							<td class="info_price">${ o.optionQty }개(${ o.optCalcPrice }원)</td>
			 						</tr>
			 						<c:set var="total" value="${ total + o.optCalcPrice }"/>
			 						</c:forEach>
			 						
			 						<tfoot>
			 							<tr>
			 								<td>총 금액</td>
			 								<td>${ result + total }원</td>
			 							</tr>
			 						</tfoot>
			 					</table>
			 				</div>
			 			</div>
			 		</div>
			 		
			 		<div class="delivery_info">
			 			<div class="payment_subject">
							배송정보
						</div>
			 			<table cellspacing="10">
			 				<tr>
			 					<td>우편번호<b>*</b></td>
			 					<td><input type="text" id="address1" name="address" class="postcodify_postcode5" value="${ loginUser.address1 }" readonly required></td>
			 					<td><button id="postcodify_search_button" type="button">검색</button></td>			 							
			 				</tr>
			 				<tr>
			 					<td>도로명주소<b>*</b></td>
			 					<td colspan="3"><input type="text" id="address2" name="address" class="postcodify_address" value="${ loginUser.address2 }" readonly required></td>
			 				</tr>
			 				<tr>
			 					<td>상세주소<b>*</b></td>
			 					<td colspan="3"><input type="text" id="address3" name="address" class="postcodify_details" value="${ loginUser.address3 }" required></td>
			 				</tr>
			 				<tr>
			 					<td>받는 분<b>*</b></td>
			 					<td colspan="2"><input type="text" id="name" name="member" value="${ loginUser.userName }" required></td>
			 				</tr>
			 				<tr>
			 					<td>연락처<b>*</b></td>
			 					<td colspan="2"><input type="text" id="phone" name="member" value="${ loginUser.phone }" required></td>
			 				</tr>
			 				<tr>
			 					<td colspan="3"><input type="text" id="deleveryMsg" name="msg" class="delivery_msg" 
			 						placeholder="배송 메시지를 입력해주세요"></td>
			 				</tr>
			 			</table>
			 		</div>
			 		
			 		<div class="discount">
			 			<div class="payment_subject">
							할인
						</div>
			 			<div class="discount_div">
				 			적립금 <input type="text" id="use" value="">&nbsp;&nbsp;/&nbsp;&nbsp;<span>${ loginUser.coin }</span>
				 			<form class="full_use">
				 				<input type="checkbox" id="coin">
								<label for="coin">전액사용</label>
				 			</form>
			 			</div>
			 		</div>
			 	</div>
				<div class="right">
					
						<div class="payment_info">
							<div class="price">
								<div class="payment_subject">
									결제금액
								</div>
								<c:set var="fee" value="3000"/>
								<span id="final_price">${ result + total + fee }</span>원<br>
								주문금액 <span id="productPrice">${ result + total }</span>원 - 할인금액 <span id="useCoin">0</span>원 + 배송비 <span id="deliveryFee">3000</span>원 
							</div>
							<div class="pay_method">
								신용카드 결제
							</div>
								<div class="btn_area">
									<button type="submit" id="btn" onclick="requestPay()">결제하기</button>
								</div>
						</div>
					
				</div>
			</div> <!-- inner div -->		
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<!-- 우편번호 검색  -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
   
  	<!-- 아임포트 -->
  	<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script> -->
  	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

	<script>
		// 우편번호 검색
		$(function(){ 
			$("#postcodify_search_button").postcodifyPopUp(); 
		}); 
		
		// 적립금 전액사용 버튼
		let chk = $("input[id=coin]");
		
		chk.on("click", function(){
		  if($(this).is(":checked")){
		    $("#use").val(${ loginUser.coin });
		    $("#useCoin").text(${ loginUser.coin });
		   
		  } else {
			 $("#use").val(0);
		  	 $("#useCoin").text(0);
  	 
		  } 	 
			let productPrice = parseInt($("#productPrice").text());
			let useCoin = parseInt($("#useCoin").text());
			let deliveryFee = parseInt(3000);
			
			$("#final_price").text(productPrice - useCoin + deliveryFee);
		});
		
		// 적립금 작성 할 경우
		let use = document.getElementById("use");
		 use.addEventListener('change', function(){
			if(use.value > ${ loginUser.coin }){
				alert("보유 적립금보다 클 수 없습니다.");
				use.focus();
				use.value = "";
				document.getElementById("useCoin").innerText = "0";
			} else {
				document.getElementById("useCoin").innerText = use.value;
				console.log(use.value);
			}
		});
		
		$("#use").change(function(){
			let productPrice = parseInt($("#productPrice").text());
			let useCoin = parseInt($("#useCoin").text());
			let deliveryFee = parseInt(3000);
			
			$("#final_price").text(productPrice - useCoin + deliveryFee);
		});
		
		
		// 아임포트 API 연동 -----------------------------------------------------------------------
	    var IMP = window.IMP;
		IMP.init("imp24190117");
		var email = "${ loginUser.email }";
		var price = parseInt($("#final_price").text());
	    function requestPay() {
	        
	        IMP.request_pay({ // param
	            pg: "inicis",		// PG사 선택 html5_inicis
	            pay_method: "card",		// 지불 수단
	            merchant_uid: 'merchant_' + new Date().getTime(),	// 고유한 id
	            amount: price,			// 가격
	            buyer_email: email,
	            buyer_name: $("#name").val(),
	            buyer_tel: $("#phone").val(),
	            
	        }, function (rsp) { 
	        	console.log(rsp);
	            if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
	            	var msg = "오늘의 밀 결제가 완료되었습니다.";
	            	msg += "결제 금액 : " + rsp.paid_amount + "입니다. 감사합니다.";

	            	location.href='<%= request.getContextPath() %>/user/mypage';
	            	
	            } else {
	            	var msg = "오늘의 밀 결제에 실패하였습니다.";
	            	msg += "에러 내용 : " + rsp.error_msg;
	            }
	            alert(msg);
	            
	            });
	      }
	</script>
</body>
</html>