<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요 목록</title>
<link href="<%= request.getContextPath() %>/resources/css/myLike/myLikeListView.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div id="container">
		<jsp:include page="/WEB-INF/views/common/mypageSubMenubar.jsp"/>
		<div class="contents">
			<div class="contents_inner">
				<h2>나의 좋아요 목록</h2>
				<div class="like_list <c:if test="${ myLikeList.size() == 0 }">empty_result</c:if>">
					<div class="list_header">
						<div>
							<input type="checkbox" name="select_all" id="select_all" <c:if test="${ myLikeList.size() == 0 }">disabled</c:if>>
							<label for="select_all">전체 선택</label>
						</div>
						<button type="button" class="green_button" disabled>선택 삭제</button>
					</div>
					<div class="list_body">
					<c:choose>
						<c:when test="${ myLikeList.size() == 0 }">
							<p>좋아요한 리뷰가 없습니다.</p>
						</c:when>
						<c:otherwise>
						<c:forEach var="like" items="${ myLikeList }">
						<div class="item">
							<div><input type="checkbox" name="select_row" data-like-no=${ like.lno }></div>
							<div class="image_area"><img src="${ contextPath }/resources/uploadFiles/product/${ like.review.product.representationImage }" alt="음식 대표 사진"></div>
							<div class="description_area <c:if test="${ like.review.product.optionList.size() == 0 }">no_option</c:if>">
								<h4>${ like.review.product.pname }</h4>
								<p>수량 : <span>${ like.review.product.buyQuantity }</span>개</p>
								<c:if test="${ like.review.product.optionList.size() > 0 }">
									<div class="option_area">
									<button type="button" class="green_button option">옵션 전체 보기</button>
									<div class="option_layer">
										<div>
										<c:forEach var="option" items="${ like.review.product.optionList }">
											<dl>
												<dt>${ option.name }</dt>
												<dd>${ option.buyQuantity }</dd>
											</dl>
										</c:forEach>
										</div>
									</div>
								</div>
								</c:if>
							</div>
							<div class="price_area">
								<p><fmt:formatNumber value="${ like.review.sum }" type="number" groupingUsed="true"/></p>
							</div>
							<div class="button_area">
								<div>
									<button type="button" class="green_button cart" <c:if test="${ !like.review.status }">disabled</c:if>>구매하기</button>
									<button type="button" class="green_button delete" data-like-no=${ like.lno }>삭제하기</button>
								</div>
							</div>
						</div>
						</c:forEach>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="paging">
						<jsp:include page="/WEB-INF/views/common/paging/paging.jsp">
							<jsp:param name="url" value="${ contextPath }/myLike/list?"/>
							<jsp:param name="searchParam" value=""/>
						</jsp:include>
					</div>
				</div>
			</div>
		</div>
		<div class="dimmed">
			<div id ="modal">
				<div class="modal_inner">
					<div class="delete_all_inner">
						<h3>선택된 목록을 삭제하시겠습니까?</h3>
						<div class="button_area">
							<button type="button" class="green_button">취소</button>
							<button type="button" class="confirm_button">삭제</button>
						</div>
					</div>
					<div class="delete_inner">
						<h3>해당 목록을 삭제하시겠습니까?</h3>
						<div class="button_area">
							<button type="button" class="green_button">취소</button>
							<button type="button" class="confirm_button">삭제</button>
						</div>
					</div>
				</div>
				<button type="button" class="close_button"></button>
				<form name="deleteForm" method="post"></form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="${ contextPath }/resources/js/myLike/myLike.js"></script>
</body>
</html>