<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<c:if test="${not empty success}">
	<div class="alert alert-success">${success}</div>
</c:if>
<body>
	<div class="container mt-5">
		<div class="card shadow-sm">
			<div class="card-body">
				<h2 class="card-title text-center mb-4">마이페이지</h2>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><strong>사진:</strong> 
					<c:if test="${not empty dto.ufile}">
					<img src="${pageContext.request.contextPath}/upload/${dto.ufile}"
						alt="프로필 사진" style="max-width: 150px; height: auto;" />
						</c:if> <c:if test="${empty dto.ufile}">
							<span>등록된 사진 없음</span>
						</c:if></li>

					<li class="list-group-item"><strong>이메일:</strong> ${dto.email}</li>
					<li class="list-group-item"><strong>MBTI:</strong> <c:choose>
							<c:when test="${dto.mbtiTypeId == 1}">ISTJ</c:when>
							<c:when test="${dto.mbtiTypeId == 2}">ISFJ</c:when>
							<c:when test="${dto.mbtiTypeId == 3}">INFJ</c:when>
							<%-- 필요하면 다른 MBTI 타입을 추가 --%>
							<c:otherwise>Unknown</c:otherwise>
						</c:choose></li>
					<li class="list-group-item"><strong>가입일:</strong>
						${dto.createdAt}</li>


				</ul>
				<div class="d-flex justify-content-center gap-3 mt-4 flex-wrap">
					<a href="list.user" class="btn btn-outline-primary">메인으로</a> <a
						href="edit.user?appUserId=${dto.appUserId}"
						class="btn btn-outline-warning">정보수정</a> <a
						href="delete.user?appUserId=${dto.appUserId}"
						class="btn btn-outline-danger">회원탈퇴</a>

				</div>
			</div>
		</div>
	</div>
</body>

<%@ include file="../inc/footer.jsp"%>



<!-- 1. mypage -  유형 1,2,3,
	 2. first님      MbtiBaord   /  로그인 회원가입
	 3. 테이블에서 숫자자동으로 카운트 -->