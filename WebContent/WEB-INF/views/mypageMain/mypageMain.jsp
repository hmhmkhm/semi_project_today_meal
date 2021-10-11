<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cancelMsg = (String)request.getAttribute("cancelMsg");
	if(cancelMsg != null){
%>
<script>
	alert('주문번호 : ' + <%= cancelMsg %> + ', 취소 신청이 정상적으로 접수되었습니다.');
</script>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/mypageMain/mypageMain.css?ver=1.5164" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div id="container">
<jsp:include page="/WEB-INF/views/common/mypageSubMenubar.jsp" />
 <div class="myPageFrame">
     <div class="myPageWrapper">
         <div class="mpTitle">
             <span><h2 class="mpH2 welcomeName">${ loginUser.userName }님 반갑습니다.</h2></span>
             <span><h2 class="mpH2 welcomeCoin">적립금 : <fmt:formatNumber value="${ loginUser.coin }" groupingUsed="true"/>원</h2></span>
         </div>
         <div class="mpContent">
             <div class="myOrderHistory">
                 <h2 class="mpH2" style="color: black;">주문내역(최근 1개월)</h2>
             </div>
             <div class="mpStatus">
                 <img class="mpImg" src="<%= request.getContextPath() %>/resources/images/mypageMain/mp1.png">
                 <img class="mpPoint" src="<%= request.getContextPath() %>/resources/images/mypageMain/mpPoint.png">
                 <img class="mpImg" src="<%= request.getContextPath() %>/resources/images/mypageMain/mp2.png">
                 <img class="mpPoint" src="<%= request.getContextPath() %>/resources/images/mypageMain/mpPoint.png">
                 <img class="mpImg" src="<%= request.getContextPath() %>/resources/images/mypageMain/mp3.png">
                 
                 <c:set var="cnt_payment" value="0"/>
                 <c:set var="cnt_shipping" value="0"/>
                 <c:set var="cnt_complete" value="0"/>
                 
                 <c:forEach var="receipt" items="${ receiptList }">
                     <c:if test="${receipt.orderStateNo == 1 || receipt.orderStateNo == 2 }"><c:set var="cnt_payment" value="${cnt_payment+1 }"></c:set></c:if>
                     <c:if test="${receipt.orderStateNo == 3 }"><c:set var="cnt_shipping" value="${cnt_shipping+1 }"></c:set></c:if>
                     <c:if test="${receipt.orderStateNo == 4 }"><c:set var="cnt_complete" value="${cnt_complete+1 }"></c:set></c:if>
                 </c:forEach>
                 
                 <c:if test="${cnt_payment != 0 }"><span id="mpCount1"><c:out value="${ cnt_payment }"/></span></c:if>
                 <c:if test="${cnt_shipping != 0 }"><span id="mpCount2"><c:out value="${ cnt_shipping }"/></span></c:if>
                 <c:if test="${cnt_complete != 0 }"><span id="mpCount3"><c:out value="${ cnt_complete }"/></span></c:if>
                 
             </div>
             <div class="mpTable">
                 <table>
                     <thead>
                         <tr>
                             <th>주문일자</th>
                             <th>주문 상품 정보</th>
                             <th>결제 금액</th>
                             <th>주문 상태</th>
                         </tr>
                     </thead>
                     <tbody>
                    	
                        <c:if test="${ receiptList.size() == 0 }">
                            <tr>
                                <td colspan="4">최근 주문/배송 조회 내역이 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:if test="${ receiptList.size() != 0 }">
                            <c:forEach var="receipt" items="${ receiptList }">
                                <tr>
                                    <td rowspan="${ receipt.orderCount }">
                                        <fmt:formatDate value="${ receipt.saleDate }" type="date" pattern="yyyy년 MM월 dd일"/>
                                    </td>
                                    <td>
                                        <div>
                                            <img src="<%= request.getContextPath() %>/resources/uploadFiles/product/${ receipt.productList[0].productImg }">
                                             <p>
                                                 [<c:out value="${ receipt.productList[0].catagoryName }"/>]
                                                 <c:out value="${ receipt.productList[0].productName }"/><br>
                                                 <c:if test="${receipt.productList[0].optionList != null }">
                                                 	
                                                 	추가 상품:&nbsp;
						                             <c:forEach var="option" items="${ receipt.productList[0].optionList }" varStatus="status">
						                             	<c:out value="${ option.optionName }"/>&nbsp;<c:out value="${ option.optionQty }"/>개
						                             	<c:if test="${!status.last}">,&nbsp;</c:if>
						                             </c:forEach>
						                             <br>
                                                 </c:if>
                                                               
			                                                                   수량 : <c:out value="${ receipt.productList[0].productQty }"/>개
                                             </p>
                                             
                                        </div>
                                        <div class="reviewBox">
                                        <form method="get">
                                        	<input type="hidden" name="orderNo" value="${ receipt.orderNo }">
                                        	<input type="hidden" name="pNo" value="${ receipt.productList[0].pNo }">
                                        	<button class="reviewBtn" type="button" onclick="reviewInsert(${ receipt.orderNo },${ receipt.productList[0].pNo });">리뷰작성</button>
                                        </form>
                                        </div>
                                    </td>
                                    
                                    <td rowspan="${ receipt.orderCount }"><fmt:formatNumber value="${ receipt.orderSum }" groupingUsed="true"/>원</td>
                                    <td rowspan="${ receipt.orderCount }">
                                        <c:if test="${ receipt.orderStateNo == 1 || receipt.orderStateNo == 2}">
                                        	
                                         	주문완료
                                            <c:if test="${ receipt.productNo == 1 }">
                                                <button class="tableBtn" type="button" onclick="productStatusModify(${ receipt.orderNo });">취소하기</button>
                                           </c:if>
                                           <c:if test="${ receipt.productNo == 2 }"><span style="color:red;">[취소처리중]</span></c:if>
                                        </c:if>
                                        <c:if test="${ receipt.orderStateNo == 3 }">
                                         	배송중
                                            <c:if test="${ receipt.productNo == 1 }">
                                                <button class="tableBtn" type="button" onclick="productStatusModify(${ receipt.orderNo });">반품하기</button>
                                           </c:if>
                                           <c:if test="${ receipt.productNo == 2 }"><span style="color:red;">[취소처리중]</span></c:if>
                                        </c:if>
                                        <c:if test="${ receipt.orderStateNo == 4 }">
                                         	배송완료
                                            <c:if test="${ receipt.productNo == 1 }">
                                                <button class="tableBtn" type="button" onclick="productStatusModify(${ receipt.orderNo });">반품하기</button>
                                            </c:if>
                                            <c:if test="${ receipt.productNo == 2 }"><span style="color:red;">[취소처리중]</span></c:if>
                                        </c:if>
                                        <c:if test="${ receipt.orderStateNo == 5 }">
                                        	취소/환불
                                        </c:if>
                                    </td>
                                </tr>
                                
                                <c:forEach var="product" items="${ receipt.productList }" varStatus="status">
                                	<c:if test="${status.index >= 1 }">
                                		<tr>
                                     <td>
                                         <div>
                                             <img src="<%= request.getContextPath() %>/resources/uploadFiles/product/${ product.productImg }">
                                             <p>
                                                 [<c:out value="${ product.catagoryName }"/>]
                                                 <c:out value="${ product.productName }"/><br>
                                                 <c:if test="${product.optionList != null }">
                                                 	추가 상품:&nbsp;
	                                                 <c:forEach var="option" items="${ product.optionList }" varStatus="status">
	                                                 	<c:out value="${ option.optionName }"/>&nbsp;<c:out value="${ option.optionQty }"/>개
	                                                 	<c:if test="${!status.last}">,&nbsp;</c:if>
	                                                 </c:forEach>
	                                                 <br>
                                                 </c:if>
                                                               
			                                                                   수량 : <c:out value="${ product.productQty }"/>개
                                             </p>
                                         </div>
                                         <div class="reviewBox">
                                         <form method="get">
                                        	<input type="hidden" name="orderNo" value="${ receipt.orderNo }">
                                        	<input type="hidden" name="productName" value="${ product.pNo }">
                                        	<button class="reviewBtn" type="button" onclick="reviewInsert(${ receipt.orderNo },${ product.pNo });">리뷰작성</button>
                                        </form>
                                         </div>
                                     </td>
                                     
                                 </tr>
                                	</c:if>
                                	
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                     </tbody>
                 </table>
             </div>
             <ul class="board_paging">
		
                 <!-- 맨 처음으로(<<) -->
                 <li><a href="${ pageContext.servletContext.contextPath}/user/mypage?page=1">&lt;&lt;</a></li>
                 
                 <!-- 이전 페이지로(<) -->
                 <li>
                 <c:choose>
                     <c:when test="${ pi.page > 1 }">
                     <a href="${ pageContext.servletContext.contextPath}/user/mypage?page=${ pi.page - 1}">&lt;</a>
                     </c:when>
                     <c:otherwise>
                     <a href="#">&lt;</a>
                     </c:otherwise>
                 </c:choose>
                 </li>
                 
                 <!-- 페이지 목록(최대 3개) -->
                 <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                 <li>
                     <c:choose>
                         <c:when test="${ p eq pi.page }">
                             <a href="#" class="current_page">${ p }</a>
                         </c:when>
                         <c:otherwise>
                             <a href="${ pageContext.servletContext.contextPath}/user/mypage?page=${ p }">${ p }</a>
                         </c:otherwise>
                     </c:choose>
                 </li>
                 </c:forEach>
                 <!--  다음 페이지로(>) -->
                 <li>
                 <c:choose>
                     <c:when test="${ pi.page < pi.maxPage}">
                     <a href="${ pageContext.servletContext.contextPath}/user/mypage?page=${pi.page +1}">&gt;</a>
                     </c:when>
                     <c:otherwise>
                         <a href="#">&gt;</a>
                     </c:otherwise>
                 </c:choose>
                 </li>
                 
                 <!-- 맨 끝으로 (>>) -->
                 <li><a href="${ pageContext.servletContext.contextPath}/user/mypage?page=${ pi.maxPage }">&gt;&gt;</a></li>
             </ul>
         </div>
     </div>
 </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />

<script>

function reviewInsert(ono,pno){
    window.open("${contextPath}/review/insert?ono="+ono+"&pno="+pno, "popup1", "width=1000, height=800");
}

function productStatusModify(orderNo){
	location.href="${contextPath}/user/mypage?orderNo="+orderNo;
}


</script> 
</body>
</html>