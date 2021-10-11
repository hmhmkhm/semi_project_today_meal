<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String message = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
<link href="<%= request.getContextPath() %>/resources/css/common/reset.css" rel="stylesheet">
<style>
   #imageArea{
       width: 500px;
       margin: 100px auto;
       display: flex;
       justify-content: center;
       align-items: center;
   }
   #imageArea img {
       width: 100%;
   }
</style>
</head>
<body>
   <%@ include file="/WEB-INF/views/common/header.jsp" %>
   <div id="imageArea">
       <img id="errorImage" src="<%= request.getContextPath() %>/resources/images/common/errorPage/error.png">
   </div>
   <h1 align="center" ><%= message %></h1>

</body>
</html>