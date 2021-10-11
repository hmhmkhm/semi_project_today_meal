<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 밀</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/subpage/productListView.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div id="container">
		<div class="contents">
			<h2>${ cName }</h2>
			<div class="result_option">
				<ul>
					<li <c:if test="${ empty param.st || param.st == 'recent' }">class="active"</c:if>><a href="${ contextPath }/product/list?cno=${ cno }&st=recent">최신순</a></li>
					<li <c:if test="${ param.st == 'satisfaction' }">class="active"</c:if>><a href="${ contextPath }/product/list?cno=${ cno }&st=satisfaction">별점 높은순</a></li>
					<li <c:if test="${ param.st == 'hightprice' }">class="active"</c:if>><a href="${ contextPath }/product/list?cno=${ cno }&st=hightprice">높은 가격순</a></li>
					<li <c:if test="${ param.st == 'lowprice' }">class="active"</c:if>><a href="${ contextPath }/product/list?cno=${ cno }&st=lowprice">낮은 가격순</a></li>
				</ul>
			</div>
			<div class="result_list">
				<ul>
				<c:forEach var="p" items="${ productList }">
				<c:choose>
					<c:when test="${ p.soldOutStatus.equals('Y') }">
					<li class="card sold_out">
						<a href="${ contextPath }/product/info?pno=${ p.pno }">
							<img src="${ contextPath }/resources/uploadFiles/product/${ p.representationImage }" alt="${ p.pname } 대표 이미지">
							<div>
								<em>품절</em>
							</div>
						</a>
						<p>${ p.pname }</p>
						<p><fmt:formatNumber value="${ p.price }" type="number" groupingUsed="true"/></p>
					</li>
					</c:when>
					<c:otherwise>
					<li class="card">
						<a href="${ contextPath }/product/info?pno=${ p.pno }"><img src="${ contextPath }/resources/uploadFiles/product/${ p.representationImage }" alt="${ p.pname } 대표 이미지"></a>
						<p>${ p.pname }</p>
						<p><fmt:formatNumber value="${ p.price }" type="number" groupingUsed="true"/></p>
					</li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</ul>
			</div>
			<div class="paging">
				<c:choose>
					<c:when test="${ empty param.st }">
						<c:set var="addQueryParam" value="&st=recent"/>
					</c:when>
					<c:otherwise>
						<c:set var="addQueryParam" value="&st=${ param.st }"/>
					</c:otherwise>
				</c:choose>
				<jsp:include page="/WEB-INF/views/common/paging/paging.jsp">
					<jsp:param name="url" value="${ contextPath }/product/list?cno=${ cno }&"/>
					<jsp:param name="searchParam" value="${ addQueryParam }"/>
				</jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>