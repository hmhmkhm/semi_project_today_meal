<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="<%= request.getContextPath() %>/resources/css/searchOrders/searchOrders.css?ver=1.118" rel="stylesheet">
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
                        <h2 class="mpH2" style="color: black;">상세 주문 조회</h2>
                    </div>
                    <div class="mpSearchArea">
                    <form method="get" action="${ pageContext.servletContext.contextPath}/user/search">
                    
                        <select class="yearDropdown" id="startYY" name="startYY">
                            <option value="2021" <c:if test="${ param.startYY == '2021' }">selected</c:if>>2021</option>
                            <option value="2020" <c:if test="${ param.startYY == '2020' }">selected</c:if>>2020</option>
                            <option value="2019" <c:if test="${ param.startYY == '2019' }">selected</c:if>>2019</option>
                            <option value="2018" <c:if test="${ param.startYY == '2018' }">selected</c:if>>2018</option>
                            <option value="2017" <c:if test="${ param.startYY == '2017' }">selected</c:if>>2017</option>
                            <option value="2016" <c:if test="${ param.startYY == '2016' }">selected</c:if>>2016</option>
                            <option value="2015" <c:if test="${ param.startYY == '2015' }">selected</c:if>>2015</option>
                            <option value="2014" <c:if test="${ param.startYY == '2014' }">selected</c:if>>2014</option>
                            <option value="2013" <c:if test="${ param.startYY == '2013' }">selected</c:if>>2013</option>
                            <option value="2012" <c:if test="${ param.startYY == '2012' }">selected</c:if>>2012</option>
                            <option value="2011" <c:if test="${ param.startYY == '2011' }">selected</c:if>>2011</option>
                        </select>
                        <label for="startYY">년</label>
                        <select class="monthDateDropdown" id="startMM" name="startMM">
                            <option value="01" <c:if test="${ param.startMM == '01' }">selected</c:if>>1</option>
                            <option value="02" <c:if test="${ param.startMM == '02' }">selected</c:if>>2</option>
                            <option value="03" <c:if test="${ param.startMM == '03' }">selected</c:if>>3</option>
                            <option value="04" <c:if test="${ param.startMM == '04' }">selected</c:if>>4</option>
                            <option value="05" <c:if test="${ param.startMM == '05' }">selected</c:if>>5</option>
                            <option value="06" <c:if test="${ param.startMM == '06' }">selected</c:if>>6</option>
                            <option value="07" <c:if test="${ param.startMM == '07' }">selected</c:if>>7</option>
                            <option value="08" <c:if test="${ param.startMM == '08' }">selected</c:if>>8</option>
                            <option value="09" <c:if test="${ param.startMM == '09' }">selected</c:if>>9</option>
                            <option value="10" <c:if test="${ param.startMM == '10' }">selected</c:if>>10</option>
                            <option value="11" <c:if test="${ param.startMM == '11' }">selected</c:if>>11</option>
                            <option value="12" <c:if test="${ param.startMM == '12' }">selected</c:if>>12</option>
                        </select>
                        <label for="startMM">월</label>
                        <select class="monthDateDropdown" id="startDD" name="startDD">
                            <option value="01" <c:if test="${ param.startDD == '01' }">selected</c:if>>1</option>
                            <option value="02" <c:if test="${ param.startDD == '02' }">selected</c:if>>2</option>
                            <option value="03" <c:if test="${ param.startDD == '03' }">selected</c:if>>3</option>
                            <option value="04" <c:if test="${ param.startDD == '04' }">selected</c:if>>4</option>
                            <option value="05" <c:if test="${ param.startDD == '05' }">selected</c:if>>5</option>
                            <option value="06" <c:if test="${ param.startDD == '06' }">selected</c:if>>6</option>
                            <option value="07" <c:if test="${ param.startDD == '07' }">selected</c:if>>7</option>
                            <option value="08" <c:if test="${ param.startDD == '08' }">selected</c:if>>8</option>
                            <option value="09" <c:if test="${ param.startDD == '09' }">selected</c:if>>9</option>
                            <option value="10" <c:if test="${ param.startDD == '10' }">selected</c:if>>10</option>
                            <option value="11" <c:if test="${ param.startDD == '11' }">selected</c:if>>11</option>
                            <option value="12" <c:if test="${ param.startDD == '12' }">selected</c:if>>12</option>
                            <option value="13" <c:if test="${ param.startDD == '13' }">selected</c:if>>13</option>
                            <option value="14" <c:if test="${ param.startDD == '14' }">selected</c:if>>14</option>
                            <option value="15" <c:if test="${ param.startDD == '15' }">selected</c:if>>15</option>
                            <option value="16" <c:if test="${ param.startDD == '16' }">selected</c:if>>16</option>
                            <option value="17" <c:if test="${ param.startDD == '17' }">selected</c:if>>17</option>
                            <option value="18" <c:if test="${ param.startDD == '18' }">selected</c:if>>18</option>
                            <option value="19" <c:if test="${ param.startDD == '19' }">selected</c:if>>19</option>
                            <option value="20" <c:if test="${ param.startDD == '20' }">selected</c:if>>20</option>
                            <option value="21" <c:if test="${ param.startDD == '21' }">selected</c:if>>21</option>
                            <option value="22" <c:if test="${ param.startDD == '22' }">selected</c:if>>22</option>
                            <option value="23" <c:if test="${ param.startDD == '23' }">selected</c:if>>23</option>
                            <option value="24" <c:if test="${ param.startDD == '24' }">selected</c:if>>24</option>
                            <option value="25" <c:if test="${ param.startDD == '25' }">selected</c:if>>25</option>
                            <option value="26" <c:if test="${ param.startDD == '26' }">selected</c:if>>26</option>
                            <option value="27" <c:if test="${ param.startDD == '27' }">selected</c:if>>27</option>
                            <option value="28" <c:if test="${ param.startDD == '28' }">selected</c:if>>28</option>
                            <option value="29" <c:if test="${ param.startDD == '29' }">selected</c:if>>29</option>
                            <option value="30" <c:if test="${ param.startDD == '30' }">selected</c:if>>30</option>
                            <option value="31" <c:if test="${ param.startDD == '31' }">selected</c:if>>31</option>
                        </select>
                        <label for="startDD">일</label>
                        &nbsp;&nbsp;~&nbsp;&nbsp;
                        <select class="yearDropdown" id="endYY" name="endYY">
                            <option value="2021" <c:if test="${ param.endYY == '2021' }">selected</c:if>>2021</option>
                            <option value="2020" <c:if test="${ param.endYY == '2020' }">selected</c:if>>2020</option>
                            <option value="2019" <c:if test="${ param.endYY == '2019' }">selected</c:if>>2019</option>
                            <option value="2018" <c:if test="${ param.endYY == '2018' }">selected</c:if>>2018</option>
                            <option value="2017" <c:if test="${ param.endYY == '2017' }">selected</c:if>>2017</option>
                            <option value="2016" <c:if test="${ param.endYY == '2016' }">selected</c:if>>2016</option>
                            <option value="2015" <c:if test="${ param.endYY == '2015' }">selected</c:if>>2015</option>
                            <option value="2014" <c:if test="${ param.endYY == '2014' }">selected</c:if>>2014</option>
                            <option value="2013" <c:if test="${ param.endYY == '2013' }">selected</c:if>>2013</option>
                            <option value="2012" <c:if test="${ param.endYY == '2012' }">selected</c:if>>2012</option>
                            <option value="2011" <c:if test="${ param.endYY == '2011' }">selected</c:if>>2011</option>
                        </select>
                        <label for="endYY">년</label>
                        <select class="monthDateDropdown" id="endMM" name="endMM">
                            <option value="01" <c:if test="${ param.endMM == '01' }">selected</c:if>>1</option>
                            <option value="02" <c:if test="${ param.endMM == '02' }">selected</c:if>>2</option>
                            <option value="03" <c:if test="${ param.endMM == '03' }">selected</c:if>>3</option>
                            <option value="04" <c:if test="${ param.endMM == '04' }">selected</c:if>>4</option>
                            <option value="05" <c:if test="${ param.endMM == '05' }">selected</c:if>>5</option>
                            <option value="06" <c:if test="${ param.endMM == '06' }">selected</c:if>>6</option>
                            <option value="07" <c:if test="${ param.endMM == '07' }">selected</c:if>>7</option>
                            <option value="08" <c:if test="${ param.endMM == '08' }">selected</c:if>>8</option>
                            <option value="09" <c:if test="${ param.endMM == '09' }">selected</c:if>>9</option>
                            <option value="10" <c:if test="${ param.endMM == '10' }">selected</c:if>>10</option>
                            <option value="11" <c:if test="${ param.endMM == '11' }">selected</c:if>>11</option>
                            <option value="12" <c:if test="${ param.endMM == '12' }">selected</c:if>>12</option>
                        </select>
                        <label for="endMM">월</label>
                        <select class="monthDateDropdown" id="endDD" name="endDD">
                            <option value="01" <c:if test="${ param.endDD == '01' }">selected</c:if>>1</option>
                            <option value="02" <c:if test="${ param.endDD == '02' }">selected</c:if>>2</option>
                            <option value="03" <c:if test="${ param.endDD == '03' }">selected</c:if>>3</option>
                            <option value="04" <c:if test="${ param.endDD == '04' }">selected</c:if>>4</option>
                            <option value="05" <c:if test="${ param.endDD == '05' }">selected</c:if>>5</option>
                            <option value="06" <c:if test="${ param.endDD == '06' }">selected</c:if>>6</option>
                            <option value="07" <c:if test="${ param.endDD == '07' }">selected</c:if>>7</option>
                            <option value="08" <c:if test="${ param.endDD == '08' }">selected</c:if>>8</option>
                            <option value="09" <c:if test="${ param.endDD == '09' }">selected</c:if>>9</option>
                            <option value="10" <c:if test="${ param.endDD == '10' }">selected</c:if>>10</option>
                            <option value="11" <c:if test="${ param.endDD == '11' }">selected</c:if>>11</option>
                            <option value="12" <c:if test="${ param.endDD == '12' }">selected</c:if>>12</option>
                            <option value="13" <c:if test="${ param.endDD == '13' }">selected</c:if>>13</option>
                            <option value="14" <c:if test="${ param.endDD == '14' }">selected</c:if>>14</option>
                            <option value="15" <c:if test="${ param.endDD == '15' }">selected</c:if>>15</option>
                            <option value="16" <c:if test="${ param.endDD == '16' }">selected</c:if>>16</option>
                            <option value="17" <c:if test="${ param.endDD == '17' }">selected</c:if>>17</option>
                            <option value="18" <c:if test="${ param.endDD == '18' }">selected</c:if>>18</option>
                            <option value="19" <c:if test="${ param.endDD == '19' }">selected</c:if>>19</option>
                            <option value="20" <c:if test="${ param.endDD == '20' }">selected</c:if>>20</option>
                            <option value="21" <c:if test="${ param.endDD == '21' }">selected</c:if>>21</option>
                            <option value="22" <c:if test="${ param.endDD == '22' }">selected</c:if>>22</option>
                            <option value="23" <c:if test="${ param.endDD == '23' }">selected</c:if>>23</option>
                            <option value="24" <c:if test="${ param.endDD == '24' }">selected</c:if>>24</option>
                            <option value="25" <c:if test="${ param.endDD == '25' }">selected</c:if>>25</option>
                            <option value="26" <c:if test="${ param.endDD == '26' }">selected</c:if>>26</option>
                            <option value="27" <c:if test="${ param.endDD == '27' }">selected</c:if>>27</option>
                            <option value="28" <c:if test="${ param.endDD == '28' }">selected</c:if>>28</option>
                            <option value="29" <c:if test="${ param.endDD == '29' }">selected</c:if>>29</option>
                            <option value="30" <c:if test="${ param.endDD == '30' }">selected</c:if>>30</option>
                            <option value="31" <c:if test="${ param.endDD == '31' }">selected</c:if>>31</option>
                        </select>
                        <label for="endDD">일</label>
                        <button id="sBtn" type="submit">조회하기</button>
                    </form>
                    </div>
                    <div class="mpTable">
                        <table border="1px">
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
                                    </td>
                                    
                                    <td rowspan="${ receipt.orderCount }"><fmt:formatNumber value="${ receipt.orderSum }" groupingUsed="true"/>원</td>
                                    <td rowspan="${ receipt.orderCount }">
                                        <c:if test="${ receipt.orderStateNo == 1 || receipt.orderStateNo == 2}">
                                            	주문완료
                                        </c:if>
                                        <c:if test="${ receipt.orderStateNo == 3 }">
                                            	배송중
                                        </c:if>
                                        <c:if test="${ receipt.orderStateNo == 4 }">
                                            	배송완료
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
                    <%-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 --%>
					<c:if test="${ !empty param.startDD && !empty param.endDD }">
					<c:set var="searchParam" value="&startYY=${ param.startYY }&startMM=${ param.startMM }&startDD=${ param.startDD }&endYY=${ param.endYY }&endMM=${ param.endMM }&endDD=${ param.endDD }"/>
					</c:if>
	                 <!-- 맨 처음으로(<<) -->
	                 <li><a href="${ pageContext.servletContext.contextPath}/user/search?page=1${ searchParam }">&lt;&lt;</a></li>
	                 <!-- 이전 페이지로(<) -->
	                 <li>
	                 <c:choose>
	                     <c:when test="${ pi.page > 1 }">
	                     <a href="${ pageContext.servletContext.contextPath}/user/search?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
	                             <a href="${ pageContext.servletContext.contextPath}/user/search?page=${ p }${ searchParam }">${ p }</a>
	                         </c:otherwise>
	                     </c:choose>
	                 </li>
	                 </c:forEach>
	                 <!--  다음 페이지로(>) -->
	                 <li>
	                 <c:choose>
	                     <c:when test="${ pi.page < pi.maxPage}">
	                     <a href="${ pageContext.servletContext.contextPath}/user/search?page=${pi.page +1}${ searchParam }">&gt;</a>
	                     </c:when>
	                     <c:otherwise>
	                         <a href="#">&gt;</a>
	                     </c:otherwise>
	                 </c:choose>
	                 </li>
	                 <!-- 맨 끝으로 (>>) -->
	                 <li><a href="${ pageContext.servletContext.contextPath}/user/search?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
	             </ul>
                </div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>