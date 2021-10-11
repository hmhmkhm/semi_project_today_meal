<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/resources/css/common/adminPage/subMenu.css" rel="stylesheet">
</head>
<body>
<div class="snb_inner">
	<h2>관리자 페이지</h2>
	<ul>
		<li><a href="${ contextPath }/sale/list"><span>판매 관리</span></a></li>
		<li><a href="${ contextPath }/admin/sales/analysis"><span>매출 관리</span></a></li>
		<li>
			<span>상품 관리</span>
			<ul class="depth_2">
				<li><a href="${ contextPath }/product/enroll">상품 등록</a></li>
				<li><a href="${ contextPath }/product/listView">상품 조회/수정</a></li>
			</ul>
		</li>
		<li><a href="#"><span>재고 관리</span></a></li>
		<li>
			<span>문의 관리</span>
			<ul class="depth_2">
				<li><a href="${ contextPath }/faq/insert">FAQ 관리</a></li>
				<li><a href="#">1:1 문의 관리</a></li>
			</ul>
		</li>
		<li><a href="${ contextPath }/admin/eventlist"><span>이벤트 게시판 관리</span></a></li>
	</ul>
</div>
</body>
</html>