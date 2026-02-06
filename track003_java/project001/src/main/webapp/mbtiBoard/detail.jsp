<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

   <div class="container card  my-5">
      <h3  class="card-header"> MBTI 글상세보기 </h3>
		<form action="detail.do" method="post">
			<div class="mb-3 mt-3">
				<label for="hit" class="form-label">조회수</label> 
				<input type="email" class="form-control" id="hit" name="hit" readonly value="${dto.hit}">
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">TITLE:</label> <input
					type="text" class="form-control" id="title"
					placeholder="내용을 입력해주세요" name="title" readonly value="${dto.title}">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">CONTENT:</label> 
				<textarea class="form-control" id="content" placeholder="내용을 입력해주세요"
				readonly name="content">${dto.content}</textarea>
			</div>
		<c:if test="${not empty email}">
			<div class="mb-3">
				<a href="<%=request.getContextPath()%>/editView.do?id=${dto.id}" class="btn btn-dark form-control">글수정</a>
			</div>
			<div class="mb-3">
				<a href="<%=request.getContextPath()%>/deleteView.do?id=${dto.id}" class="btn btn-secondary form-control">글삭제</a>
			</div>
		</c:if>
			
			<div class="mb-3">
				<a href="<%=request.getContextPath()%>/list.do" class="btn btn-dark form-control">목록보기</a>
			</div>
		</form>
	</div>
<%@ include file="../inc/footer.jsp" %>