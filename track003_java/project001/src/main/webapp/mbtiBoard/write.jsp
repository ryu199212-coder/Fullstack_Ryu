<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>

   <div class="container card  my-5">
      <h3  class="card-header"> MBTI 글쓰기 </h3>
		<form action="${pageContext.request.contextPath}/write.do?=${param.id}" method="post">
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">TITLE:</label> <input
					type="text" class="form-control" id="title"
					placeholder="제목을 입력해주세요" name="title">
			</div>
			<div class="mb-3">
				<label for="pass" class="form-label">PASS:</label> <input
					type="password" class="form-control" id="pass"
					placeholder="비밀번호를 입력해주세요" name="pass">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">CONTENT:</label> 
				<textarea class="form-control" id="content" placeholder="내용을 입력해주세요" name="content"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">글쓰기</button>
			<a href="<%=request.getContextPath()%>/list.do" class="btn btn-primary">목록보기</a>
		</form>
	</div>
	
<%@ include file="../inc/footer.jsp" %>