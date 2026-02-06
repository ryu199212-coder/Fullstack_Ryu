<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
   <div class="container card  my-5">
      <h3  class="card-header"> REQUEST - EX GOOGLE </h3>
		 <pre  class="alert alert-warning">
         https://www.google.com/search?q=APPLE 
         1.처리컨테이너 : https://www.google.com/search  
         2.처리방식      : get  
         3.보관용기      : q  
      </pre>
      <form action="https://www.google.com/search" method="get">
			<div class="mb-3 mt-3">
				<label for="search" class="form-label">GOOGLE에 물어보세요~! 검색어</label> <input
					type="search" class="form-control" id="search"
					placeholder="검색어를 입력해주세요" name="q">
			</div>
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
   </div>
</body>
</html>