<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 5 Website Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style> .fakeimg { height: 200px; background: #aaa; }  
  </style>
</head>
<body>

<div class="p-5 bg-primary text-white text-center">
  <h1>MBTI Quest Board</h1>
  <p>각종 보드들의 기능익히기 - PAGING + UPLOAD + BOARD</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav"> 
    <c:if test="${not empty sessionScope.email}">
    	  <li class="nav-item">
	        <a class="nav-link"   href="${pageContext.request.contextPath}/list.users">유저관리</a>
	      </li>  
    	  <li class="nav-item">
	        <a class="nav-link"   href="${pageContext.request.contextPath}/mypage.users">${sessionScope.email}</a>
	      </li>  
	      <li class="nav-item">
	        <a class="nav-link"   href="${pageContext.request.contextPath}/logout.users">로그아웃</a>
	      </li>    
	</c:if> 
	<c:if test="${empty sessionScope.email}"> 
	      <li class="nav-item">
	        <a class="nav-link"    href="${pageContext.request.contextPath}/login.users">LOGIN</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link"    href="${pageContext.request.contextPath}/join.users">JOIN</a>
	      </li> 
	</c:if>	         
	      <li class="nav-item">  
	      	 <a class="nav-link" href="${pageContext.request.contextPath}/list.quest">Quest Board</a>  
	      </li>   
    </ul> 
  </div>
</nav>
<!-- 	header		 -->
<!-- 	header		 -->
<!-- 	header		 -->
    