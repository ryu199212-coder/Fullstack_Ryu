<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap 5 Website Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
   rel="stylesheet">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.fakeimg {
   height: 200px;
   background: #aaa;
}
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
            <%-- <li class="nav-item">
           <a class="nav-link" href="${pageContext.request.contextPath}/logout.user?appUserId=${appUserId}">로그아웃</a>
         </li>   
         <li class="nav-item">
           <a class="nav-link" href="${pageContext.request.contextPath}/login.user">LOGIN</a>
         </li>
         <li class="nav-item">
           <a class="nav-link" href="${pageContext.request.contextPath}/join.user">JOIN</a>
         </li>    
         <li class="nav-item">
           <a class="nav-link" href="${pageContext.request.contextPath}/mypage.user">${sessionScope.email} page </a>
         </li>  
         <li class="nav-item">
           <a class="nav-link" href="${pageContext.request.contextPath}/list.user">전체회원 보기</a>
         </li>  --%>
            <c:choose>

               <%-- 로그인 상태 --%>
             
               <c:when test="${sessionScope.loginUser != null and not empty sessionScope.email}">
                  <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/mypage.user">
                        ${sessionScope.loginUser.email} Page </a></li>

                  <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/logout.user"> Logout
                  </a></li>
                  	
                  <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/list.user"> List </a>                    
                  </li>
                </c:when>
              

               <%-- 로그아웃 상태 --%>
               <c:otherwise>
                  <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/login.user"> Login </a>
                  </li>

                  <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/join.user"> Join </a></li>
                     
                     <li class="nav-item"><a class="nav-link"
                     href="${pageContext.request.contextPath}/list.user"> List </a>
                  </li>
               </c:otherwise>

            </c:choose>

         </ul>

      </div>
   </nav>
   <!--    header       -->
   <!--    header       -->
   <!--    header       -->