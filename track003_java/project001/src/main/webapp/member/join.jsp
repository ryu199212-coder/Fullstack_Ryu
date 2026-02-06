<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp" %>
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->

<div class="container mt-5">
	<h3>WELCOME! 회원가입</h3>
	<form action="join_process.jsp" method="post">
		<div class="mb-3 mt-3">
			<label for="email" class="form-label">Email : </label>
			<input type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요" required name="email">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password : </label> 
			<input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요" required name="password">
		</div>
		<div class="mb-3">
			<label class="form-check-lable">MBTI TYPE : </label>
			<select name="mbti_type_id" class="form-control" required id="mbti">
				<option value="1">ENTP</option>
				<option value="2">INTP</option>
				<option value="3">INFJ</option>
			</select>
			
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
<!--  action="join_process.jsp"
      method="post"
      name="email" name="password"  name="mbti_type_id" -->
</div>
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<%@ include file="../inc/footer.jsp" %>
