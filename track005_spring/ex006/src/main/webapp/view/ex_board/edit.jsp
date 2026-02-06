<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<c:if test="${not empty success}">
	<script>
		alert("${success}");
		if ("${success}" === "수정 성공") {
			location.href = '${pageContext.request.contextPath}/mypage.user';
		}
	</script>
</c:if>
<body>

	<div class="container mt-5">
		<div class="card shadow-sm">
			<div class="card-body">
				<h2 class="card-title text-center mb-4">회원 정보 수정</h2>


				<form action="${pageContext.request.contextPath}/editUpload.user" 
			          method="post" 
			          enctype="multipart/form-data">

					<input type="hidden" name="appUserId" value="${dto.appUserId}">

					<div class="mb-3">
						<label for="mbtiTypeId" class="form-label">MBTI</label> <input
							type="text" class="form-control" id="mbtiTypeId"
							name="mbtiTypeId" value="${dto.mbtiTypeId}" required>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">새 비밀번호</label> <input
							type="password" class="form-control" id="password"
							name="password" required>
					</div>

					<div class="d-flex justify-content-center gap-3 mt-4 flex-wrap">
						<button type="submit" class="btn btn-primary">수정하기</button>
						<a href="mypage.user" class="btn btn-secondary">취소</a>
					</div>
					
					<div class="mb-3">
			            <label for="file" class="form-label">프로필 사진:</label>
			            <input type="file" class="form-control" id="file" name="file" accept="image/*">
        			</div>
					
					
				</form>
			</div>
		</div>
	</div>
</body>

<%@ include file="../inc/footer.jsp"%>
