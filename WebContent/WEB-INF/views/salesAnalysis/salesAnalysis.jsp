<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today_meal</title>
<link href="<%= request.getContextPath() %>/resources/css/salesAnalysis/salesAnalysis.css?ver=1.112" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
 integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div id="container">
		<nav class="snb">
			<jsp:include page="/WEB-INF/views/common/adminPage/subMenu.jsp"/>
		</nav>
		<div class="myPageFrame">
        <div class="myPageWrapper">
            <div class="titleBox">
                <span><h2 class="titleH2">매출 TOP 5 !</h2></span>
            </div>
            
            <div class="rankingContent">
            	<c:forEach var="ranking" items="${ rankingList }" varStatus="status">
            		<div class="rankBox">
            			<img src="<%= request.getContextPath() %>/resources/images/salesAnalysis/top${ status.count }.png">
            			<img src="<%= request.getContextPath() %>/resources/uploadFiles/product/${ ranking.productImg }">
            			<p>
	                        [<c:out value="${ ranking.category }"/>]&nbsp;<c:out value="${ ranking.productName }"/><br>
				                       판매수량 : <fmt:formatNumber value="${ ranking.salesQuantity }"  groupingUsed="true"/>개<br>
				                       총 매출액 : <fmt:formatNumber value="${ ranking.total }"  groupingUsed="true"/>원
	                    </p>
            		</div>
            	</c:forEach>
            </div>
            <div class="titleBox">
                <span><h2 class="titleH2">일자별 매출 현황</h2></span>
                <input type="hidden" id="analysisType">
            </div>
            <div class="salesOfDateBox">
                <div class="mpSearchArea">
                    <select class="yearDropdown" id="startYY" name="dailyStartYY">
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                    </select>
                    <label for="startYY">년</label>
                    <select class="monthDateDropdown" id="startMM" name="dailyStartMM">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <label for="startMM">월</label>
                    <select class="monthDateDropdown" id="startDD" name="dailyStartDD">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                    <label for="startDD">일</label>
                    &nbsp;&nbsp;~&nbsp;&nbsp;
                    <select class="yearDropdown" id="endYY" name="dailyEndYY">
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                    </select>
                    <label for="endYY">년</label>
                    <select class="monthDateDropdown" id="endMM" name="dailyEndMM">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <label for="endMM">월</label>
                    <select class="monthDateDropdown" id="endDD" name="dailyEndDD">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                    <label for="endDD">일</label>
                    <button id="searchDate" class="searchBtn">조회하기</button>
                </div>
                <div class="salesOfDateTable">
                    <table>
                        <thead>
                            <tr>
                                <th>판매일자</th>
                                <th>요일</th>
                                <th>영수건수</th>
                                <th>총매출액</th>
                                <th>실 매출액</th>
                                <th>취소액</th>
                                <th>반품액</th>
                            </tr>
                        </thead>
                        <tbody>
                        
                        </tbody>
                        
                    </table>
                </div>
                <div class="titleBox">
                    <span><h2 class="titleH2">월별 매출 현황</h2></span>
                </div>
                <div class="mpSearchArea">
                    <select class="yearDropdown" id="startYY" name="monthlyStartYY">
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                    </select>
                    <label for="startYY">년</label>
                    <select class="monthDateDropdown" id="startMM" name="monthlyStartMM">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <label for="startMM">월</label>
                    &nbsp;&nbsp;~&nbsp;&nbsp;
                    <select class="yearDropdown" id="endYY" name="monthlyEndYY">
                        <option value="2021">2021</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                    </select>
                    <label for="endYY">년</label>
                    <select class="monthDateDropdown" id="endMM" name="monthlyEndMM">
                        <option value="01">1</option>
                        <option value="02">2</option>
                        <option value="03">3</option>
                        <option value="04">4</option>
                        <option value="05">5</option>
                        <option value="06">6</option>
                        <option value="07">7</option>
                        <option value="08">8</option>
                        <option value="09">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <label for="endMM">월</label>
                    <button id="searchMonth" class="searchBtn" style="margin-left: 286px;">조회하기</button>
                </div>
                <div class="salesOfMonthTable">
                    <table>
                        <thead>
                            <tr>
                                <th>월</th>
                                <th>영수건수</th>
                                <th>총 매출액</th>
                                <th>실 매출액</th>
                                <th>취소액</th>
                                <th>반품액</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            
        </div>
    </div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
      	$(function(){
			$("#searchDate").click(function(){
				$("#analysisType").val('date');
				
				$.ajax({
					url : "${pageContext.servletContext.contextPath}/admin/sales/analysis",
					type : "post",
					dataType : "json",
					data : {analysisType : $("#analysisType").val(),
							dailyStartYY : $("[name=dailyStartYY]").val(),
							dailyStartMM : $("[name=dailyStartMM]").val(),
							dailyStartDD : $("[name=dailyStartDD]").val(),
							dailyEndYY : $("[name=dailyEndYY]").val(),
							dailyEndMM : $("[name=dailyEndMM]").val(),
							dailyEndDD : $("[name=dailyEndDD]").val()},
					success : function(list){
						
						var html ='';
						$.each(list, function(index, value){
							
							if(index >= 34){
								if(index == 34){alert('데이터 조회 최대 개수는 35개 입니다.');}
							} else{
								html += '<tr>';
								html += '<td>' + value.salesDate + '</td>';
								html += '<td>' + value.saleDay + '</td>';
								html += '<td>' + value.countSales  + '</td>';
								html += '<td>' + value.totalSales + '</td>';
								html += '<td>' + value.actualSales + '</td>';
								html += '<td>' + value.cancelSales + '</td>';
								html += '<td>' + value.returnSales + '</td>';
								html += '</tr>';
							}
							

						});
						
						$(".salesOfDateTable table tbody").html(html);
						
					},
					error : function(e){
						console.log(e);
					}
				});
				
			});
		});
      	
      	
      	$(function(){
			$("#searchMonth").click(function(){
				$("#analysisType").val('month');
				
				$.ajax({
					url : "${pageContext.servletContext.contextPath}/admin/sales/analysis",
					type : "post",
					dataType : "json",
					data : {analysisType : $("#analysisType").val(),
							monthlyStartYY : $("[name=monthlyStartYY]").val(),
							monthlyStartMM : $("[name=monthlyStartMM]").val(),
							monthlyEndYY : $("[name=monthlyEndYY]").val(),
							monthlyEndMM : $("[name=monthlyEndMM]").val()},
					success : function(list){
						
						var html ='';
						$.each(list, function(index, value){
							if(index >= 11){
								if(index == 11){alert('데이터 조회 최대 개수는 12개 입니다.');}
							} else{
								html += '<tr>';
								html += '<td>' + value.salesDate + '</td>';
								html += '<td>' + value.countSales  + '</td>';
								html += '<td>' + value.totalSales + '</td>';
								html += '<td>' + value.actualSales + '</td>';
								html += '<td>' + value.cancelSales + '</td>';
								html += '<td>' + value.returnSales + '</td>';
								html += '</tr>';
							}
						});
						
						$(".salesOfMonthTable table tbody").html(html);
						
					},
					error : function(e){
						console.log(e);
					}
				});
			});
		});
    </script>
</body>
</html>