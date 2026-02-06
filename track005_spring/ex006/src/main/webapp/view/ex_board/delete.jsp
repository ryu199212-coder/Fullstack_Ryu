<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>

<c:if test="${not empty success}">
	<script>
		alert("${success}");
		if ("${success}" === "탈퇴 성공") {
			location.href = '${pageContext.request.contextPath}/list.user';
		}
	</script>
</c:if>

<body>
	<div class="container mt-5">
		<div class="card shadow-sm mx-auto" style="max-width: 500px;">
			<div class="card-body text-center">
				<h2 class="card-title mb-4">회원탈퇴</h2>

				<p>비밀번호를 입력</p>
				<form action="${pageContext.request.contextPath}/delete.user"
					method="post">
					<input type="hidden" name="appUserId" value="${dto.appUserId}" />

					<div class="mb-3">
						<input type="password" name="password" class="form-control"
							placeholder="비밀번호 입력" required />
					</div>

					<div class="d-flex justify-content-center gap-3">
						<button type="submit" class="btn btn-danger">탈퇴</button>
						<a href="${pageContext.request.contextPath}/mypage.user"
							class="btn btn-secondary">취소</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../inc/footer.jsp"%>
</body>
