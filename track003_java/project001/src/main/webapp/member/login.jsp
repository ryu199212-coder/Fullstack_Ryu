<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../inc/header.jsp" %>
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->
<!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header --><!-- Header -->

<div class="container card  my-5">
    <h3  class="card-header"> 로그인 </h3>
	<form action="login_process.jsp" method="post">
		<div class="mb-3 mt-3">
			<label for="email" class="form-label">Email : </label> 
			<input type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요" required name="email">
		</div>
		<div class="my-5">
			<label for="password" class="form-label">Password : </label> 
			<input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요" required name="password">
		</div>
		<div class="form-check mb-3">
			<label class="form-check-label">
				<input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>


	
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer --><!-- Footer -->
<%@ include file="../inc/footer.jsp" %>
