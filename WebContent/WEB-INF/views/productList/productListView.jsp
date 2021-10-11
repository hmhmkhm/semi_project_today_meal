<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 조회/수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/productList/productListView.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<div id="container">
		<nav class="snb">
			<jsp:include page="/WEB-INF/views/common/adminPage/subMenu.jsp"/>
		</nav>
		<div class="contents">
			<div class="inner">
			 	<div class="product_title">
			 		상품 조회/수정
			 	</div>
			 	<form name="categoryForm" method="get">
			 	<div class="product_category">			 	
				 	<div class="category_div">카테고리</div>
				 	<select name="searchCategory" id="category">
 						<option selected="selected">- 선택 -</option>
 						<option value="한식" <c:if test="${ param.searchCategory == '한식' }">selected</c:if>>한식</option>
 						<option value="양식" <c:if test="${ param.searchCategory == '양식' }">selected</c:if>>양식</option>
 						<option value="중식" <c:if test="${ param.searchCategory == '중식' }">selected</c:if>>중식</option>
 						<option value="일식" <c:if test="${ param.searchCategory == '일식' }">selected</c:if>>일식</option>
 						<option value="분식" <c:if test="${ param.searchCategory == '분식' }">selected</c:if>>분식</option>
 						<option value="야식" <c:if test="${ param.searchCategory == '야식' }">selected</c:if>>야식</option>
 						<option value="샐러드" <c:if test="${ param.searchCategory == '샐러드' }">selected</c:if>>샐러드</option>
 					</select>			
			 	</div> 
			 	</form>	
				<form method="get" action="${ contextPath }/product/listView">
				<div class="product_search">
					<div class="search_div">검색 항목</div>
					<div>	
						<select id="searchCondition" name="searchCondition">
							<option value="procode" <c:if test="${ param.searchCondition == 'procode' }">selected</c:if>>상품코드</option>
							<option value="proname" <c:if test="${ param.searchCondition == 'proname' }">selected</c:if>>상품명</option>
						</select> 
						<input type="search" id="searchbox" name="searchValue" value="${ param.searchValue }">
						<button type="submit" class="searchBtn" onclick="validate()">검색</button>
					</div>
				</div>
				</form>
			 	
			 	<div class="product_list">
					<form name="checkDeleteForm" method="post" action="<%= request.getContextPath() %>/product/checkDelete">
					<button id="chdel_btn">선택 삭제</button>
					<table class="pro_table">
						<thead>
							<th class="pro_th"><input type="checkbox" id="check_list" onclick="selectAll()"></th>
							<th class="pro_th">상품코드</th>
							<th class="pro_th">카테고리</th>
							<th class="pro_th">상품명</th>
							<th class="pro_th">상품관리</th>
						</thead>
						<tbody>
						<c:forEach var="p" items="${ productList }">
							<tr>
								<td class="pro_td"><input type="checkbox" name="pNo" value="${ p.pNo }"></td>
					</form>
								<td class="pro_td">${ p.pNo }</td>
								<td class="pro_td">${ p.cName }</td>
								<td class="pro_td">${ p.pName }</td>
								<td class="pro_td">
									<button type="button" id="mod_btn" onclick="updateProductView(${p.pNo})">수정</button>
									<button type="button" id="del_btn" onclick="deleteProduct(${p.pNo})">삭제</button>
									<form name="productForm" method="post">
										<input type="hidden" name="pNo" value="${ p.pNo }">
									</form>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div> <!-- inner div -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		// 카테고리 선택 시 list 재호출
		$("#category").change(function(){
			document.forms.categoryForm.action = '${contextPath}/product/listView';
			document.forms.categoryForm.submit();
		})

		// 상품 삭제
		function deleteProduct(pNo){
			if(confirm('상품을 삭제하시겠습니까?')){
				document.forms.productForm.action = '${contextPath}/product/delete';
				document.forms.productForm.submit();
			}
		}
		
		// 상품 수정
		function updateProductView(pNo){
			document.forms.productForm.action = '${contextPath}/product/updateView?pNo=' + pNo;
			document.forms.productForm.submit();
		}

		// 체크박스 - 전체 선택/해제
		function selectAll(){
       		if($("#check_list").is(":checked")){
       			$("input[type='checkbox']").prop("checked", true);
       		} else {
       			$("input[type='checkbox']").prop("checked", false);
       		}		
       	}


	</script>
</body>
</html>