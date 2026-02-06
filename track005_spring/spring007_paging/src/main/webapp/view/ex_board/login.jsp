<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../inc/header.jsp"%>

<c:if test="${not empty success}">
	<script>
		alert("${success}");
		if ("${success}" === "로그인 성공") {
			location.href = '${pageContext.request.contextPath}/mypage.user';
		}
	</script>
</c:if>

<!-- 로그인 폼 -->
<form action="${pageContext.request.contextPath}/login.user"
	method="post">
	<div class="mb-3 mt-3">
		<label for="email" class="form-label">Email:</label> <input
			type="email" class="form-control" id="email" placeholder="이메일을 적어주세요"
			required name="email">
	</div>
	<div class="mb-3">
		<label for="password" class="form-label">Password:</label> <input
			type="password" class="form-control" id="password"
			placeholder="비밀번호를 적어주세요" required name="password">
	</div>
	<button type="submit" class="btn btn-primary">로그인</button>
</form>
</div>

<%@ include file="../inc/footer.jsp"%>
