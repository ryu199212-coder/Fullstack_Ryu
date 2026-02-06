<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="../inc/header.jsp" %>

<div class="container card my-5 p-4">
  <h3 class="card-header">MBTI QUEST 글수정</h3>

  <!-- 로그인한 사용자만 글 수정 가능 -->
  <sec:authorize access="isAuthenticated()">
    <form action="${pageContext.request.contextPath}/updateEdit.quest"  
          method="post" enctype="multipart/form-data"> 

      <!-- 글 번호 전달 -->
      <input type="hidden" name="id" value="${dto.id}"/>  

      <!-- CSRF 토큰 전달 -->
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

      <div class="mb-3 mt-3">
        <label for="btitle" class="form-label">TITLE:</label>
        <input type="text" class="form-control" id="btitle" 
               placeholder="내용을 입력해주세요" name="btitle" value="${dto.btitle}">
      </div>

      <div class="mb-3">
        <label for="bpass" class="form-label">PASS:</label>
        <input type="password" class="form-control" id="bpass" 
               placeholder="비밀번호를 입력해주세요" name="bpass">
      </div>

      <div class="mb-3">
        <label for="bcontent" class="form-label">CONTENT:</label>
        <textarea class="form-control" id="bcontent"
                  placeholder="내용을 입력해주세요" name="bcontent">${dto.bcontent}</textarea>
      </div> 

      <div class="mb-3">
        <label for="file" class="form-label">FILE:</label>
        <input type="file" class="form-control" id="file" name="file">
        <input type="text" class="form-control" id="bfile" readonly name="bfile" value="${dto.bfile}">
      </div>      

      <div class="mb-3 text-end">
        <button type="submit" class="btn btn-primary">글수정</button>
        <a href="javascript:history.go(-1)" class="btn btn-danger">BACK</a>
      </div>
    </form>
  </sec:authorize>

  <!-- 비로그인 사용자 안내 -->
  <sec:authorize access="!isAuthenticated()">
    <p class="alert alert-warning">로그인 후 글수정이 가능합니다.</p>
  </sec:authorize>
</div>

<%@include file="../inc/footer.jsp" %>
